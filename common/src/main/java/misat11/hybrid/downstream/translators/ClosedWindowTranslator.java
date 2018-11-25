package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.window.ServerCloseWindowPacket;

public class ClosedWindowTranslator implements IDownstreamTranslator<ServerCloseWindowPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerCloseWindowPacket packet) {
		session.getDownstream().getInventoryCache().closeOpened(session, true);
		return null;
	}

}
