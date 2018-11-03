package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket;
import com.nukkitx.api.util.Rotation;
import com.nukkitx.server.entity.EntityType;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.AddEntityPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class SpawnExpOrbTranslator implements IDownstreamTranslator<ServerSpawnExpOrbPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnExpOrbPacket packet) {

		AddEntityPacket aep = new AddEntityPacket();
		aep.setEntityType(EntityType.EXPERIENCE_ORB.getType());
		if (aep.getEntityType() == 0) {
			return null;
		}
		aep.setPosition(new Vector3f(packet.getX(), packet.getY(), packet.getZ()));
		aep.setMotion(new Vector3f(0, 0, 0));
		aep.setRuntimeEntityId(packet.getEntityId());
		aep.setUniqueEntityId(packet.getEntityId());
		aep.setRotation(new Rotation(0, 0));
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), aep.getEntityType()));
		return new BedrockPacket[] { aep };
	}

}
