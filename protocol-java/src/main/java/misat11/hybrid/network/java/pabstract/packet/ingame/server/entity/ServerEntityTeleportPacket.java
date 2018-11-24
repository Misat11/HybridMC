package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

public interface ServerEntityTeleportPacket {

	public int getEntityId();

	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();

	public boolean isOnGround();
}
