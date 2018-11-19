package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.message.Message;

public interface ServerPlayerListDataPacket {

	public Message getHeader();

	public Message getFooter();
}
