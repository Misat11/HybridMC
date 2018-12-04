package misat11.hybrid.network.java;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import misat11.hybrid.network.java.p393.MinecraftConstants393;
import misat11.hybrid.network.java.p393.MinecraftProtocol393;
import misat11.hybrid.network.java.p401.MinecraftConstants401;
import misat11.hybrid.network.java.p401.MinecraftProtocol401;
import misat11.hybrid.network.java.p404.MinecraftConstants404;
import misat11.hybrid.network.java.p404.MinecraftProtocol404;
import misat11.hybrid.network.java.pabstract.MinecraftProtocolAbstract;

public class JavaProtocolBuilder {
	private static final Map<Integer, MinecraftProtocolInfo> protocols = new HashMap<>();

	static {
		// 1.13.2
		new MinecraftProtocolInfo(MinecraftConstants404.PROTOCOL_VERSION, MinecraftConstants404.GAME_VERSION,
				MinecraftProtocol404.class, VersionType.FLATTENING);
		// 1.13.1
		new MinecraftProtocolInfo(MinecraftConstants401.PROTOCOL_VERSION, MinecraftConstants401.GAME_VERSION,
				MinecraftProtocol401.class, VersionType.FLATTENING);
		// 1.13
		new MinecraftProtocolInfo(MinecraftConstants393.PROTOCOL_VERSION, MinecraftConstants393.GAME_VERSION,
				MinecraftProtocol393.class, VersionType.FLATTENING);
		// 1.12.2
		// TODO
		// 1.12.1
		// TODO
		// 1.12
		// TODO
		// 1.11.1/2
		// TODO
		// 1.11
		// TODO
		// 1.10.x
		// TODO
		// 1.9.3/4
		// TODO
		// 1.9.2
		// TODO
		// 1.9.1
		// TODO
		// 1.9
		// TODO
		// 1.8.x
		// TODO
	}

	public static MinecraftProtocolInfo getProtocol(int protocolVersion) {
		if (protocols.containsKey(protocolVersion)) {
			return protocols.get(protocolVersion);
		}
		return null;
	}

	@Getter
	public static class MinecraftProtocolInfo {
		private final int protocolVersion;
		private final String minecraftVersionName;
		private final Class<? extends MinecraftProtocolAbstract> minecraftProtocol;
		private final VersionType versionType;

		private MinecraftProtocolInfo(int protocolVersion, String minecraftVersionName,
				Class<? extends MinecraftProtocolAbstract> minecraftProtocol, VersionType versionType) {
			this.protocolVersion = protocolVersion;
			this.minecraftVersionName = minecraftVersionName;
			this.minecraftProtocol = minecraftProtocol;
			this.versionType = versionType;
			JavaProtocolBuilder.protocols.put(this.protocolVersion, this);
		}

		public MinecraftProtocolAbstract newInstance(String username)
				throws InstantiationException, IllegalAccessException, IllegalArgumentException,
				InvocationTargetException, NoSuchMethodException, SecurityException {
			return minecraftProtocol.getDeclaredConstructor(String.class).newInstance(username);
		}
	}

	public enum VersionType {
		LEGACY, FLATTENING;
	}
}
