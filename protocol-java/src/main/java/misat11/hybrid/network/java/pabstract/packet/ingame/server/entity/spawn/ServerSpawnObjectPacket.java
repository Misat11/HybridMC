package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn;

import java.util.UUID;

import misat11.hybrid.network.java.pabstract.data.game.entity.type.object.ObjectData;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.object.ObjectType;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSpawnObjectPacket extends IMinecraftPacket {
	public int getEntityId();

	public UUID getUUID();

	public ObjectType getType();

	public ObjectData getData();

	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();

	public double getMotionX();

	public double getMotionY();

	public double getMotionZ();
}
