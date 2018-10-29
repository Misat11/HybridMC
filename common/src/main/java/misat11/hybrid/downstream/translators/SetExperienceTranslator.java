package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class SetExperienceTranslator implements IDownstreamTranslator<ServerPlayerSetExperiencePacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerSetExperiencePacket packet) {
		return null;
	}

}
