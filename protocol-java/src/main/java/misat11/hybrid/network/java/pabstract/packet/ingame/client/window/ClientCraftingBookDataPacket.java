package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.data.game.window.CraftingBookDataType;

public interface ClientCraftingBookDataPacket {
	
    public CraftingBookDataType getType();

    public String getRecipeId();

    public boolean isCraftingBookOpen();

    public boolean isFilterCraftingActive();

    public boolean isSmeltingBookOpen();

    public boolean isFilterSmeltingActive();
}
