package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

public interface ClientPrepareCraftingGridPacket {

	public int getWindowId();

	public String getRecipeId();

	public boolean doesMakeAll();
}
