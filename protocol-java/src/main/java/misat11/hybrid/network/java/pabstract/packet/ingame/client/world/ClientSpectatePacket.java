package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

import java.util.UUID;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientSpectatePacket extends IMinecraftPacket {
	public UUID getTarget();
}
