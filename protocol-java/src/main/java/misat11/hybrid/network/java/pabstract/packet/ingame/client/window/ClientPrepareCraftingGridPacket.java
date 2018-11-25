package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPrepareCraftingGridPacket extends IMinecraftPacket {

	public int getWindowId();

	public String getRecipeId();

	public boolean doesMakeAll();
}
