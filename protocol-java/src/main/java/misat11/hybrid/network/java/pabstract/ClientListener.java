package misat11.hybrid.network.java.pabstract;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.InvalidCredentialsException;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.mc.auth.exception.request.ServiceUnavailableException;
import com.github.steveice10.mc.auth.service.SessionService;
import com.github.steveice10.packetlib.event.session.ConnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;

import misat11.hybrid.network.java.pabstract.data.SubProtocol;
import misat11.hybrid.network.java.pabstract.data.handshake.HandshakeIntent;
import misat11.hybrid.network.java.pabstract.data.status.ServerStatusInfo;
import misat11.hybrid.network.java.pabstract.data.status.handler.ServerInfoHandler;
import misat11.hybrid.network.java.pabstract.data.status.handler.ServerPingTimeHandler;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.handshake.client.HandshakePacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientKeepAlivePacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerDisconnectPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerKeepAlivePacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerSetCompressionPacket;
import misat11.hybrid.network.java.pabstract.packet.login.client.LoginStartPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.EncryptionRequestPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.LoginDisconnectPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.LoginSetCompressionPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.LoginSuccessPacket;
import misat11.hybrid.network.java.pabstract.packet.status.client.StatusPingPacket;
import misat11.hybrid.network.java.pabstract.packet.status.client.StatusQueryPacket;
import misat11.hybrid.network.java.pabstract.packet.status.server.StatusPongPacket;
import misat11.hybrid.network.java.pabstract.packet.status.server.StatusResponsePacket;
import misat11.hybrid.network.java.pabstract.util.CryptUtil;

import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.net.Proxy;
import java.security.PublicKey;

public class ClientListener extends SessionAdapter {
	@Override
	public void packetReceived(PacketReceivedEvent event) {
		MinecraftProtocolAbstract protocol = (MinecraftProtocolAbstract) event.getSession().getPacketProtocol();
		if (protocol.getSubProtocol() == SubProtocol.LOGIN) {
			if (event.getPacket() instanceof EncryptionRequestPacket) {
				EncryptionRequestPacket packet = event.getPacket();
				SecretKey key = CryptUtil.generateSharedKey();

				Proxy proxy = event.getSession().<Proxy>getFlag(MinecraftConstants.AUTH_PROXY_KEY);
				if (proxy == null) {
					proxy = Proxy.NO_PROXY;
				}

				GameProfile profile = event.getSession().getFlag(MinecraftConstants.PROFILE_KEY);
				String serverHash = new BigInteger(
						CryptUtil.getServerIdHash(packet.getServerId(), packet.getPublicKey(), key)).toString(16);
				String accessToken = event.getSession().getFlag(MinecraftConstants.ACCESS_TOKEN_KEY);
				try {
					new SessionService(proxy).joinServer(profile, accessToken, serverHash);
				} catch (ServiceUnavailableException e) {
					event.getSession().disconnect("Login failed: Authentication service unavailable.", e);
					return;
				} catch (InvalidCredentialsException e) {
					event.getSession().disconnect("Login failed: Invalid login session.", e);
					return;
				} catch (RequestException e) {
					event.getSession().disconnect("Login failed: Authentication error: " + e.getMessage(), e);
					return;
				}

				try {
					MinecraftPacket encryptionResponse = protocol.generatePacket(EncryptionRequestPacket.class,
							SecretKey.class, key, PublicKey.class, packet.getPublicKey(), byte[].class,
							packet.getVerifyToken());
					event.getSession().send(encryptionResponse);
					protocol.enableEncryption(key);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (event.getPacket() instanceof LoginSuccessPacket) {
				LoginSuccessPacket packet = event.getPacket();
				event.getSession().setFlag(MinecraftConstants.PROFILE_KEY, packet.getProfile());
				protocol.setSubProtocol(SubProtocol.GAME, event.getSession());
			} else if (event.getPacket() instanceof LoginDisconnectPacket) {
				LoginDisconnectPacket packet = event.getPacket();
				event.getSession().disconnect(packet.getReason().getFullText());
			} else if (event.getPacket() instanceof LoginSetCompressionPacket) {
				event.getSession().setCompressionThreshold(event.<LoginSetCompressionPacket>getPacket().getThreshold());
			}
		} else if (protocol.getSubProtocol() == SubProtocol.STATUS) {
			if (event.getPacket() instanceof StatusResponsePacket) {
				ServerStatusInfo info = event.<StatusResponsePacket>getPacket().getInfo();
				ServerInfoHandler handler = event.getSession().getFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY);
				if (handler != null) {
					handler.handle(event.getSession(), info);
				}

				try {
					MinecraftPacket statusPing = protocol.generatePacket(StatusPingPacket.class, long.class,
							System.currentTimeMillis());
					event.getSession().send(statusPing);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (event.getPacket() instanceof StatusPongPacket) {
				long time = System.currentTimeMillis() - event.<StatusPongPacket>getPacket().getPingTime();
				ServerPingTimeHandler handler = event.getSession()
						.getFlag(MinecraftConstants.SERVER_PING_TIME_HANDLER_KEY);
				if (handler != null) {
					handler.handle(event.getSession(), time);
				}

				event.getSession().disconnect("Finished");
			}
		} else if (protocol.getSubProtocol() == SubProtocol.GAME) {
			if (event.getPacket() instanceof ServerKeepAlivePacket) {
				try {
					MinecraftPacket keepAlive = protocol.generatePacket(ClientKeepAlivePacket.class, long.class,
							event.<ServerKeepAlivePacket>getPacket().getPingId());
					event.getSession().send(keepAlive);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (event.getPacket() instanceof ServerDisconnectPacket) {
				event.getSession().disconnect(event.<ServerDisconnectPacket>getPacket().getReason().getFullText());
			} else if (event.getPacket() instanceof ServerSetCompressionPacket) {
				event.getSession()
						.setCompressionThreshold(event.<ServerSetCompressionPacket>getPacket().getThreshold());
			}
		}
	}

	@Override
	public void connected(ConnectedEvent event) {
		try {
			MinecraftProtocolAbstract protocol = (MinecraftProtocolAbstract) event.getSession().getPacketProtocol();
			int protocolVersion = protocol.getProtocolVersion();
			if (protocol.getSubProtocol() == SubProtocol.LOGIN) {
				GameProfile profile = event.getSession().getFlag(MinecraftConstants.PROFILE_KEY);
				protocol.setSubProtocol(SubProtocol.HANDSHAKE, event.getSession());
				event.getSession()
						.send(protocol.generatePacket(HandshakePacket.class, int.class, protocolVersion, String.class,
								event.getSession().getHost(), int.class, event.getSession().getPort(),
								HandshakeIntent.class, HandshakeIntent.LOGIN));
				protocol.setSubProtocol(SubProtocol.LOGIN, event.getSession());
				event.getSession().send(protocol.generatePacket(LoginStartPacket.class, String.class,
						profile != null ? profile.getName() : ""));
			} else if (protocol.getSubProtocol() == SubProtocol.STATUS) {
				protocol.setSubProtocol(SubProtocol.HANDSHAKE, event.getSession());
				event.getSession()
						.send(protocol.generatePacket(HandshakePacket.class, int.class, protocolVersion, String.class,
								event.getSession().getHost(), int.class, event.getSession().getPort(),
								HandshakeIntent.class, HandshakeIntent.STATUS));
				protocol.setSubProtocol(SubProtocol.STATUS, event.getSession());
				event.getSession().send(protocol.generatePacket(StatusQueryPacket.class));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
