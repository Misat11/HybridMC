package misat11.hybrid.network.java.pabstract.packet.ingame.server.window;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSetSlotPacket extends IMinecraftPacket {
	public int getWindowId();

	public int getSlot();

	public ItemStack getItem();
}
