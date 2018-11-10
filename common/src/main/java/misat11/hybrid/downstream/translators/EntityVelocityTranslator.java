package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityVelocityPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetEntityMotionPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

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
