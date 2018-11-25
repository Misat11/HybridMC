package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerListDataPacket extends IMinecraftPacket {

	public Message getHeader();

	public Message getFooter();
}
