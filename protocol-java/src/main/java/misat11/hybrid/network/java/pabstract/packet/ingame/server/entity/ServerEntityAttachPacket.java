package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerEntityAttachPacket extends IMinecraftPacket {

	public int getEntityId();

	public int getAttachedToId();
}
