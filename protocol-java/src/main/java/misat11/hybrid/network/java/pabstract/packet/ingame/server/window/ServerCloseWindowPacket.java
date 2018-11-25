package misat11.hybrid.network.java.pabstract.packet.ingame.server.window;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerCloseWindowPacket extends IMinecraftPacket {
	public int getWindowId();
}
