package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerMapDataPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class MapTranslator implements IDownstreamTranslator<ServerMapDataPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerMapDataPacket packet) {
		// Not implemented yet in NukkitX
		return null;
	}

}
