package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerAdvancementTabPacket extends IMinecraftPacket {
	public String getTabId();
}
