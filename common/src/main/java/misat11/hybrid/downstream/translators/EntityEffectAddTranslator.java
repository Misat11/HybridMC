package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityEffectPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityEffectAddTranslator implements IDownstreamTranslator<ServerEntityEffectPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityEffectPacket packet) {
		return null;
	}

}
