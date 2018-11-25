package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.data.game.window.WindowAction;
import misat11.hybrid.network.java.pabstract.data.game.window.WindowActionParam;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientWindowActionPacket extends IMinecraftPacket {

	public int getWindowId();

	public int getActionId();

	public int getSlot();

	public ItemStack getClickedItem();

	public WindowAction getAction();

	public WindowActionParam getParam();
}
