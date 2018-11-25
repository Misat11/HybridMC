package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket;

public class SpawnGlobalTranslator implements IDownstreamTranslator<ServerSpawnGlobalEntityPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnGlobalEntityPacket packet) {
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), EntityType.LIGHTNING_BOLT.getType(), (float) packet.getX(),
						(float) packet.getY(), (float) packet.getZ(), 0, 0));
		// TODO lightning bolt
		return null;
	}

}
