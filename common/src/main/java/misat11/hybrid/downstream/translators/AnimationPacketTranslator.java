package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.AnimatePacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Animation;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityAnimationPacket;

public class AnimationPacketTranslator implements IDownstreamTranslator<ServerEntityAnimationPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityAnimationPacket packet) {
		List<BedrockPacket> packets = new ArrayList<BedrockPacket>();
		if (packet.getAnimation() == Animation.SWING_ARM) {
			AnimatePacket animate = new AnimatePacket();
			animate.setRuntimeEntityId(packet.getEntityId());
			animate.setAction(AnimatePacket.Animation.SWING_ARM);
			packets.add(animate);
		}
		if (packet.getAnimation() == Animation.CRITICAL_HIT) {
			AnimatePacket animate = new AnimatePacket();
			animate.setRuntimeEntityId(packet.getEntityId());
			animate.setAction(AnimatePacket.Animation.CRITICAL_HIT);
			packets.add(animate);
		}
		if (packet.getAnimation() == Animation.LEAVE_BED) {
			AnimatePacket animate = new AnimatePacket();
			animate.setRuntimeEntityId(packet.getEntityId());
			animate.setAction(AnimatePacket.Animation.WAKE_UP);
			packets.add(animate);
		}
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

}
