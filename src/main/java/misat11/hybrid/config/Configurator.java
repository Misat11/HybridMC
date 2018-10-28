package misat11.hybrid.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import misat11.hybrid.HybridPlugin;

public class Configurator {

	public File configf;
	public FileConfiguration config;

	public final File datafolder;
	public final HybridPlugin plugin;

	public Configurator(HybridPlugin plugin) {
		this.datafolder = plugin.getDataFolder();
		this.plugin = plugin;
	}

	public void createFiles() {

		configf = new File(datafolder, "config.yml");

		if (!configf.exists()) {
			configf.getParentFile().mkdirs();
			plugin.saveResource("config.yml", false);
		}
		config = new YamlConfiguration();
		try {
			config.load(configf);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}

		boolean modified = false;
		if (!config.isSet("port")) {
			config.set("port", HybridPlugin.DEFAULT_PORT);
			modified = true;
		}
		if (!config.isSet("motd")) {
			config.set("motd", "Minecraft Server");
			modified = true;
		}
		if (!config.isSet("submotd")) {
			config.set("submotd", "Server by HybridMC");
			modified = true;
		}
		if (!config.isSet("networkthreads")) {
			config.set("networkthreads", 8);
			modified = true;
		}
		if (!config.isSet("xbox")) {
			config.set("xbox", true);
			modified = true;
		}
		if (!config.isSet("debug")) {
			config.set("debug", false);
			modified = true;
		}
		if (modified) {
			try {
				config.save(configf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
