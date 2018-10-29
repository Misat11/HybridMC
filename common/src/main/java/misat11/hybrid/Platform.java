package misat11.hybrid;

public final class Platform {

	private static IPlatform platform;

	public static final int DEFAULT_PORT = 19132;

	static void initPlatform(IPlatform platform) {
		Platform.platform = platform;
	}

	public static IPlatform getPlatform() {
		return platform;
	}

	public static void log(String message) {
		if (platform != null)
			platform.log(message);
	}

	public static boolean xbox() {
		if (platform != null) 
			return platform.xbox();
		return false;
	}

	public static boolean debug() {
		if (platform != null) 
			return platform.debug();
		return false;
	}
	
	public static int getOnlinePlayers() {
		if (platform != null) 
			return platform.getOnlinePlayers();
		return 0;
	}
	
	public static int getMaxPlayers() {
		if (platform != null) 
			return platform.getMaxPlayers();
		return 0;
	}
	
	public static String getMotd() {
		if (platform != null) 
			return platform.getMotd();
		return "";
	}
	
	public static String getSubmotd() {
		if (platform != null) 
			return platform.getSubmotd();
		return "";
	}
}
