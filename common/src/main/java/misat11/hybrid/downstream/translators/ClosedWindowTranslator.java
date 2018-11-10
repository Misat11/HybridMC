package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerCloseWindowPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ClosedWindowTranslator implements IDownstreamTranslator<ServerCloseWindowPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerCloseWindowPacket packet) {
		session.getDownstream().getInventoryCache().closeOpened(session, true);
		return null;
	}

}
