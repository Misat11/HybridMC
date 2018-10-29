package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ChunkTranslator implements IDownstreamTranslator<ServerChunkDataPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerChunkDataPacket packet) {
		return null;
	}

}
