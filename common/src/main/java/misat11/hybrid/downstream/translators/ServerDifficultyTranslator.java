package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetDifficultyPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDifficultyPacket;

public class ServerDifficultyTranslator implements IDownstreamTranslator<ServerDifficultyPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerDifficultyPacket packet) {
		SetDifficultyPacket sdp = new SetDifficultyPacket();
		sdp.setDifficulty(packet.getDifficulty().ordinal());
		return new BedrockPacket[] {sdp};
	}

}
