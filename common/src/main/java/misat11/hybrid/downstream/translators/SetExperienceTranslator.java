package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.entity.Attribute;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.UpdateAttributesPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket;

public class SetExperienceTranslator implements IDownstreamTranslator<ServerPlayerSetExperiencePacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerSetExperiencePacket packet) {
		UpdateAttributesPacket uap = new UpdateAttributesPacket();
		uap.setRuntimeEntityId(session.getDownstream().playerEntityId);
		uap.getAttributes().add(new Attribute("minecraft:player.experience", packet.getSlot(), 0, 1));
		uap.getAttributes().add(new Attribute("minecraft:player.level", packet.getLevel(), 0, 24791));
		return new BedrockPacket[] {uap};
	}

}
