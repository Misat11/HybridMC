package misat11.hybrid.network.bedrock.session;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.nukkitx.network.NetworkPacket;
import com.nukkitx.network.NetworkSession;
import com.nukkitx.network.raknet.RakNetPacket;
import com.nukkitx.network.raknet.session.RakNetSession;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.BedrockPacketCodec;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import com.nukkitx.server.network.bedrock.annotations.NoEncryption;
import com.nukkitx.server.network.bedrock.packet.DisconnectPacket;
import com.nukkitx.server.network.bedrock.session.data.AuthData;
import com.nukkitx.server.network.bedrock.session.data.ClientData;
import com.nukkitx.server.network.bedrock.wrapper.DefaultWrapperHandler;
import com.nukkitx.server.network.bedrock.wrapper.WrapperHandler;
import com.nukkitx.server.util.NativeCodeFactory;
import com.voxelwind.server.jni.hash.VoxelwindHash;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import misat11.hybrid.HybridServer;
import misat11.hybrid.downstream.DownstreamConnection;
import misat11.hybrid.network.bedrock.packet.HybridWrappedPacket;
import net.md_5.bungee.jni.cipher.BungeeCipher;

import static misat11.hybrid.Platform.log;
import static misat11.hybrid.Platform.debug;

public class HybridSession implements NetworkSession<RakNetSession> {
	private static final ThreadLocal<VoxelwindHash> hashLocal = ThreadLocal
			.withInitial(NativeCodeFactory.hash::newInstance);
	private static final InetSocketAddress LOOPBACK_BEDROCK = new InetSocketAddress(InetAddress.getLoopbackAddress(),
			19132);
	private final Queue<BedrockPacket> currentlyQueued = new ConcurrentLinkedQueue<>();
	private final AtomicLong sentEncryptedPacketCount = new AtomicLong();
	private final HybridServer server;
	private BedrockPacketCodec packetCodec = BedrockPacketCodec.DEFAULT;
	private NetworkPacketHandler handler = new HybridLoginPacketHandler(this);
	private WrapperHandler wrapperHandler = DefaultWrapperHandler.DEFAULT;
	private RakNetSession connection;
	private AuthData authData;
	private ClientData clientData;
	private BungeeCipher encryptionCipher;
	private BungeeCipher decryptionCipher;
	private byte[] serverKey;
	private int protocolVersion;

	public HybridSession(HybridServer server, RakNetSession connection) {
		this.server = server;
		this.connection = connection;
	}

	public AuthData getAuthData() {
		return authData;
	}

	public void setAuthData(AuthData authData) {
		this.authData = authData;
	}

	public NetworkPacketHandler getHandler() {
		return handler;
	}

	public void setHandler(NetworkPacketHandler handler) {
		if (checkForClosed())
			return;
		this.handler = handler;
	}

	void setPacketCodec(BedrockPacketCodec packetCodec) {
		this.packetCodec = packetCodec;
	}

	public BedrockPacketCodec getPacketCodec() {
		return packetCodec;
	}

	public WrapperHandler getWrapperHandler() {
		return wrapperHandler;
	}

	public void setWrapperHandler(WrapperHandler wrapperHandler) {
		if (checkForClosed())
			return;
		this.wrapperHandler = wrapperHandler;
	}

	private boolean checkForClosed() {
		return isClosed();
	}

	public void addToSendQueue(BedrockPacket packet) {
		if (checkForClosed())
			return;
		if (debug()) {
			String to = connection.getRemoteAddress().orElse(LOOPBACK_BEDROCK).toString();
			log("Outbound " + to + ": " + packet);
		}

		// Verify that the packet ID exists.
		packetCodec.getId(packet);

		currentlyQueued.add(packet);
	}

	public void sendImmediatePackage(NetworkPacket packet) {
		if (checkForClosed())
			return;
		internalSendPackage(packet);
	}

