package misat11.hybrid.downstream;

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
import misat11.hybrid.downstream.cache.InventoryCache;
import misat11.hybrid.downstream.cache.JukeboxCache;
import misat11.hybrid.downstream.cache.MovementCache;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.MinecraftProtocol404;
import misat11.hybrid.network.java.p404.data.game.PlayerListEntry;
import misat11.hybrid.network.java.p404.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientChatPacket;

import static misat11.hybrid.Platform.log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DownstreamConnection {

	private final MinecraftProtocol404 protocol;
	private final HybridSession session;
	private final String ip;
	private final int port;
	private Client remoteClient;
	
	private ChunkSentCache cache;
	private MovementCache move;
	private InventoryCache inventory;
	private JukeboxCache jukebox;
	private HashMap<UUID, PlayerListEntry> listEntryCache = new HashMap<>();
	private HashMap<Long, WatchedEntity> watchedEntities = new HashMap<>();
	
	public boolean switchFakePos;
	public long playerEntityId;
	public GameMode gamemode = GameMode.SURVIVAL;

	public DownstreamConnection(HybridSession session, MinecraftProtocol404 protocol, String ip, int port) {
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
		move = new MovementCache();
		inventory = new InventoryCache();
		jukebox = new JukeboxCache();
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

	public MinecraftProtocol404 getMinecraftProtocol() {
		return protocol;
	}
	
	public ChunkSentCache getChunkCache() {
		return cache;
	}
	
	public MovementCache getMovementCache() {
		return move;
	}
	
	public InventoryCache getInventoryCache() {
		return inventory;
	}
	
	public JukeboxCache getJukeboxCache() {
		return jukebox;
	}
	
	public Map<UUID, PlayerListEntry> getPlayerListEntryCache() {
		return listEntryCache;
	}
	
	public Map<Long, WatchedEntity> getWatchedEntities(){
		return watchedEntities;
	}
}
