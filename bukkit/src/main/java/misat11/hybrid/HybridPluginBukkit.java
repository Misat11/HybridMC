package misat11.hybrid;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import misat11.hybrid.utils.NMSUtil;
import misat11.hybrid.HybridServer;
import misat11.hybrid.blockitems.flattening.CraftBukkitFlatteningBlockData;
import misat11.hybrid.blockitems.flattening.CraftBukkitFlatteningItemData;
import misat11.hybrid.blockitems.flattening.FlatteningBlockTranslator;
import misat11.hybrid.blockitems.flattening.FlatteningItemTranslator;
import misat11.hybrid.blockitems.legacy.LegacyItemBlockTranslator;
import misat11.hybrid.network.java.JavaProtocolBuilder.VersionType;

public final class HybridPluginBukkit extends JavaPlugin implements IPlatform {

	private String version;
	private HybridServer server;

	@Override
	public void onEnable() {
		if (Float.parseFloat(System.getProperty("java.class.version")) < 52.0) {
			log("§c*** ERROR *** Outdated Java detected! HybridMC requires at least Java 8. Disabling plugin!");
			getPluginLoader().disablePlugin(this);
			return;
		}
		boolean isNMS;
		try {
			Class.forName("org.bukkit.craftbukkit.Main");
			isNMS = true;
		} catch (ClassNotFoundException e) {
			isNMS = false;
		}
		if (!isNMS) {
			log("§cHybridMC doesn't work on non-NMS server yet! Please use Craftbukkit, Spigot or forks!");
			getPluginLoader().disablePlugin(this);
			// TODO add glowstone support
			return;
		}
		if (getServer().getOnlineMode()) {
			log("§cHybridMC doesn't work on online-mode server yet! Please disable it in your server.properties");
			getPluginLoader().disablePlugin(this);
			return;
		}
		Metrics metrics = new Metrics(this);
		saveDefaultConfig();
		getServer().getScheduler().runTask(this, () -> {
			try {
				version = getDescription().getVersion();
				log("§aStarting HybridMC version " + version);
				Platform.initPlatform(this);
				server = new HybridServer(getServer().getIp(), getConfig().getInt("port"), getServer().getIp(),
						getServer().getPort(), NMSUtil.getServerProtocolVersion(), getConfig().getInt("networkthreads"),
						(hybridServer, javaProtocolInfo) -> {
							if (javaProtocolInfo.getVersionType() == VersionType.FLATTENING) {
								CraftBukkitFlatteningBlockData blockData = new CraftBukkitFlatteningBlockData();
								CraftBukkitFlatteningItemData itemData = new CraftBukkitFlatteningItemData();
								hybridServer.setBlockTranslator(new FlatteningBlockTranslator(blockData));
								hybridServer.setItemTranslator(new FlatteningItemTranslator(itemData));
							} else {
								LegacyItemBlockTranslator legacy = new LegacyItemBlockTranslator();
								hybridServer.setBlockTranslator(legacy);
								hybridServer.setItemTranslator(legacy);
							}
						});
				if (!server.start()) {
					log("§cAn error occurred while starting server! Disabling...");
					getPluginLoader().disablePlugin(this);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log("§cAn error occurred while starting server! Disabling...");
				getPluginLoader().disablePlugin(this);
			}
		});
	}

	@Override
	public void onDisable() {
		if (server != null) {
			server.stop();
			server = null;
		}
		Platform.initPlatform(null);
	}

	public HybridServer getPEServer() {
		return server;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public void log(String message) {
		getServer().getConsoleSender().sendMessage(message);

	}

	@Override
	public boolean xbox() {
		return getConfig().getBoolean("xbox");
	}

	@Override
	public boolean debug() {
		return getConfig().getBoolean("debug");
	}

	@Override
	public int getOnlinePlayers() {
		return getServer().getOnlinePlayers().size();
	}

	@Override
	public int getMaxPlayers() {
		return getServer().getMaxPlayers();
	}

	@Override
	public String getMotd() {
		return getConfig().getString("motd");
	}

	@Override
	public String getSubmotd() {
		return getConfig().getString("submotd");
	}
}
