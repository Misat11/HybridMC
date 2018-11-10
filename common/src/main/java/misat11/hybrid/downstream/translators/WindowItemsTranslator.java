package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerWindowItemsPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class WindowItemsTranslator implements IDownstreamTranslator<ServerWindowItemsPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerWindowItemsPacket packet) {

		return null;
	}

}
