package misat11.hybrid.network.java.pabstract.packet.ingame.client.world;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;

public interface ClientUpdateSignPacket {
	public Position getPosition();

	public String[] getLines();
}
