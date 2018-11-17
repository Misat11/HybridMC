package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

public interface ClientPlayerAbilitiesPacket {

	public boolean getInvincible();

	public boolean getCanFly();

	public boolean getFlying();

	public boolean getCreative();

	public float getFlySpeed();

	public float getWalkSpeed();
}
