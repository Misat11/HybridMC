package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPluginMessagePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class CustomPayloadTranslator implements IDownstreamTranslator<ServerPluginMessagePacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPluginMessagePacket packet) {
		return null;
	}

}
