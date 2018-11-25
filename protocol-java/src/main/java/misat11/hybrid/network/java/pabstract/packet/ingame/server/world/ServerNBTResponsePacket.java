package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerNBTResponsePacket extends IMinecraftPacket {
	public int getTransactionId();

	public CompoundTag getNBT();
}
