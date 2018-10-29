package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityDestroyPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityDestroyTranslator implements IDownstreamTranslator<ServerEntityDestroyPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityDestroyPacket packet) {
		return null;
	}

}
