package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.window.ServerOpenWindowPacket;

public class OpenWindowTranslator implements IDownstreamTranslator<ServerOpenWindowPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerOpenWindowPacket packet) {
		session.getDownstream().getInventoryCache().open(session, packet);
		return null;
	}

}
