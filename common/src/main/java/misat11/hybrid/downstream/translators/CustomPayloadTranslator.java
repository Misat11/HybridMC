package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPluginMessagePacket404;

public class CustomPayloadTranslator implements IDownstreamTranslator<ServerPluginMessagePacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPluginMessagePacket404 packet) {
		return null;
	}

}
