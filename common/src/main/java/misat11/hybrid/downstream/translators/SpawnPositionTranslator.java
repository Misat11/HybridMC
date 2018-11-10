package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerSpawnPositionPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetSpawnPositionPacket;
import misat11.hybrid.network.bedrock.packet.SetSpawnPositionPacket.Type;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class SpawnPositionTranslator implements IDownstreamTranslator<ServerSpawnPositionPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnPositionPacket packet) {
		SetSpawnPositionPacket sspp = new SetSpawnPositionPacket();
		sspp.setBlockPosition(convertPosition(packet.getPosition()));
		sspp.setSpawnForced(true);
		sspp.setSpawnType(Type.WORLD_SPAWN);
		return new BedrockPacket[] {sspp};
	}

}
