package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import java.util.List;

import misat11.hybrid.network.java.pabstract.data.game.entity.attribute.Attribute;

public interface ServerEntityPropertiesPacket {

	public int getEntityId();

	public List<Attribute> getAttributes();
}
