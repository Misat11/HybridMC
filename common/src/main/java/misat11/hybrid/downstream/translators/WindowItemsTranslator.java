package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerWindowItemsPacket404;

public class WindowItemsTranslator implements IDownstreamTranslator<ServerWindowItemsPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerWindowItemsPacket404 packet) {

		return null;
	}

}
