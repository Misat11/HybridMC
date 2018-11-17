package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;

public interface ClientCreativeInventoryActionPacket {

	public int getSlot();

	public ItemStack getClickedItem();
}