	private void internalSendPackage(NetworkPacket packet) {
		if (packet instanceof BedrockPacket) {
			if (debug()) {
				String to = connection.getRemoteAddress().orElse(LOOPBACK_BEDROCK).toString();
				log("Outbound " + to + ": " + packet);
			}
			HybridWrappedPacket wrappedPacket = new HybridWrappedPacket();
			wrappedPacket.getPackets().add((BedrockPacket) packet);
			if (packet.getClass().isAnnotationPresent(NoEncryption.class)) {
				wrappedPacket.setEncrypted(true);
			}
			packet = wrappedPacket;
		}

		if (packet instanceof HybridWrappedPacket) {
			HybridWrappedPacket wrappedPacket = (HybridWrappedPacket) packet;
			ByteBuf compressed;
			if (wrappedPacket.getBatched() == null) {
				compressed = wrapperHandler.compressPackets(packetCodec, wrappedPacket.getPackets());
			} else {
				compressed = wrappedPacket.getBatched();
			}

			ByteBuf finalPayload = PooledByteBufAllocator.DEFAULT.directBuffer();
			try {
				if (encryptionCipher == null || wrappedPacket.isEncrypted()) {
					compressed.readerIndex(0);
					finalPayload.writeBytes(compressed);
				} else {
					compressed.readerIndex(0);
					byte[] trailer = generateTrailer(compressed);
					compressed.writeBytes(trailer);

					compressed.readerIndex(0);
					encryptionCipher.cipher(compressed, finalPayload);
				}
			} catch (GeneralSecurityException e) {
				finalPayload.release();
				throw new RuntimeException("Unable to encipher package", e);
			} finally {
				compressed.release();
			}
			wrappedPacket.setPayload(finalPayload);
		}

		if (packet instanceof RakNetPacket) {
			connection.sendPacket((RakNetPacket) packet);
		} else {
			throw new UnsupportedOperationException("Unknown packet type sent");
		}
	}

	public void onTick() {
		if (connection.isClosed()) {
			return;
		}

		sendQueued();
	}

	private void sendQueued() {
		BedrockPacket packet;
		HybridWrappedPacket wrapper = new HybridWrappedPacket();
		while ((packet = currentlyQueued.poll()) != null) {
			if (packet.getClass().isAnnotationPresent(NoEncryption.class)) {
				// We hit a wrappable packet. Send the current wrapper and then send the
				// un-wrappable packet.
				if (!wrapper.getPackets().isEmpty()) {
					internalSendPackage(wrapper);
					wrapper = new HybridWrappedPacket();
				}

				internalSendPackage(packet);

				try {
					// Delay things a tiny bit
					Thread.sleep(1);
				} catch (InterruptedException e) {
					log("§cInterrupted: §f" + e);
				}

				// continue;
			} /*
				 * else if (wrapper.getPackets().size() >= 100) { // Reached a per-batch limit
				 * on packages, send these packages now internalSendPackage(wrapper); wrapper =
				 * new WrappedPacket(); try { // Delay things a tiny bit Thread.sleep(1); }
				 * catch (InterruptedException e) { log("§c Interrupted: §f" + e); } }
				 */

			wrapper.getPackets().add(packet);
		}

		if (!wrapper.getPackets().isEmpty()) {
			internalSendPackage(wrapper);
		}
	}

	void enableEncryption(byte[] secretKey) {
		if (checkForClosed())
			return;

		serverKey = secretKey;
		byte[] iv = Arrays.copyOf(secretKey, 16);
		SecretKey key = new SecretKeySpec(secretKey, "AES");
		try {
			encryptionCipher = NativeCodeFactory.cipher.newInstance();
			decryptionCipher = NativeCodeFactory.cipher.newInstance();

			encryptionCipher.init(true, key, iv);
			decryptionCipher.init(false, key, iv);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException("Unable to initialize ciphers", e);
		}

		connection.setUseOrdering(true);
	}

	private byte[] generateTrailer(ByteBuf buf) {
		VoxelwindHash hash = hashLocal.get();
		ByteBuf counterBuf = PooledByteBufAllocator.DEFAULT.directBuffer(8);
		ByteBuf keyBuf = PooledByteBufAllocator.DEFAULT.directBuffer(serverKey.length);
		try {
			counterBuf.writeLongLE(sentEncryptedPacketCount.getAndIncrement());
			keyBuf.writeBytes(serverKey);

			hash.update(counterBuf);
			hash.update(buf);
			hash.update(keyBuf);
			byte[] digested = hash.digest();
			return Arrays.copyOf(digested, 8);
		} finally {
			counterBuf.release();
			keyBuf.release();
		}
	}

