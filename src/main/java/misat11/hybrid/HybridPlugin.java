package misat11.hybrid;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import misat11.hybrid.config.Configurator;
import misat11.hybrid.network.HybridServer;

public final class HybridPlugin extends JavaPlugin {

	private String version;
	private HybridServer server;
	private Configurator config;

	private static HybridPlugin instance;
	
	public static boolean DEBUG = false;

	public static final int DEFAULT_PORT = 19132;

	@Override
	public void onEnable() {
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
			return;
		}
		if (getServer().getOnlineMode()) {
			log("§cHybridMC doesn't work on online-mode server yet! Please disable it in your server.properties");
			getPluginLoader().disablePlugin(this);
			return;
		}
		getServer().getScheduler().runTask(this, () -> {
			version = getDescription().getVersion();
			log("§aStarting HybridMC version " + version);
			instance = this;
			config = new Configurator(this);
			config.createFiles();
			DEBUG = config.config.getBoolean("debug");
			server = new HybridServer(Bukkit.getIp(), config.config.getInt("port"));
			if (!server.start()) {
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
		instance = null;
	}

	public static void log(String msg) {
		Bukkit.getServer().getConsoleSender().sendMessage(msg);
	}

	public HybridServer getPEServer() {
		return server;
	}

	public String getVersion() {
		return version;
	}

	public Configurator getConfigurator() {
		return config;
	}

	public static HybridPlugin getInstance() {
		return instance;
	}
}
