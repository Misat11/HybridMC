package misat11.hybrid.network.java.pabstract.packet.ingame.client;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientChatPacket extends IMinecraftPacket {
	public String getMessage();
}
