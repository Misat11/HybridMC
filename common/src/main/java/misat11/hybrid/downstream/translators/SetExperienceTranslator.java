package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket;
import com.nukkitx.server.entity.Attribute;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.UpdateAttributesPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

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
