package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class StartGameTranslator implements IDownstreamTranslator<ServerJoinGamePacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerJoinGamePacket packet) {
		return null;
	}

}
