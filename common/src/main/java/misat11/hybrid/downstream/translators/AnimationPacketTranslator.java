package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.github.steveice10.mc.protocol.data.game.entity.player.Animation;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityAnimationPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.AnimatePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class AnimationPacketTranslator implements IDownstreamTranslator<ServerEntityAnimationPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityAnimationPacket packet) {
		List<BedrockPacket> packets = new ArrayList<BedrockPacket>();
		if (packet.getAnimation() == Animation.SWING_ARM) {
			AnimatePacket animate = new AnimatePacket();
			animate.setRuntimeEntityId(packet.getEntityId());
			animate.setAction(com.nukkitx.api.Player.Animation.SWING_ARM);
			packets.add(animate);
		}
		if (packet.getAnimation() == Animation.CRITICAL_HIT) {
			AnimatePacket animate = new AnimatePacket();
			animate.setRuntimeEntityId(packet.getEntityId());
			animate.setAction(com.nukkitx.api.Player.Animation.CRITICAL_HIT);
			packets.add(animate);
		}
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

}
