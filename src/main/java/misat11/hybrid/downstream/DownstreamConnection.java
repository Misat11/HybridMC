package misat11.hybrid.downstream;

import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.event.session.ConnectedEvent;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.DisconnectingEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.PacketSendingEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;
import com.github.steveice10.packetlib.packet.Packet;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;

import misat11.hybrid.network.HybridSession;

import static misat11.hybrid.HybridPlugin.log;

public class DownstreamConnection {

	private final MinecraftProtocol protocol;
	private final HybridSession session;
	private final String ip;
	private final int port;
	private Client remoteClient;

	public DownstreamConnection(HybridSession session, MinecraftProtocol protocol, String ip, int port) {
		this.session = session;
		this.protocol = protocol;
		if (ip.equals("0.0.0.0") || ip.equals("")) {
			ip = "127.0.0.1";
		}
		this.ip = ip;
		this.port = port;
	}

	public void connect() {
		log("§aConnecting to downstream server... (" + ip + ":" + port + ")");

		if (this.protocol == null) {
			session.disconnect("ERROR!");
			return;
		}
		remoteClient = new Client(ip, port, protocol, new TcpSessionFactory());
		remoteClient.getSession().setConnectTimeout(5);
		remoteClient.getSession().setReadTimeout(5);
		remoteClient.getSession().setWriteTimeout(5);
		remoteClient.getSession().addListener(new SessionAdapter() {

			@Override
			public void connected(ConnectedEvent event) {
				// onConnected
			}

			@Override
			public void packetSending(PacketSendingEvent event) {

			}

			@Override
			public void disconnected(DisconnectedEvent event) {
				session.disconnect(event.getReason());
			}

			@Override
			public void disconnecting(DisconnectingEvent event) {
				session.disconnect(event.getReason());
			}

			@Override
			public void packetReceived(PacketReceivedEvent event) {
				// Handle the packet
			}
		});
		remoteClient.getSession().connect();
	}

	public void disconnect() {
        if (isConnected()) {
            remoteClient.getSession().disconnect("Disconnect");
        }
	}

	public boolean isConnected() {
        return remoteClient != null && remoteClient.getSession().isConnected();
	}

	public void send(Packet... packets) {
		for (Packet p : packets)
			send(p);
	}

    public void sendChat(String chat) {
        remoteClient.getSession().send(new ClientChatPacket(chat));
    }

	public void send(Packet packet) {
        if (packet == null) {
            return;
        }
        remoteClient.getSession().send(packet);
	}

	public HybridSession getSession() {
		return session;
	}

	public MinecraftProtocol getMinecraftProtocol() {
		return protocol;
	}
}
