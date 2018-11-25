package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.AddEntityPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket404;
import misat11.hybrid.typeremapper.EntityRemapper;
import misat11.hybrid.util.Rotation;

public class SpawnObjectTranslator implements IDownstreamTranslator<ServerSpawnObjectPacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnObjectPacket404 packet) {
		AddEntityPacket aep = new AddEntityPacket();
		aep.setEntityType(EntityRemapper.transformObjectType(packet.getType()));
		if (aep.getEntityType() == 0) {
			return null;
		}
		Vector3f offset = EntityRemapper.makeOffset(aep.getEntityType());
		aep.setPosition(new Vector3f(packet.getX() + offset.getX(), packet.getY() + offset.getY(),
				packet.getZ() + offset.getZ()));
		aep.setMotion(new Vector3f(packet.getMotionX(), packet.getMotionY(), packet.getMotionZ()));
		aep.setRuntimeEntityId(packet.getEntityId());
		aep.setUniqueEntityId(packet.getEntityId());
		aep.setRotation(new Rotation(packet.getPitch(), packet.getYaw()));
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), aep.getEntityType(), (float) packet.getX(),
						(float) packet.getY(), (float) packet.getZ(), packet.getYaw(), packet.getPitch()));
		return new BedrockPacket[] { aep };
	}

}
