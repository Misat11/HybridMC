package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.entity.Attribute;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.RespawnPacket;
import misat11.hybrid.network.bedrock.packet.UpdateAttributesPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerHealthPacket;

public class SetHealthTranslator implements IDownstreamTranslator<ServerPlayerHealthPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerHealthPacket packet) {
		List<BedrockPacket> packets = new ArrayList<>();
		UpdateAttributesPacket uap = new UpdateAttributesPacket();
		uap.setRuntimeEntityId(session.getDownstream().playerEntityId);
		uap.getAttributes().add(new Attribute("minecraft:health", packet.getHealth(), 0, 20));
		uap.getAttributes().add(new Attribute("minecraft:player.hunger", packet.getFood(), 0, 20));
		uap.getAttributes().add(new Attribute("minecraft:player.saturation", packet.getSaturation(), 0, 20));
		packets.add(uap);
		if (packet.getHealth() <= 0.0){
			RespawnPacket rp = new RespawnPacket();
			rp.setPosition(new Vector3f(0,0,0));
			packets.add(rp);
		}
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

}
