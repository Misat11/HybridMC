package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerCloseWindowPacket404;

public class ClosedWindowTranslator implements IDownstreamTranslator<ServerCloseWindowPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerCloseWindowPacket404 packet) {
		session.getDownstream().getInventoryCache().closeOpened(session, true);
		return null;
	}

}
