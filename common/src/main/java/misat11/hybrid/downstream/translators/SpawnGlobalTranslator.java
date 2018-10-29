package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class SpawnGlobalTranslator implements IDownstreamTranslator<ServerSpawnGlobalEntityPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnGlobalEntityPacket packet) {
		return null;
	}

}
