package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityAttachPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityLeashTranslator implements IDownstreamTranslator<ServerEntityAttachPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityAttachPacket packet) {
		return null;
	}

}
