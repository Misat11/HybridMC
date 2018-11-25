package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.FullChunkDataPacket;
import misat11.hybrid.network.bedrock.packet.MovePlayerPacket;
import misat11.hybrid.network.bedrock.packet.MovePlayerPacket.Mode;
import misat11.hybrid.network.bedrock.packet.MovePlayerPacket.TeleportationCause;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket404;
import misat11.hybrid.typeremapper.EntityRemapper;
import misat11.hybrid.util.Rotation;

public class SetPositionTranslator implements IDownstreamTranslator<ServerPlayerPositionRotationPacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerPositionRotationPacket404 packet) {
		if (packet.getTeleportId() != 0) {
			session.getDownstream().getMovementCache().setTeleportLocation(packet.getX(), packet.getY(), packet.getZ(), packet.getTeleportId());
		}
		session.getDownstream().getWatchedEntities().get(session.getDownstream().playerEntityId).moveEntityAbsolute((float) packet.getX(), (float) packet.getY(), (float) packet.getZ(), packet.getYaw(), packet.getPitch());
		List<BedrockPacket> packets = new ArrayList<>();
		int chunkX = (int) Math.floor(packet.getX()) >> 4;
		int chunkZ = (int) Math.floor(packet.getZ()) >> 4;
		if (!session.getDownstream().getChunkCache().isMarkedAsSent(chunkX, chunkZ)) {
			FullChunkDataPacket fcdp = new FullChunkDataPacket();
			fcdp.setChunkX(chunkX);
			fcdp.setChunkZ(chunkZ);
			fcdp.setData(ChangeDimensionTranslator.emptyChunk);
			packets.add(fcdp);
		}
		if (session.getDownstream().getMovementCache().isPEPositionAboveLeniency()) {
			packets.add(create(session.getDownstream().playerEntityId,
					new Vector3f(packet.getX(), packet.getY() + 0.01, packet.getZ()),
					new Rotation(packet.getPitch(), packet.getYaw(), packet.getYaw()), true));
		}
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

	public static MovePlayerPacket create(long entityID, Vector3f position, Rotation rotation, boolean teleported) {
		Vector3f offset = EntityRemapper.makeOffset(EntityType.PLAYER.getType());
		Vector3f positionWithOffset = new Vector3f(position.getX() + offset.getX(), position.getY() + offset.getY(),
				position.getZ() + offset.getZ());
		MovePlayerPacket mpp = new MovePlayerPacket();
		mpp.setRuntimeEntityId(entityID);
		mpp.setPosition(positionWithOffset);
		mpp.setRotation(rotation);
		mpp.setMode(teleported ? Mode.TELEPORT : Mode.NORMAL);
		mpp.setTeleportationCause(TeleportationCause.UNKNOWN);
		return mpp;
	}

}
