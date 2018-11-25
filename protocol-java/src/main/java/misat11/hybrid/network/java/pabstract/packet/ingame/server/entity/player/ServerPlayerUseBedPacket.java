package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerUseBedPacket extends IMinecraftPacket {
	public int getEntityId();

	public Position getPosition();
}
