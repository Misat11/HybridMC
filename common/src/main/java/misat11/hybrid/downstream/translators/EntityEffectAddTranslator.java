package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityEffectPacket404;

public class EntityEffectAddTranslator implements IDownstreamTranslator<ServerEntityEffectPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityEffectPacket404 packet) {
		return null;
	}

}
