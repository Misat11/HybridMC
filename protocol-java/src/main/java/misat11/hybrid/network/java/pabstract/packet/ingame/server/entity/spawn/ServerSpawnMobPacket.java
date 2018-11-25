package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn;

import java.util.UUID;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.MobType;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSpawnMobPacket extends IMinecraftPacket {
	public int getEntityId();

	public UUID getUUID();

	public MobType getType();

	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();

	public float getHeadYaw();

	public double getMotionX();

	public double getMotionY();

	public double getMotionZ();

	public EntityMetadata[] getMetadata();
}
