package misat11.hybrid;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.nukkitx.network.raknet.RakNetServer;
import com.nukkitx.server.NukkitServer;
import com.nukkitx.server.network.bedrock.BedrockPacketCodec;

import misat11.hybrid.blockitems.IBlockTranslator;
import misat11.hybrid.blockitems.IItemTranslator;
import misat11.hybrid.network.HybridRakNetEventListener;
import misat11.hybrid.network.HybridSessionManager;
import misat11.hybrid.network.bedrock.packet.HybridWrappedPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

import static misat11.hybrid.Platform.log;

public class HybridServer {
	
	public static final int FLATTENING_FIRST_VERSION = 393; // 1.13
	
	public final String peIp;
	public final int pePort;
	public final String pcIp;
	public final int pcPort;
	public final int pcProtocolVersion;
	public final int networkthreads;
	public final HybridServerPreStartWorker selector;
	
	private boolean running;
	private RakNetServer<HybridSession> rakNetServer;
	private HybridSessionManager sessionManager;
	private IItemTranslator<?> itemTranslator;
	private IBlockTranslator<?> blockTranslator;
	
	private final ScheduledExecutorService timerService = Executors.unconfigurableScheduledExecutorService(
            Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("HybridMC Ticker").setDaemon(true).build()));

	public HybridServer(String peIp, int pePort, String pcIp, int pcPort, int pcProtocolVersion, int networkthreads, HybridServerPreStartWorker selector) {
		this.peIp = peIp;
		this.pePort = pePort;
		this.pcIp = pcIp;
		this.pcPort = pcPort;
		this.pcProtocolVersion = pcProtocolVersion;
		this.networkthreads = networkthreads;
		this.selector = selector;
	}

	public boolean start() {
		log("§aStarting PE server version " + NukkitServer.MINECRAFT_VERSION + " (protocol "
				+ BedrockPacketCodec.BROADCAST_PROTOCOL_VERSION + ")");
		try {
			if (pcProtocolVersion != MinecraftConstants.PROTOCOL_VERSION) {
				log("§cYour server version is not supported!");
			}
			selector.select(this, pcProtocolVersion);
			sessionManager = new HybridSessionManager();
	        int configNetThreads = networkthreads;
	        int maxThreads = configNetThreads < 1 ? Runtime.getRuntime().availableProcessors() : configNetThreads;
	        rakNetServer = RakNetServer.<HybridSession>builder()
	                .address(peIp, pePort)
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
	        running = true;
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
		log("§cStopping PE server...");
		timerService.shutdown();
		rakNetServer.close();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public HybridSessionManager getSessionManager() {
		return sessionManager;
	}
	
	public IItemTranslator<?> getItemTranslator(){
		return itemTranslator;
	}
	
	public IBlockTranslator<?> getBlockTranslator(){
		return blockTranslator;
	}
	
	public void setItemTranslator(IItemTranslator<?> translator) {
		if (itemTranslator == null) {
			itemTranslator = translator;
		}
	}
	
	public void setBlockTranslator(IBlockTranslator<?> translator) {
		if (blockTranslator == null) {
			blockTranslator = translator;
		}
	}
}