	public boolean isEncrypted() {
		return encryptionCipher != null;
	}

	private void close() {
		if (isDownstreamConnect()) {
			downstreamDisconnect();
		}

		if (!connection.isClosed()) {
			connection.close();
		}

		server.getSessionManager().remove(this);

		if (encryptionCipher != null) {
			encryptionCipher.free();
		}
		if (decryptionCipher != null) {
			decryptionCipher.free();
		}
	}

	public BungeeCipher getEncryptionCipher() {
		return encryptionCipher;
	}

	public BungeeCipher getDecryptionCipher() {
		return decryptionCipher;
	}

	public ClientData getClientData() {
		return clientData;
	}

	public void setClientData(ClientData clientData) {
		this.clientData = clientData;
	}

	public int getProtocolVersion() {
		return protocolVersion;
	}

	void setProtocolVersion(int protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public void disconnect() {
		disconnect(null, true);
	}

	public void disconnect(String reason) {
		disconnect(reason, false);
	}

	public void disconnect(String reason, boolean hideReason) {
		if (checkForClosed())
			return;

		DisconnectPacket packet = new DisconnectPacket();
		if (reason == null || hideReason) {
			packet.setDisconnectScreenHidden(true);
			reason = "disconnect.disconnected";
		} else {
			packet.setKickMessage(reason);
		}
		sendImmediatePackage(packet);

		if (authData != null) {
			log("§f[" + authData.getDisplayName() + "] §cDisconnected from server: §r" + reason);
		} else {
			log("§f[" + getRemoteAddress().map(Object::toString).orElse("UNKNOWN") + "] §cDisconnected from server: §r" + reason);
		}

		close();
	}

	public void onWrappedPacket(HybridWrappedPacket packet) throws Exception {
		if (wrapperHandler == null) {
			return;
		}

		ByteBuf wrappedData = packet.getPayload();
		ByteBuf unwrappedData = null;
		try {
			if (isEncrypted()) {
				// Decryption
				unwrappedData = PooledByteBufAllocator.DEFAULT.directBuffer(wrappedData.readableBytes());
				decryptionCipher.cipher(wrappedData, unwrappedData);
				// TODO: Persuade Microjang into removing adler32
				unwrappedData = unwrappedData.slice(0, unwrappedData.readableBytes() - 8);
			} else {
				// Encryption not enabled so it should be readable.
				unwrappedData = wrappedData;
			}

			String to = getRemoteAddress().orElse(LOOPBACK_BEDROCK).toString();
			// Decompress and handle packets
			for (BedrockPacket pk : wrapperHandler.decompressPackets(packetCodec, unwrappedData)) {
				if (debug()) {
					log("Inbound " + to + ": " + pk.toString());
				}
				pk.handle(handler);
			}
		} finally {
			wrappedData.release();
			if (unwrappedData != null && unwrappedData != wrappedData) {
				unwrappedData.release();
			}
		}
	}

	public boolean isClosed() {
		return connection.isClosed();
	}

	public Optional<InetSocketAddress> getRemoteAddress() {
		return connection.getRemoteAddress();
	}

	public RakNetSession getConnection() {
		return connection;
	}
	
	public HybridServer getServer() {
		return server;
	}

	@Override
	public void onTimeout() {
        if (authData != null) {
			log("§f[" + authData.getDisplayName() + "] §cDisconnected from server: §rTimeout!");
        } else {
			log("§f[" + getRemoteAddress().map(Object::toString).orElse("UNKNOWN") + "] §cDisconnected from server: §rTimeout!");
        }
        close();
	}

	// CONNECTION TO PC

	private DownstreamConnection downstream;

	public DownstreamConnection getDownstream() {
		return downstream;
	}

	public void downstreamConnect() {
		if (downstream != null && downstream.isConnected()) {
			return;
		}
		MinecraftProtocol protocol = new MinecraftProtocol(authData.getDisplayName());
		downstream = new DownstreamConnection(this, protocol, server.pcIp, server.pcPort);
		downstream.connect();
	}

	public void downstreamDisconnect() {
		if (downstream == null) {
			return;
		}
		downstream.disconnect();
		downstream = null;
	}

	public boolean isDownstreamConnect() {
		if (downstream == null) {
			return false;
		}
		return downstream.isConnected();
	}

}
