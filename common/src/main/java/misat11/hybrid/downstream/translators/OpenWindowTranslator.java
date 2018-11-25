package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerOpenWindowPacket404;

public class OpenWindowTranslator implements IDownstreamTranslator<ServerOpenWindowPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerOpenWindowPacket404 packet) {
		session.getDownstream().getInventoryCache().open(session, packet);
		return null;
	}

}
