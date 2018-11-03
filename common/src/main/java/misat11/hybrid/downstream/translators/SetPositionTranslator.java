package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientTeleportConfirmPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.nukkitx.api.util.Rotation;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.FullChunkDataPacket;
import com.nukkitx.server.network.bedrock.packet.MovePlayerPacket;
import com.nukkitx.server.network.bedrock.packet.MovePlayerPacket.Mode;
import com.nukkitx.server.network.bedrock.packet.MovePlayerPacket.TeleportationCause;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class SetPositionTranslator implements IDownstreamTranslator<ServerPlayerPositionRotationPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerPositionRotationPacket packet) {
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
		packets.add(create(session.getDownstream().playerEntityId, new Vector3f(packet.getX(), packet.getY(), packet.getZ()), new Rotation(packet.getPitch(), packet.getYaw()), true));
		if (packet.getTeleportId() != 0) {
			session.getDownstream().send(new ClientTeleportConfirmPacket(packet.getTeleportId()));
		}
		return packets.toArray(new BedrockPacket[packets.size()]);
	}
	
	public static MovePlayerPacket create(long entityID, Vector3f position, Rotation rotation, boolean teleported) {
		MovePlayerPacket mpp = new MovePlayerPacket();
		mpp.setRuntimeEntityId(entityID);
		mpp.setPosition(position);
		mpp.setRotation(rotation);
		mpp.setMode(teleported ? Mode.TELEPORT : Mode.NORMAL);
		mpp.setTeleportationCause(TeleportationCause.UNKNOWN);
		return mpp;
	}

}
