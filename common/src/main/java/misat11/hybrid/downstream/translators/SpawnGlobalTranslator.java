package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket404;

public class SpawnGlobalTranslator implements IDownstreamTranslator<ServerSpawnGlobalEntityPacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnGlobalEntityPacket404 packet) {
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), EntityType.LIGHTNING_BOLT.getType(), (float) packet.getX(),
						(float) packet.getY(), (float) packet.getZ(), 0, 0));
		// TODO lightning bolt
		return null;
	}

}
