package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityHeadLookPacket404;

public class EntityHeadRotationTranslator implements IDownstreamTranslator<ServerEntityHeadLookPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityHeadLookPacket404 packet) {
		WatchedEntity entity = session.getDownstream().getWatchedEntities().get((long)packet.getEntityId());
		if (entity != null) {
			entity.setHeadYaw(packet.getHeadYaw());
		}

		return new BedrockPacket[] {EntityTeleportTranslator.updateGeneral(entity, entity.getPosition(), entity.getRotation(), true, false)};
	}

}
