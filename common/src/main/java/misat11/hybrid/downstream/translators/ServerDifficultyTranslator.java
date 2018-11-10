package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerDifficultyPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetDifficultyPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ServerDifficultyTranslator implements IDownstreamTranslator<ServerDifficultyPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerDifficultyPacket packet) {
		SetDifficultyPacket sdp = new SetDifficultyPacket();
		sdp.setDifficulty(packet.getDifficulty().ordinal());
		return new BedrockPacket[] {sdp};
	}

}
