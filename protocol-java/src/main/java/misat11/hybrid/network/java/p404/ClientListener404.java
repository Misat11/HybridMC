package misat11.hybrid.network.java.p404;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.InvalidCredentialsException;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.mc.auth.exception.request.ServiceUnavailableException;
import com.github.steveice10.mc.auth.service.SessionService;
import com.github.steveice10.packetlib.event.session.ConnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;

import misat11.hybrid.network.java.p404.packet.handshake.client.HandshakePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientKeepAlivePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDisconnectPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerKeepAlivePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerSetCompressionPacket;
import misat11.hybrid.network.java.p404.packet.login.client.EncryptionResponsePacket;
import misat11.hybrid.network.java.p404.packet.login.client.LoginStartPacket;
import misat11.hybrid.network.java.p404.packet.login.server.EncryptionRequestPacket;
import misat11.hybrid.network.java.p404.packet.login.server.LoginDisconnectPacket;
import misat11.hybrid.network.java.p404.packet.login.server.LoginSetCompressionPacket;
import misat11.hybrid.network.java.p404.packet.login.server.LoginSuccessPacket;
import misat11.hybrid.network.java.p404.packet.status.client.StatusPingPacket404;
import misat11.hybrid.network.java.p404.packet.status.client.StatusQueryPacket404;
import misat11.hybrid.network.java.p404.packet.status.server.StatusPongPacket404;
import misat11.hybrid.network.java.p404.packet.status.server.StatusResponsePacket404;
import misat11.hybrid.network.java.pabstract.MinecraftConstants;
import misat11.hybrid.network.java.pabstract.data.SubProtocol;
import misat11.hybrid.network.java.pabstract.data.handshake.HandshakeIntent;
import misat11.hybrid.network.java.pabstract.data.status.ServerStatusInfo;
import misat11.hybrid.network.java.pabstract.data.status.handler.ServerInfoHandler;
import misat11.hybrid.network.java.pabstract.data.status.handler.ServerPingTimeHandler;
import misat11.hybrid.network.java.pabstract.util.CryptUtil;

import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.net.Proxy;

public class ClientListener404 extends SessionAdapter {
    @Override
    public void packetReceived(PacketReceivedEvent event) {
        MinecraftProtocol404 protocol = (MinecraftProtocol404) event.getSession().getPacketProtocol();
        if(protocol.getSubProtocol() == SubProtocol.LOGIN) {
            if(event.getPacket() instanceof EncryptionRequestPacket) {
                EncryptionRequestPacket packet = event.getPacket();
                SecretKey key = CryptUtil.generateSharedKey();

                Proxy proxy = event.getSession().<Proxy>getFlag(MinecraftConstants.AUTH_PROXY_KEY);
                if(proxy == null) {
                    proxy = Proxy.NO_PROXY;
                }

                GameProfile profile = event.getSession().getFlag(MinecraftConstants.PROFILE_KEY);
                String serverHash = new BigInteger(CryptUtil.getServerIdHash(packet.getServerId(), packet.getPublicKey(), key)).toString(16);
                String accessToken = event.getSession().getFlag(MinecraftConstants.ACCESS_TOKEN_KEY);
                try {
                    new SessionService(proxy).joinServer(profile, accessToken, serverHash);
                } catch(ServiceUnavailableException e) {
                    event.getSession().disconnect("Login failed: Authentication service unavailable.", e);
                    return;
                } catch(InvalidCredentialsException e) {
                    event.getSession().disconnect("Login failed: Invalid login session.", e);
                    return;
                } catch(RequestException e) {
                    event.getSession().disconnect("Login failed: Authentication error: " + e.getMessage(), e);
                    return;
                }

                event.getSession().send(new EncryptionResponsePacket(key, packet.getPublicKey(), packet.getVerifyToken()));
                protocol.enableEncryption(key);
            } else if(event.getPacket() instanceof LoginSuccessPacket) {
                LoginSuccessPacket packet = event.getPacket();
                event.getSession().setFlag(MinecraftConstants.PROFILE_KEY, packet.getProfile());
                protocol.setSubProtocol(SubProtocol.GAME, event.getSession());
            } else if(event.getPacket() instanceof LoginDisconnectPacket) {
                LoginDisconnectPacket packet = event.getPacket();
                event.getSession().disconnect(packet.getReason().getFullText());
            } else if(event.getPacket() instanceof LoginSetCompressionPacket) {
                event.getSession().setCompressionThreshold(event.<LoginSetCompressionPacket>getPacket().getThreshold());
            }
        } else if(protocol.getSubProtocol() == SubProtocol.STATUS) {
            if(event.getPacket() instanceof StatusResponsePacket404) {
                ServerStatusInfo info = event.<StatusResponsePacket404>getPacket().getInfo();
                ServerInfoHandler handler = event.getSession().getFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY);
                if(handler != null) {
                    handler.handle(event.getSession(), info);
                }

                event.getSession().send(new StatusPingPacket404(System.currentTimeMillis()));
            } else if(event.getPacket() instanceof StatusPongPacket404) {
                long time = System.currentTimeMillis() - event.<StatusPongPacket404>getPacket().getPingTime();
                ServerPingTimeHandler handler = event.getSession().getFlag(MinecraftConstants.SERVER_PING_TIME_HANDLER_KEY);
                if(handler != null) {
                    handler.handle(event.getSession(), time);
                }

                event.getSession().disconnect("Finished");
            }
        } else if(protocol.getSubProtocol() == SubProtocol.GAME) {
            if(event.getPacket() instanceof ServerKeepAlivePacket) {
                event.getSession().send(new ClientKeepAlivePacket(event.<ServerKeepAlivePacket>getPacket().getPingId()));
            } else if(event.getPacket() instanceof ServerDisconnectPacket) {
                event.getSession().disconnect(event.<ServerDisconnectPacket>getPacket().getReason().getFullText());
            } else if(event.getPacket() instanceof ServerSetCompressionPacket) {
                event.getSession().setCompressionThreshold(event.<ServerSetCompressionPacket>getPacket().getThreshold());
            }
        }
    }

    @Override
    public void connected(ConnectedEvent event) {
        MinecraftProtocol404 protocol = (MinecraftProtocol404) event.getSession().getPacketProtocol();
        if(protocol.getSubProtocol() == SubProtocol.LOGIN) {
            GameProfile profile = event.getSession().getFlag(MinecraftConstants.PROFILE_KEY);
            protocol.setSubProtocol(SubProtocol.HANDSHAKE, event.getSession());
            event.getSession().send(new HandshakePacket(MinecraftConstants404.PROTOCOL_VERSION, event.getSession().getHost(), event.getSession().getPort(), HandshakeIntent.LOGIN));
            protocol.setSubProtocol(SubProtocol.LOGIN, event.getSession());
            event.getSession().send(new LoginStartPacket(profile != null ? profile.getName() : ""));
        } else if(protocol.getSubProtocol() == SubProtocol.STATUS) {
            protocol.setSubProtocol(SubProtocol.HANDSHAKE, event.getSession());
            event.getSession().send(new HandshakePacket(MinecraftConstants404.PROTOCOL_VERSION, event.getSession().getHost(), event.getSession().getPort(), HandshakeIntent.STATUS));
            protocol.setSubProtocol(SubProtocol.STATUS, event.getSession());
            event.getSession().send(new StatusQueryPacket404());
        }
    }
}
