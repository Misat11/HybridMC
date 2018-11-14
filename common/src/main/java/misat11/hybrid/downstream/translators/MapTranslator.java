package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerMapDataPacket;

public class MapTranslator implements IDownstreamTranslator<ServerMapDataPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerMapDataPacket packet) {
		// Not implemented yet in NukkitX
		return null;
	}

}
