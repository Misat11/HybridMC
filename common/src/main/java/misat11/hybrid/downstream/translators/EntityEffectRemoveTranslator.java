package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityRemoveEffectPacket404;

public class EntityEffectRemoveTranslator implements IDownstreamTranslator<ServerEntityRemoveEffectPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityRemoveEffectPacket404 packet) {
		return null;
	}

}
