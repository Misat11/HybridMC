package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientCreativeInventoryActionPacket extends IMinecraftPacket {

	public int getSlot();

	public ItemStack getClickedItem();
}
