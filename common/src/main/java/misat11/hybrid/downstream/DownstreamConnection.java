package misat11.hybrid.downstream;

import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.data.game.PlayerListEntry;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
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

import misat11.hybrid.downstream.cache.ChunkSentCache;
import misat11.hybrid.network.bedrock.session.HybridSession;

import static misat11.hybrid.Platform.log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DownstreamConnection {

	private final MinecraftProtocol protocol;
	private final HybridSession session;
	private final String ip;
	private final int port;
	private Client remoteClient;
	
	private ChunkSentCache cache;
	private HashMap<UUID, PlayerListEntry> listEntryCache = new HashMap<>();
	private HashMap<Long, WatchedEntity> watchedEntities = new HashMap<>();
	
	public boolean switchFakePos;
	public long playerEntityId;
	public GameMode gamemode = GameMode.SURVIVAL;

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
		if (this.protocol == null) {
			session.disconnect("ERROR!");
			return;
		}

		log("§f[" + this.protocol.getProfile().getName() + "] §aConnecting to downstream server... (" + ip + ":" + port
				+ ")");

		cache = new ChunkSentCache();
		remoteClient = new Client(ip, port, protocol, new TcpSessionFactory());
		remoteClient.getSession().setConnectTimeout(5);
		remoteClient.getSession().setReadTimeout(5);
		remoteClient.getSession().setWriteTimeout(5);
		remoteClient.getSession().addListener(new SessionAdapter() {

			@Override
			public void connected(ConnectedEvent event) {
				log("§f[" + DownstreamConnection.this.protocol.getProfile().getName()
						+ "] §aDownstream server connected!");
			}

			@Override
			public void packetSending(PacketSendingEvent event) {

			}

			@Override
			public void disconnected(DisconnectedEvent event) {
				log("§f[" + DownstreamConnection.this.protocol.getProfile().getName()
						+ "] §cDisconnected from downstream!");
				session.disconnect(event.getReason());
				event.getCause().printStackTrace();
			}

			@Override
			public void disconnecting(DisconnectingEvent event) {
				event.getCause().printStackTrace();
			}

			@Override
			public void packetReceived(PacketReceivedEvent event) {
				DownstreamTranslatorRegister.translate(session, event.getPacket());
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
	
	public ChunkSentCache getChunkCache() {
		return cache;
	}
	
	public Map<UUID, PlayerListEntry> getPlayerListEntryCache() {
		return listEntryCache;
	}
	
	public Map<Long, WatchedEntity> getWatchedEntities(){
		return watchedEntities;
	}
}
