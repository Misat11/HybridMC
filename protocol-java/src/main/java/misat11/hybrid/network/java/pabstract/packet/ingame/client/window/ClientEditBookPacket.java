package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientEditBookPacket extends IMinecraftPacket {

	public ItemStack getBook();

	public boolean getIsSigning();
	
	public Hand getHand();
}
