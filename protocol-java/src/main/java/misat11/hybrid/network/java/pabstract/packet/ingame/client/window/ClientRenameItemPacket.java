package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientRenameItemPacket extends IMinecraftPacket {
	public String getName();
}
