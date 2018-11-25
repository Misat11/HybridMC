package misat11.hybrid.network.java.pabstract.packet.ingame.server.window;

import misat11.hybrid.network.java.pabstract.data.game.window.WindowType;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerOpenWindowPacket extends IMinecraftPacket {
	public int getWindowId();

	public WindowType getType();

	public String getName();

	public int getSlots();

	public int getOwnerEntityId();
}
