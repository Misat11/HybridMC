package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerResourcePackSendPacket extends IMinecraftPacket {

	public String getUrl();

	public String getHash();
}
