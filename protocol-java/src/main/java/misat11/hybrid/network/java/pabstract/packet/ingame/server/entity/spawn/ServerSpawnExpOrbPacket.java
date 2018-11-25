package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSpawnExpOrbPacket extends IMinecraftPacket {
	public int getEntityId();

	public double getX();

	public double getY();

	public double getZ();

	public int getExp();
}
