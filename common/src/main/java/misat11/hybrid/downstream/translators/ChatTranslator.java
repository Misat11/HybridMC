package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.data.game.MessageType;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.nukkitx.api.message.RawMessage;
import com.nukkitx.api.message.TipMessage;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.TextPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ChatTranslator implements IDownstreamTranslator<ServerChatPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerChatPacket packet) {
		TextPacket tp = new TextPacket();
		String message = packet.getMessage().getFullText();
		if (packet.getType() == MessageType.NOTIFICATION) {
			tp.setMessage(new TipMessage(message, false));
		} else {
			tp.setMessage(new RawMessage(message, false));
		}
		tp.setPlatformChatId("");
		tp.setXuid("");
		return null;
	}

}
