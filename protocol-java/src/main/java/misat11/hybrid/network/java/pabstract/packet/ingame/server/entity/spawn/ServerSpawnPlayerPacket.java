package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn;

import java.util.UUID;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSpawnPlayerPacket extends IMinecraftPacket {
	public int getEntityId();

	public UUID getUUID();

	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();

	public EntityMetadata[] getMetadata();
}
