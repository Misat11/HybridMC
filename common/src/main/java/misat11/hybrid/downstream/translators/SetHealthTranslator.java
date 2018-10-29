package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerHealthPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class SetHealthTranslator implements IDownstreamTranslator<ServerPlayerHealthPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerHealthPacket packet) {
		return null;
	}

}
