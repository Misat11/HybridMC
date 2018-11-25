package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerEntityVelocityPacket extends IMinecraftPacket {

	public int getEntityId();

	public double getMotionX();

	public double getMotionY();

	public double getMotionZ();
}
