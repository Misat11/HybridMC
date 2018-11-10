package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerOpenWindowPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class OpenWindowTranslator implements IDownstreamTranslator<ServerOpenWindowPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerOpenWindowPacket packet) {
		session.getDownstream().getInventoryCache().open(session, packet);
		return null;
	}

}
