package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlayBuiltinSoundPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class WorldSoundTranslator implements IDownstreamTranslator<ServerPlayBuiltinSoundPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayBuiltinSoundPacket packet) {
		return null;
	}

}
