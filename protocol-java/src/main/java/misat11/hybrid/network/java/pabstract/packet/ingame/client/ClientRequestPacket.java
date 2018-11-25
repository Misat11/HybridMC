package misat11.hybrid.network.java.pabstract.packet.ingame.client;

import misat11.hybrid.network.java.pabstract.data.game.ClientRequest;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientRequestPacket extends IMinecraftPacket {
	public ClientRequest getRequest();
}
