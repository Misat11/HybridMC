package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityVelocityPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityVelocityTranslator implements IDownstreamTranslator<ServerEntityVelocityPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityVelocityPacket packet) {
		return null;
	}

}
