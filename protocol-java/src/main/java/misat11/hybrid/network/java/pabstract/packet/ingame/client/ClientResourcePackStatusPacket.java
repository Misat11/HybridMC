package misat11.hybrid.network.java.pabstract.packet.ingame.client;

import misat11.hybrid.network.java.pabstract.data.game.ResourcePackStatus;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientResourcePackStatusPacket extends IMinecraftPacket {
	public ResourcePackStatus getStatus();
}
