package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerTitlePacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class TitleTranslator implements IDownstreamTranslator<ServerTitlePacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerTitlePacket packet) {
		return null;
	}

}
