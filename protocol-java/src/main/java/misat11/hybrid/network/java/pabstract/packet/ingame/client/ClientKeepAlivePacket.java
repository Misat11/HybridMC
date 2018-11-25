package misat11.hybrid.network.java.pabstract.packet.ingame.client;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientKeepAlivePacket extends IMinecraftPacket {
	public long getPingId();
}
