package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlayEffectPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class WorldEventTranslator implements IDownstreamTranslator<ServerPlayEffectPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayEffectPacket packet) {
		return null;
	}

}
