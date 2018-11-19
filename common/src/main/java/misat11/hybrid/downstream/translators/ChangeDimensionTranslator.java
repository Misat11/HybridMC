package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.ChangeDimensionPacket;
import misat11.hybrid.network.bedrock.packet.FullChunkDataPacket;
import misat11.hybrid.network.bedrock.packet.MovePlayerPacket;
import misat11.hybrid.network.bedrock.packet.PlayStatusPacket;
import misat11.hybrid.network.bedrock.packet.MovePlayerPacket.Mode;
import misat11.hybrid.network.bedrock.packet.MovePlayerPacket.TeleportationCause;
import misat11.hybrid.network.bedrock.packet.PlayStatusPacket.Status;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerRespawnPacket404;
import misat11.hybrid.network.util.VarInts;
import misat11.hybrid.util.Rotation;

public class ChangeDimensionTranslator implements IDownstreamTranslator<ServerRespawnPacket404> {
	
	public static final byte[] emptyChunk;
	
	static {
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte(1); //1 section
		buf.writeByte(8); //New subchunk version!
		buf.writeByte(1); //Zero blockstorages :O
		buf.writeByte((1 << 1) | 1);  //Runtimeflag and palette id.
		buf.writeZero(512);
	    VarInts.writeInt(buf, 1); //Palette size
	    VarInts.writeInt(buf, 0); //Air
	    buf.writeZero(512); //heightmap.
	    buf.writeZero(256); //Biomedata.
	    buf.writeByte(0); //borders
	    emptyChunk = buf.array();
	}

	public static final int NETHER = -1;
	public static final int THE_END = 1;
	public static final int OVERWORLD = 0;

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerRespawnPacket404 packet) {
		session.getDownstream().getChunkCache().clear();
		session.getDownstream().switchFakePos = !session.getDownstream().switchFakePos;
		return create(getPeDimensionId(packet.getDimension()), session.getDownstream().switchFakePos ? 20 : 30, session.getDownstream().playerEntityId);
	}
	
	public static BedrockPacket[] create(int dimension, double posY, long eid) {
		List<BedrockPacket> packets = new ArrayList<BedrockPacket>();
		ChangeDimensionPacket cdp = new ChangeDimensionPacket();
		cdp.setDimension(dimension);
		cdp.setPosition(new Vector3f());
		cdp.setRespawn(true);
		packets.add(cdp);
		PlayStatusPacket psp = new PlayStatusPacket();
		psp.setStatus(Status.PLAYER_SPAWN);
		packets.add(psp);
		addFakeChunksAndPos(packets, posY, eid);
		return packets.toArray(new BedrockPacket[packets.size()]);
		
	}
	
	public static void addFakeChunksAndPos(List<BedrockPacket> packets, double posY, long eid) {
		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				FullChunkDataPacket fcdp = new FullChunkDataPacket();
				fcdp.setChunkX(x);
				fcdp.setChunkZ(z);
				fcdp.setData(emptyChunk);
				packets.add(fcdp);
			}
		}
		MovePlayerPacket mpp = new MovePlayerPacket();
		mpp.setPosition(new Vector3f(0, posY, 0));
		mpp.setRotation(new Rotation(0, 0, 0));
		mpp.setMode(Mode.TELEPORT);
		mpp.setRuntimeEntityId(eid);
		mpp.setTeleportationCause(TeleportationCause.UNKNOWN);
		packets.add(mpp);
	}

	public static int getPeDimensionId(int dimId) {
		switch (dimId) {
		case NETHER: {
			return 1;
		}
		case THE_END: {
			return 2;
		}
		case OVERWORLD: {
			return 0;
		}
		default: {
			throw new IllegalArgumentException("Uknown dim id " + dimId);
		}
		}
	}

}
