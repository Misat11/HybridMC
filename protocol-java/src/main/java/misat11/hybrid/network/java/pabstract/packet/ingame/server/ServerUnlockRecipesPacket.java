package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import java.util.List;

import misat11.hybrid.network.java.pabstract.data.game.UnlockRecipesAction;

public interface ServerUnlockRecipesPacket {
	public UnlockRecipesAction getAction();

	public List<String> getRecipes();

	public List<String> getAlreadyKnownRecipes();

	public boolean getOpenCraftingBook();

	public boolean getActivateCraftingFiltering();

	public boolean getOpenSmeltingBook();

	public boolean getActivateSmeltingFiltering();
}
