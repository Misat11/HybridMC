package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerChangeHeldItemPacket extends IMinecraftPacket {
	public int getSlot();
}
