package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn;

import misat11.hybrid.network.java.pabstract.data.game.entity.type.GlobalEntityType;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSpawnGlobalEntityPacket extends IMinecraftPacket {
	public int getEntityId();

	public GlobalEntityType getType();

	public double getX();

	public double getY();

	public double getZ();
}
