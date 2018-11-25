package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockChangeRecord;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerMultiBlockChangePacket extends IMinecraftPacket {
	public BlockChangeRecord[] getRecords();
}
