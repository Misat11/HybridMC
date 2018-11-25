package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityRemoveEffectPacket;

public class EntityEffectRemoveTranslator implements IDownstreamTranslator<ServerEntityRemoveEffectPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityRemoveEffectPacket packet) {
		return null;
	}

}
