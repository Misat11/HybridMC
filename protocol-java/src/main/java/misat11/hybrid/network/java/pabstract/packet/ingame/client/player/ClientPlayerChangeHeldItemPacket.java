package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPlayerChangeHeldItemPacket extends IMinecraftPacket {
	public int getSlot();
}
