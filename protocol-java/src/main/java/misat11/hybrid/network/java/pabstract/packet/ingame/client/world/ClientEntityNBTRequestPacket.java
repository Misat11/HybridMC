package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientEntityNBTRequestPacket extends IMinecraftPacket {

	public int getTransactionId();

	public int getEntityId();
}
