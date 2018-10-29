package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnMobPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class SpawnLivingTranslator implements IDownstreamTranslator<ServerSpawnMobPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnMobPacket packet) {
		return null;
	}

}
