package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientTeleportConfirmPacket extends IMinecraftPacket {
	public int getTeleportId();
}
