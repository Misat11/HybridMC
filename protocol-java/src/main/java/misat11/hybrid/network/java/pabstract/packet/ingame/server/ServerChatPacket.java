package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.MessageType;
import misat11.hybrid.network.java.pabstract.data.message.Message;

public interface ServerChatPacket {

	public Message getMessage();

	public MessageType getType();
}
