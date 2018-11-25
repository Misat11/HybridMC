package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientSteerBoatPacket extends IMinecraftPacket {
	public boolean isRightPaddleTurning();

	public boolean isLeftPaddleTurning();
}
