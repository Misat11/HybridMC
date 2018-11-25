package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.UpdatedTileType;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerUpdateTileEntityPacket extends IMinecraftPacket {
	public Position getPosition();

	public UpdatedTileType getType();

	public CompoundTag getNBT();
}
