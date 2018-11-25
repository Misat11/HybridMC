package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerEntityMetadataPacket extends IMinecraftPacket {
	public int getEntityId();

	public EntityMetadata[] getMetadata();
}
