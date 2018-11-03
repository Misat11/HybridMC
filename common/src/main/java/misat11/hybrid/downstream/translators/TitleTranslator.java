package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerTitlePacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.SetTitlePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class TitleTranslator implements IDownstreamTranslator<ServerTitlePacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerTitlePacket packet) {
		switch (packet.getAction()) {
		case ACTION_BAR:
			return new BedrockPacket[] {
					create(SetTitlePacket.Type.SET_ACTIONBAR_MESSAGE, packet.getActionBar().getFullText(), 0, 0, 0) };
		case CLEAR:
			return new BedrockPacket[] { create(SetTitlePacket.Type.CLEAR_TITLE, "", 0, 0, 0) };
		case RESET:
			return new BedrockPacket[] { create(SetTitlePacket.Type.RESET_TITLE, "", 0, 0, 0) };
		case SUBTITLE:
			return new BedrockPacket[] {
					create(SetTitlePacket.Type.SET_SUBTITLE, packet.getSubtitle().getFullText(), 0, 0, 0) };
		case TIMES:
			return new BedrockPacket[] { create(SetTitlePacket.Type.SET_ANIMATION_TIMES, "", packet.getFadeIn(),
					packet.getStay(), packet.getFadeOut()) };
		case TITLE:
			return new BedrockPacket[] {
					create(SetTitlePacket.Type.SET_TITLE, packet.getTitle().getFullText(), 0, 0, 0) };
		}
		return null;
	}

	public static SetTitlePacket create(SetTitlePacket.Type type, String title, int fadeInTime, int stayTime,
			int fadeOutTime) {
		SetTitlePacket stp = new SetTitlePacket();
		stp.setText(title);
		stp.setType(type);
		stp.setFadeInTime(fadeInTime);
		stp.setFadeOutTime(fadeOutTime);
		stp.setStayTime(stayTime);
		return stp;
	}

}
