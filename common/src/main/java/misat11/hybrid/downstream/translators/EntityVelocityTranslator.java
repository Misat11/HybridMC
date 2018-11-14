package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetEntityMotionPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityVelocityPacket;

public class EntityVelocityTranslator implements IDownstreamTranslator<ServerEntityVelocityPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityVelocityPacket packet) {
		SetEntityMotionPacket semp = new SetEntityMotionPacket();
		semp.setRuntimeEntityId(packet.getEntityId());
		semp.setMotion(new Vector3f(packet.getMotionX() / 8000.0F, packet.getMotionY() / 8000.0F,
				packet.getMotionZ() / 8000.0F));
		return null;
	}

}
