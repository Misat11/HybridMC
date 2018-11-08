package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMovementPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;
import com.nukkitx.api.util.Rotation;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
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
			entity.setYaw(packet.getYaw());
			entity.setPitch(packet.getPitch());
		}
		return new BedrockPacket[] { EntityTeleportTranslator.updateGeneral(entity, new Vector3f(entity.getX(), entity.getY(), entity.getZ()), new Rotation(entity.getPitch(), entity.getYaw(), entity.getHeadYaw()), packet.isOnGround(), false) };
	}

}
