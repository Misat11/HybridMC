package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerExplosionPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ExplosionTranslator implements IDownstreamTranslator<ServerExplosionPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerExplosionPacket packet) {
		return null;
	}

}
