package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerVehicleMovePacket extends IMinecraftPacket {

	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();
}
