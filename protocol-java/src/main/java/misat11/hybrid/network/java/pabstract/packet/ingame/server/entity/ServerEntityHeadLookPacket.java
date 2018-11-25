package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerEntityHeadLookPacket extends IMinecraftPacket {

	public int getEntityId();

	public float getHeadYaw();
}
