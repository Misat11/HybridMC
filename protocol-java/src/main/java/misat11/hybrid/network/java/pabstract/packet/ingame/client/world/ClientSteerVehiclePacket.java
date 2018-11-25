package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientSteerVehiclePacket extends IMinecraftPacket {

	public float getSideways();

	public float getForward();

	public boolean getJumping();

	public boolean getDismounting();
}
