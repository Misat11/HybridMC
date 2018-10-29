package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlaySoundPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class WorldCustomSoundTranslator implements IDownstreamTranslator<ServerPlaySoundPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlaySoundPacket packet) {
		return null;
	}

}
