package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;
import com.nukkitx.api.util.Rotation;
import com.nukkitx.server.entity.EntityType;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.MoveEntityAbsolutePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.typeremapper.EntityRemapper;

public class EntityTeleportTranslator implements IDownstreamTranslator<ServerEntityTeleportPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityTeleportPacket packet) {
		WatchedEntity entity = session.getDownstream().getWatchedEntities().get((long) packet.getEntityId());
		if (entity == null) {
			return null;
		}
		Vector3f offset = EntityRemapper.makeOffset(entity.getType());
		Vector3f position = new Vector3f(packet.getX() + offset.getX(), packet.getY() + offset.getY(),
				packet.getZ() + offset.getZ());
		// TODO add offset to position
		if (entity.isRiding()) {
			WatchedEntity vehicle = session.getDownstream().getWatchedEntities().get(entity.getVehicleID());
			if (vehicle != null) {
				if (vehicle.getType() == EntityType.BOAT.getType()) {
					float relYaw = (float) ((entity.getHeadYaw() - vehicle.getHeadYaw()) + 11.38);
					if (relYaw <= -128) {
						relYaw += 256;
					}
					if (relYaw > 128) {
						relYaw -= 256;
					}
					return new BedrockPacket[] { updateGeneral(entity, position,
							new Rotation(packet.getPitch(), relYaw), packet.isOnGround(), false) };
				}
			} else {
				entity.setVehicleID(0);
			}
		}
		if (entity.getType() == EntityType.BOAT.getType()) {
			entity.setHeadYaw(packet.getYaw());
		}
		return new BedrockPacket[] { updateGeneral(entity, position,
				new Rotation(packet.getPitch(), packet.getYaw(), entity.getHeadYaw()), packet.isOnGround(), false) };
	}

	public static MoveEntityAbsolutePacket create(long eid, Vector3f position, Rotation rotation, boolean onGround,
			boolean teleported) {
		MoveEntityAbsolutePacket meap = new MoveEntityAbsolutePacket();
		meap.setRuntimeEntityId(eid);
		meap.setPosition(position);
		meap.setRotation(rotation);
		meap.setOnGround(onGround);
		meap.setTeleported(teleported);
		return meap;
	}

	public static BedrockPacket updateGeneral(WatchedEntity entity, Vector3f position, Rotation rotation,
			boolean onGround, boolean teleported) {
		if (entity.getType() == EntityType.PLAYER.getType()) {
			return SetPositionTranslator.create(entity.getEntityID(), position, rotation, teleported);
		} else {
			return create(entity.getEntityID(), position, rotation, onGround, teleported);
		}

	}

}
