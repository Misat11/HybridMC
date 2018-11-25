package misat11.hybrid.network.java.pabstract.packet.status.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface StatusPongPacket extends IMinecraftPacket {
	public long getPingTime();
}
