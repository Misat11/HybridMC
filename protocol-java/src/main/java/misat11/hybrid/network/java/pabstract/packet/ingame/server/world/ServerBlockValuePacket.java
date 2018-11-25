package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.value.BlockValue;
import misat11.hybrid.network.java.pabstract.data.game.world.block.value.BlockValueType;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerBlockValuePacket extends IMinecraftPacket {
	public Position getPosition();

	public BlockValueType getType();

	public BlockValue getValue();

	public int getBlockId();
}
