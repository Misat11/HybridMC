package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMovementPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityDeltaPositionRotationTranslator implements IDownstreamTranslator<ServerEntityMovementPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityMovementPacket packet) {
		WatchedEntity entity = session.getDownstream().getWatchedEntities().get((long) packet.getEntityId());
		if (entity == null) {
			return null;
		}
		if (packet instanceof ServerEntityPositionPacket || packet instanceof ServerEntityPositionRotationPacket) {
			entity.moveEntityDelta((float) packet.getMovementX(), (float) packet.getMovementY(),
					(float) packet.getMovementZ());
		}
		if (packet instanceof ServerEntityRotationPacket || packet instanceof ServerEntityPositionRotationPacket) {
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
