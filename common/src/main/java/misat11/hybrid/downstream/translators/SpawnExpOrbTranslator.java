package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.AddEntityPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket;
import misat11.hybrid.typeremapper.EntityRemapper;
import misat11.hybrid.util.Rotation;

public class SpawnExpOrbTranslator implements IDownstreamTranslator<ServerSpawnExpOrbPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnExpOrbPacket packet) {

		AddEntityPacket aep = new AddEntityPacket();
		aep.setEntityType(EntityType.EXPERIENCE_ORB.getType());
		if (aep.getEntityType() == 0) {
			return null;
		}
		Vector3f offset = EntityRemapper.makeOffset(aep.getEntityType());
		aep.setPosition(new Vector3f(packet.getX() + offset.getX(), packet.getY() + offset.getY(),
				packet.getZ() + offset.getZ()));
		aep.setMotion(new Vector3f(0, 0, 0));
		aep.setRuntimeEntityId(packet.getEntityId());
		aep.setUniqueEntityId(packet.getEntityId());
		aep.setRotation(new Rotation(0, 0));
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), aep.getEntityType(), (float) packet.getX(),
						(float) packet.getY(), (float) packet.getZ(), 0, 0));
		return new BedrockPacket[] { aep };
	}

}
