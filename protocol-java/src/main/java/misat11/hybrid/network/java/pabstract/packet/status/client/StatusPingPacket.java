package misat11.hybrid.network.java.pabstract.packet.status.client;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface StatusPingPacket extends IMinecraftPacket {
	public long getPingTime();
}
