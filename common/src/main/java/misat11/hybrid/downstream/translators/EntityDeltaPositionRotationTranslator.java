package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityMovementPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPositionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPositionRotationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityRotationPacket404;

public class EntityDeltaPositionRotationTranslator implements IDownstreamTranslator<ServerEntityMovementPacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityMovementPacket404 packet) {
		WatchedEntity entity = session.getDownstream().getWatchedEntities().get((long) packet.getEntityId());
		if (entity == null) {
			return null;
		}
		if (packet instanceof ServerEntityPositionPacket404 || packet instanceof ServerEntityPositionRotationPacket404) {
			entity.moveEntityDelta((float) packet.getMovementX(), (float) packet.getMovementY(),
					(float) packet.getMovementZ());
		}
		if (packet instanceof ServerEntityRotationPacket404 || packet instanceof ServerEntityPositionRotationPacket404) {
			entity.rotateEntity(packet.getYaw(), packet.getPitch());
		}
		if (entity.shouldMove()) {
			entity.setShouldMove(false);
			return new BedrockPacket[] { EntityTeleportTranslator.updateGeneral(entity, entity.getPosition(),
					entity.getRotation(), packet.isOnGround(), false) };
		} else {
			return null;
		}
	}

}
