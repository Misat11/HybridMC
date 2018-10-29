package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityTeleportTranslator implements IDownstreamTranslator<ServerEntityTeleportPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityTeleportPacket packet) {
		return null;
	}

}
