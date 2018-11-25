package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientVehicleMovePacket extends IMinecraftPacket {

	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();
}
