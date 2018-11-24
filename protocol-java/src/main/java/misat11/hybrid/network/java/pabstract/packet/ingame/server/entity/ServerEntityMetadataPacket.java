package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;

public interface ServerEntityMetadataPacket {
	public int getEntityId();

	public EntityMetadata[] getMetadata();
}
