package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.data.game.entity.EntityStatus;

public interface ServerEntityStatusPacket {

	public int getEntityId();

	public EntityStatus getStatus();
}
