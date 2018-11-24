package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.MoveEntityAbsolutePacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityTeleportPacket404;
import misat11.hybrid.typeremapper.EntityRemapper;
import misat11.hybrid.util.Rotation;

public class EntityTeleportTranslator implements IDownstreamTranslator<ServerEntityTeleportPacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityTeleportPacket404 packet) {
		WatchedEntity entity = session.getDownstream().getWatchedEntities().get((long) packet.getEntityId());
		if (entity == null) {
			return null;
		}
		entity.moveEntityAbsolute((float) packet.getX(), (float) packet.getY(), (float) packet.getZ(), packet.getYaw(),
				packet.getPitch());
		Vector3f offset = EntityRemapper.makeOffset(entity.getType());
		Vector3f position = new Vector3f(packet.getX() + offset.getX(), packet.getY() + offset.getY(),
				packet.getZ() + offset.getZ());
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
					if (entity.shouldMove()) {
						entity.setShouldMove(false);
						return new BedrockPacket[] { updateGeneral(entity, position,
								new Rotation(packet.getPitch(), relYaw), packet.isOnGround(), false) };
					} else {
						return null;
					}
				}
			} else {
				entity.setVehicleID(0);
			}
		}
		if (entity.getType() == EntityType.BOAT.getType()) {
			entity.setHeadYaw(packet.getYaw());
		}
		if (entity.shouldMove()) {
			entity.setShouldMove(false);
			return new BedrockPacket[] { updateGeneral(entity, position,
					new Rotation(packet.getPitch(), packet.getYaw(), entity.getHeadYaw()), packet.isOnGround(),
					false) };
		} else {
			return null;
		}
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
