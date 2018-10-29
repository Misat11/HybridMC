package misat11.hybrid.network.bedrock.session;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.google.common.base.Preconditions;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;
import com.nukkitx.server.NukkitServer;
import com.nukkitx.server.network.bedrock.BedrockPacketCodec;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import com.nukkitx.server.network.bedrock.packet.ClientToServerHandshakePacket;
import com.nukkitx.server.network.bedrock.packet.LoginPacket;
import com.nukkitx.server.network.bedrock.packet.PlayStatusPacket;
import com.nukkitx.server.network.bedrock.packet.ResourcePacksInfoPacket;
import com.nukkitx.server.network.bedrock.session.data.AuthData;
import com.nukkitx.server.network.bedrock.session.data.ClientData;
import com.nukkitx.server.network.util.EncryptionUtil;
import com.nukkitx.server.util.NativeCodeFactory;
import com.voxelwind.server.jni.CryptoUtil;

import misat11.hybrid.Platform;
import misat11.hybrid.downstream.DownstreamConnection;

import static misat11.hybrid.Platform.log;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Pattern;

public class HybridLoginPacketHandler implements NetworkPacketHandler {
	private static final boolean CAN_USE_ENCRYPTION = CryptoUtil.isJCEUnlimitedStrength()
			|| NativeCodeFactory.cipher.isLoaded();
	private static final String MOJANG_PUBLIC_KEY_BASE64 = "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAE8ELkixyLcwlZryUQcu1TvPOmI2B7vX83ndnWRUaXm74wFfa5f/lwQNTfrLVHa2PmenpGI6JhIMUJaWZrjmMj90NoKNFSNBuKdm8rYiXsfaz3K36x/1U26HpG0ZxK/V1V";
	private static final ECPublicKey MOJANG_PUBLIC_KEY;
	private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]*$");

	static {
		try {
			MOJANG_PUBLIC_KEY = generateKey(MOJANG_PUBLIC_KEY_BASE64);
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			throw new AssertionError(e);
		}
	}

	private final HybridSession session;

	public HybridLoginPacketHandler(HybridSession session) {
		this.session = session;
	}

	private static ECPublicKey generateKey(String b64) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return (ECPublicKey) KeyFactory.getInstance("EC")
				.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(b64)));
	}

	@Override
	public void handle(ClientToServerHandshakePacket packet) {
		initializePlayerSession();
	}

	@Override
	public void handle(LoginPacket packet) {
		int protocolVersion = packet.getProtocolVersion();
		session.setProtocolVersion(protocolVersion);

		if (!session.getPacketCodec().isCompatibleVersion(protocolVersion)) {
			PlayStatusPacket status = new PlayStatusPacket();
			if (protocolVersion > BedrockPacketCodec.BROADCAST_PROTOCOL_VERSION) {
				status.setStatus(PlayStatusPacket.Status.FAILED_SERVER);
			} else {
				status.setStatus(PlayStatusPacket.Status.FAILED_CLIENT);
			}
		}

		JsonNode certData;
		try {
			certData = NukkitServer.JSON_MAPPER.readTree(packet.getChainData().toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Certificate JSON can not be read.");
		}

		JsonNode certChainData = certData.get("chain");
		if (certChainData.getNodeType() != JsonNodeType.ARRAY) {
			throw new RuntimeException("Certificate data is not valid");
		}

		boolean validChain;
		try {
			validChain = validateChainData(certChainData);

			JWSObject jwt = JWSObject.parse(certChainData.get(certChainData.size() - 1).asText());
			JsonNode payload = NukkitServer.JSON_MAPPER.readTree(jwt.getPayload().toBytes());

			if (payload.get("extraData").getNodeType() != JsonNodeType.OBJECT) {
				throw new RuntimeException("AuthData was not found!");
			}
			AuthData authData = NukkitServer.JSON_MAPPER.convertValue(payload.get("extraData"), AuthData.class);
			session.setAuthData(authData);

			if (payload.get("identityPublicKey").getNodeType() != JsonNodeType.STRING) {
				throw new RuntimeException("Identity Public Key was not found!");
			}

			if (!validChain) {
				// Disconnect if xbox auth is enabled.
				if (Platform.xbox()) {
					session.disconnect("disconnectionScreen.notAuthenticated");
					return;
				}
				// Stop spoofing.
				session.getAuthData().setXuid(null);
				// Check for valid name characters
				if (!USERNAME_PATTERN.matcher(authData.getDisplayName()).matches()) {
					session.disconnect("disconnectionScreen.invalidName");
					return;
				}
				// Use server side UUID.
				session.getAuthData().setOfflineIdentity(UUID
						.nameUUIDFromBytes(authData.getDisplayName().toLowerCase().getBytes(StandardCharsets.UTF_8)));
			}

			ECPublicKey identityPublicKey = generateKey(payload.get("identityPublicKey").textValue());

			JWSObject clientJwt = JWSObject.parse(packet.getSkinData().toString());

			if (!verifyJwt(clientJwt, identityPublicKey)
					&& Platform.xbox()) {
				session.disconnect("disconnectionScreen.invalidSkin");
			}

			JsonNode clientPayload = NukkitServer.JSON_MAPPER.readTree(clientJwt.getPayload().toBytes());
			ClientData clientData = NukkitServer.JSON_MAPPER.convertValue(clientPayload, ClientData.class);
			session.setClientData(clientData);

			if (CAN_USE_ENCRYPTION) {
				startEncryptionHandshake(identityPublicKey);
			} else {
				initializePlayerSession();
			}
		} catch (Exception e) {
			session.disconnect("disconnectionScreen.internalError.cantConnect");
			throw new RuntimeException("Unable to complete login", e);
		}
	}

	private void startEncryptionHandshake(PublicKey key) throws Exception {
		// Generate a fresh key for each session
		KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
		generator.initialize(new ECGenParameterSpec("secp384r1"));
		KeyPair serverKeyPair = generator.generateKeyPair();

		// Enable encryption server-side
		byte[] token = EncryptionUtil.generateRandomToken();
		byte[] serverKey = EncryptionUtil.getServerKey(serverKeyPair, key, token);
		session.enableEncryption(serverKey);

		// Now send the packet to enable encryption on the client
		session.sendImmediatePackage(EncryptionUtil.createHandshakePacket(serverKeyPair, token));
	}

	private void initializePlayerSession() {
		log("§f[" + session.getAuthData().getDisplayName() + "/"
				+ session.getRemoteAddress().map(Object::toString).orElse("UNKNOWN")
				+ "] §aNew Bedrock player connected to server §r(protocol " + session.getProtocolVersion() + ")");
		PlayStatusPacket status = new PlayStatusPacket();
		status.setStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
		session.addToSendQueue(status);

		session.setHandler(new HybridPlayPacketHandler(session));

		session.downstreamConnect();

		ResourcePacksInfoPacket info = new ResourcePacksInfoPacket();
		session.addToSendQueue(info);
	}

	private boolean validateChainData(JsonNode data) throws Exception {
		ECPublicKey lastKey = null;
		boolean validChain = false;
		for (JsonNode node : data) {
			JWSObject jwt = JWSObject.parse(node.asText());

			if (lastKey == null) {
				validChain = verifyJwt(jwt, MOJANG_PUBLIC_KEY);
			} else {
				validChain = verifyJwt(jwt, lastKey);
			}

			JsonNode payloadNode = NukkitServer.JSON_MAPPER.readTree(jwt.getPayload().toString());
			JsonNode ipkNode = payloadNode.get("identityPublicKey");
			Preconditions.checkState(ipkNode != null && ipkNode.getNodeType() == JsonNodeType.STRING,
					"identityPublicKey node is missing in chain");
			lastKey = generateKey(ipkNode.asText());
		}
		return validChain;
	}

	private boolean verifyJwt(JWSObject jwt, ECPublicKey key) throws JOSEException {
		return jwt.verify(new DefaultJWSVerifierFactory().createJWSVerifier(jwt.getHeader(), key));
	}
}
