package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetTitlePacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerTitlePacket;

public class TitleTranslator implements IDownstreamTranslator<ServerTitlePacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerTitlePacket packet) {
		switch (packet.getAction()) {
		case ACTION_BAR:
			return new BedrockPacket[] {
					create(SetTitlePacket.Type.SET_ACTIONBAR_MESSAGE, ChatTranslator.translate(packet.getActionBar()), 0, 0, 0) };
		case CLEAR:
			return new BedrockPacket[] { create(SetTitlePacket.Type.CLEAR_TITLE, "", 0, 0, 0) };
		case RESET:
			return new BedrockPacket[] { create(SetTitlePacket.Type.RESET_TITLE, "", 0, 0, 0) };
		case SUBTITLE:
			return new BedrockPacket[] {
					create(SetTitlePacket.Type.SET_SUBTITLE, ChatTranslator.translate(packet.getSubtitle()), 0, 0, 0) };
		case TIMES:
			return new BedrockPacket[] { create(SetTitlePacket.Type.SET_ANIMATION_TIMES, "", packet.getFadeIn(),
					packet.getStay(), packet.getFadeOut()) };
		case TITLE:
			return new BedrockPacket[] {
					create(SetTitlePacket.Type.SET_TITLE, ChatTranslator.translate(packet.getTitle()), 0, 0, 0) };
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
