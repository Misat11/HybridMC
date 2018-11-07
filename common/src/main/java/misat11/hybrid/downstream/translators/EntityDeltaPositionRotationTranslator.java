package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMovementPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;
import com.nukkitx.api.util.Rotation;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.MoveEntityAbsolutePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.typeremapper.EntityRemapper;

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
		MoveEntityAbsolutePacket meap = new MoveEntityAbsolutePacket();
		meap.setRuntimeEntityId(packet.getEntityId());
		Vector3f offset = EntityRemapper.makeOffset(entity.getType());
		meap.setPosition(new Vector3f(entity.getX() + offset.getX(), entity.getY() + offset.getY(),
				entity.getZ() + offset.getZ()));
		if (packet instanceof ServerEntityRotationPacket || packet instanceof ServerEntityPositionRotationPacket) {
			meap.setRotation(new Rotation(packet.getPitch(), packet.getYaw()));
		}
		meap.setTeleported(false);
		meap.setOnGround(packet.isOnGround());
		return new BedrockPacket[] { meap };
	}

}
