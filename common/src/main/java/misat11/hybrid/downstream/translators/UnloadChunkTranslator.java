package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUnloadChunkPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class UnloadChunkTranslator implements IDownstreamTranslator<ServerUnloadChunkPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerUnloadChunkPacket packet) {
		session.getDownstream().getChunkCache().unmarkSent(packet.getX(), packet.getZ());
		return null;
	}

}
