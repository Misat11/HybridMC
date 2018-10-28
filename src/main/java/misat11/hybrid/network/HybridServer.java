package misat11.hybrid.network;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;

import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.nukkitx.network.raknet.RakNetServer;
import com.nukkitx.server.NukkitServer;
import com.nukkitx.server.network.bedrock.BedrockPacketCodec;

import misat11.hybrid.HybridPlugin;
import misat11.hybrid.utils.NMSUtil;

import static misat11.hybrid.HybridPlugin.log;

public class HybridServer {
	public final String ip;
	public final int port;
	
	private boolean running;
	private RakNetServer<HybridSession> rakNetServer;
	private HybridSessionManager sessionManager;
	
	private final ScheduledExecutorService timerService = Executors.unconfigurableScheduledExecutorService(
            Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("HybridMC Ticker").setDaemon(true).build()));

	public HybridServer(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public boolean start() {
		log("§aStarting PE server version " + NukkitServer.MINECRAFT_VERSION + " (protocol "
				+ BedrockPacketCodec.BROADCAST_PROTOCOL_VERSION + ")");
		try {
			int serverProtocol = NMSUtil.getServerProtocolVersion();
			if (serverProtocol != MinecraftConstants.PROTOCOL_VERSION) {
				log("§cYour server version is not supported!");
			}
			sessionManager = new HybridSessionManager();
	        int configNetThreads = HybridPlugin.getInstance().getConfigurator().config.getInt("networkthreads");
	        int maxThreads = configNetThreads < 1 ? Runtime.getRuntime().availableProcessors() : configNetThreads;
	        rakNetServer = RakNetServer.<HybridSession>builder()
	                .address(ip, port)
	                .eventListener(new HybridRakNetEventListener())
	                .packet(HybridWrappedPacket::new, 0xfe)
	                .id(12345)
	                .maximumThreads(maxThreads)
	                .sessionFactory((connection) -> new HybridSession(this, connection))
	                .sessionManager(sessionManager)
	                .build();
	        if (!rakNetServer.bind()) {
	            log("§cUnable to bind RakNet listener!");
	            return false;
	        }
	        timerService.scheduleAtFixedRate(sessionManager::onTick, 50, 50, TimeUnit.MILLISECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void stop() {
		if (!isRunning()) {
			return;
		}
		Bukkit.getLogger().info("Stopping PE server...");
		timerService.shutdown();
		rakNetServer.close();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public HybridSessionManager getSessionManager() {
		return sessionManager;
	}
}
