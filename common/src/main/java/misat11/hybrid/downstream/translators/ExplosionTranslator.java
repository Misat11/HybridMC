package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.ExplodePacket;
import misat11.hybrid.network.bedrock.packet.LevelEventPacket;
import misat11.hybrid.network.bedrock.packet.SetEntityMotionPacket;
import misat11.hybrid.network.bedrock.packet.LevelEventPacket.Event;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.data.game.world.block.ExplodedBlockRecord;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerExplosionPacket;

public class ExplosionTranslator implements IDownstreamTranslator<ServerExplosionPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerExplosionPacket packet) {
		List<BedrockPacket> packets = new ArrayList<>();
		ExplodePacket ep = new ExplodePacket();
		ep.setPosition(new Vector3f(packet.getX(), packet.getY(), packet.getZ()));
		ep.setRadius(packet.getRadius() * 100);
		for (ExplodedBlockRecord entry : packet.getExploded()) {
			ep.getRecords().add(new Vector3i(entry.getX(), entry.getY(), entry.getZ()));
		}
		packets.add(ep);
		SetEntityMotionPacket semp = new SetEntityMotionPacket();
		semp.setRuntimeEntityId(session.getDownstream().playerEntityId);
		semp.setMotion(new Vector3f(packet.getPushX(), packet.getPushY(), packet.getPushZ()));
		packets.add(semp);
		/*LevelEventPacket lep = new LevelEventPacket();
		lep.setEvent(Event.PARTICLE_DESTROY);
		lep.setPosition(new Vector3f(packet.getX(), packet.getY(), packet.getZ()));
		packets.add(lep);*/ // TODO
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

}
