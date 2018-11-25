package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerUnloadChunkPacket extends IMinecraftPacket {

	public int getX();

	public int getZ();
}
