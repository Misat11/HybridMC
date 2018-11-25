package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.chunk.Column;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerChunkDataPacket extends IMinecraftPacket {
	public Column getColumn();
}
