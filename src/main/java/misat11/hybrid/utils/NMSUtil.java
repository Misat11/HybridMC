package misat11.hybrid.utils;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;

public class NMSUtil {
	private static String BASE = Bukkit.getServer().getClass().getPackage().getName();
	private static String NMS = BASE.replace("org.bukkit.craftbukkit", "net.minecraft.server");

	public static Class<?> nms(String className) throws ClassNotFoundException {
		return Class.forName(NMS + "." + className);
	}

	public static Class<?> obc(String className) throws ClassNotFoundException {
		return Class.forName(BASE + "." + className);
	}

	public static String getVersion() {
		return BASE.substring(BASE.lastIndexOf('.') + 1);
	}

	public static int getServerProtocolVersion() throws Exception {
		try {
			Class<?> serverClazz = NMSUtil.nms("MinecraftServer");
			Object server = ReflectionUtil.invokeStatic(serverClazz, "getServer");
			Class<?> pingClazz = NMSUtil.nms("ServerPing");
			Object ping = null;
			// Search for ping method
			for (Field f : serverClazz.getDeclaredFields()) {
				if (f.getType() != null) {
					if (f.getType().getSimpleName().equals("ServerPing")) {
						f.setAccessible(true);
						ping = f.get(server);
					}
				}
			}
			if (ping == null) {
				throw new Exception("Ping cannot be null");
			}
			Object serverData = null;
			for (Field f : pingClazz.getDeclaredFields()) {
				if (f.getType() != null) {
					if (f.getType().getSimpleName().endsWith("ServerData")) {
						f.setAccessible(true);
						serverData = f.get(ping);
					}
				}
			}
			if (serverData == null) {
				throw new Exception("ServerData cannot be null");
			}
			int protocolVersion = -1;
			for (Field f : serverData.getClass().getDeclaredFields()) {
				if (f.getType() != null) {
					if (f.getType() == int.class) {
						f.setAccessible(true);
						protocolVersion = (int) f.get(serverData);
					}
				}
			}
			if (protocolVersion == -1) {
				throw new Exception("Invalid protocol version");
			}
			return protocolVersion;
		} catch (Exception e) {
			throw new Exception("Failed to get server", e);
		}
	}
}