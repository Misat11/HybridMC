package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetDifficultyPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDifficultyPacket404;

public class ServerDifficultyTranslator implements IDownstreamTranslator<ServerDifficultyPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerDifficultyPacket404 packet) {
		SetDifficultyPacket sdp = new SetDifficultyPacket();
		sdp.setDifficulty(packet.getDifficulty().ordinal());
		return new BedrockPacket[] {sdp};
	}

}
