package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

public interface ClientSteerVehiclePacket {

	public float getSideways();

	public float getForward();

	public boolean getJumping();

	public boolean getDismounting();
}
