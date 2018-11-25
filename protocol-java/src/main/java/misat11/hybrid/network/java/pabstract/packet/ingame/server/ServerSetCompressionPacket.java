package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSetCompressionPacket extends IMinecraftPacket {
	public int getThreshold();
}
