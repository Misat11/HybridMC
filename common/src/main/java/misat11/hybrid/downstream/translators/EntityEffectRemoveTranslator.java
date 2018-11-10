package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityRemoveEffectPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityEffectRemoveTranslator implements IDownstreamTranslator<ServerEntityRemoveEffectPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityRemoveEffectPacket packet) {
		return null;
	}

}
