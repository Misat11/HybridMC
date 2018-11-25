package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.window.ServerWindowItemsPacket;

public class WindowItemsTranslator implements IDownstreamTranslator<ServerWindowItemsPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerWindowItemsPacket packet) {

		return null;
	}

}
