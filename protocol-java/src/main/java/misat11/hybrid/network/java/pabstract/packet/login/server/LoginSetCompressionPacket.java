package misat11.hybrid.network.java.pabstract.packet.login.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface LoginSetCompressionPacket extends IMinecraftPacket {
	public int getThreshold();
}
