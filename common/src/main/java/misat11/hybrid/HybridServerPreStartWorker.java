package misat11.hybrid;

import misat11.hybrid.network.java.JavaProtocolBuilder;

public interface HybridServerPreStartWorker {
	public void select(HybridServer server, JavaProtocolBuilder.MinecraftProtocolInfo javaProtocolInfo) throws Exception;
}
