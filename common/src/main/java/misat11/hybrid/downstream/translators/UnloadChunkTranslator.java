package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerUnloadChunkPacket;

public class UnloadChunkTranslator implements IDownstreamTranslator<ServerUnloadChunkPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerUnloadChunkPacket packet) {
		session.getDownstream().getChunkCache().unmarkSent(packet.getX(), packet.getZ());
		return null;
	}

}
