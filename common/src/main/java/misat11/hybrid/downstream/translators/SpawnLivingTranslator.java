package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnMobPacket;
import com.nukkitx.api.util.Rotation;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.AddEntityPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.typeremapper.EntityRemapper;

public class SpawnLivingTranslator implements IDownstreamTranslator<ServerSpawnMobPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnMobPacket packet) {
		AddEntityPacket aep = new AddEntityPacket();
		aep.setEntityType(EntityRemapper.transformMobType(packet.getType()));
		if (aep.getEntityType() == 0) {
			return null;
		}
		Vector3f offset = EntityRemapper.makeOffset(aep.getEntityType());
		aep.setPosition(new Vector3f(packet.getX() + offset.getX(), packet.getY() + offset.getY(),
				packet.getZ() + offset.getZ()));
		aep.setMotion(new Vector3f(packet.getMotionX(), packet.getMotionY(), packet.getMotionZ()));
		aep.setRuntimeEntityId(packet.getEntityId());
		aep.setUniqueEntityId(packet.getEntityId());
		aep.setRotation(new Rotation(packet.getPitch(), packet.getYaw(), packet.getHeadYaw()));
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), aep.getEntityType(), (float) packet.getX(),
						(float) packet.getY(), (float) packet.getZ()));
		return new BedrockPacket[] { aep };
	}

}
