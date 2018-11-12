package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.data.game.world.particle.ParticleType;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerSpawnParticlePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.ParticleTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.LevelEventPacket;
import misat11.hybrid.network.bedrock.packet.LevelEventPacket.Event;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class WorldParticleTranslator implements IDownstreamTranslator<ServerSpawnParticlePacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnParticlePacket packet) {
		List<BedrockPacket> packets = new ArrayList<>();
        if (packet.getParticle().getType() == ParticleType.BLOCK) {
	        LevelEventPacket pk = new LevelEventPacket();
	        pk.setEvent(Event.PARTICLE_DESTROY);
	        pk.setPosition(new Vector3f(packet.getX(), packet.getY(), packet.getZ()));
	        //Position pos = new Position((int) packet.getX(), (int) packet.getY(), (int) packet.getZ());
	        //BlockEntry entry = 
	        //if (session.getChunkCache().getBlock(pos) != null)
	        //    pk.setData(session.getServer().getPalletteManager().fromLegacy(entry.getId(), (byte)entry.getData());
	        //else
	            pk.setData(session.getServer().getPaletteManager().fromLegacy(1, (byte) 0));
        packets.add(pk);
	    } else {
	        int num = ParticleTranslator.translate(packet.getParticle().getType());
	        if (num != -1) {
	            Random random = new Random(System.currentTimeMillis());
	            for (int i = 0; i < packet.getAmount(); i++) {
	                packets.add(getParticle(packet.getX() + (random.nextFloat() * 2 - 1) * packet.getOffsetX(),
	                        packet.getY() + (random.nextFloat() * 2 - 1) * packet.getOffsetY(),
	                        packet.getZ() + (random.nextFloat() * 2 - 1) * packet.getOffsetZ(), num, 0));
	            }
	        }
	    }
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

    public static LevelEventPacket getParticle(float x, float y, float z, int type, int data) {
        LevelEventPacket pk = new LevelEventPacket();
        pk.setEvent((short) (Event.SET_DATA.getId() | type));
        pk.setPosition(new Vector3f(x, y, z));
        pk.setData(data);
        return pk;
    }

}
