package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

public interface ClientPlayerMovementPacket {

	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();

	public boolean isOnGround();
}
