package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerEntityMovementPacket extends IMinecraftPacket {
	public int getEntityId();

	public double getMovementX();

	public double getMovementY();

	public double getMovementZ();

	public float getYaw();

	public float getPitch();

	public boolean isOnGround();
}
