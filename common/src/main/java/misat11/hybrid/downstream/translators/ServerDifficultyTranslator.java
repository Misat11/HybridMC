package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerDifficultyPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ServerDifficultyTranslator implements IDownstreamTranslator<ServerDifficultyPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerDifficultyPacket packet) {
		return null;
	}

}
