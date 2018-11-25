package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPlayerMovementPacket extends IMinecraftPacket {

	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();

	public boolean isOnGround();
}
