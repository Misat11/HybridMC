package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMetadataPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityMetadataTranslator implements IDownstreamTranslator<ServerEntityMetadataPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityMetadataPacket packet) {
		return null;
	}

}
