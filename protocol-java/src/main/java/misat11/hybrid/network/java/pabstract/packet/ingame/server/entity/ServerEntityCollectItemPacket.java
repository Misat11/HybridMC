package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerEntityCollectItemPacket extends IMinecraftPacket {

	public int getCollectedEntityId();

	public int getCollectorEntityId();

	public int getItemCount();
}
