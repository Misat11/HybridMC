package misat11.hybrid.network.java.pabstract.packet.login.server;

import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface LoginDisconnectPacket extends IMinecraftPacket {
	public Message getReason();
}
