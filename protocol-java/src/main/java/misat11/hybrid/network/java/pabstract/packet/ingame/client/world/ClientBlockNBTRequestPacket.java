package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientBlockNBTRequestPacket extends IMinecraftPacket {

	public int getTransactionId();

	public Position getPosition();
}
