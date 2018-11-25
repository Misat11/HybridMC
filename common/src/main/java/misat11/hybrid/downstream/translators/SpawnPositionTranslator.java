package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetSpawnPositionPacket;
import misat11.hybrid.network.bedrock.packet.SetSpawnPositionPacket.Type;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerSpawnPositionPacket404;

public class SpawnPositionTranslator implements IDownstreamTranslator<ServerSpawnPositionPacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnPositionPacket404 packet) {
		SetSpawnPositionPacket sspp = new SetSpawnPositionPacket();
		sspp.setBlockPosition(convertPosition(packet.getPosition()));
		sspp.setSpawnForced(true);
		sspp.setSpawnType(Type.WORLD_SPAWN);
		return new BedrockPacket[] {sspp};
	}

}
