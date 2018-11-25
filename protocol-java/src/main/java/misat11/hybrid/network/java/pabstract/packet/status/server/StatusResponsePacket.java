package misat11.hybrid.network.java.pabstract.packet.status.server;

import misat11.hybrid.network.java.pabstract.data.status.ServerStatusInfo;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface StatusResponsePacket extends IMinecraftPacket {
	public ServerStatusInfo getInfo();
}
