package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerUpdateTimePacket extends IMinecraftPacket {

	public long getWorldAge();

	public long getTime();
}
