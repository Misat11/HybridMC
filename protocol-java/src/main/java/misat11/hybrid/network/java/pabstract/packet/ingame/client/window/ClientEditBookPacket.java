package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;

public interface ClientEditBookPacket {

	public ItemStack getBook();

	public boolean getIsSigning();
}
