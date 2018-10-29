package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ChatTranslator implements IDownstreamTranslator<ServerChatPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerChatPacket packet) {
		return null;
	}

}
