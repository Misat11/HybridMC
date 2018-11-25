package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn;

import java.util.UUID;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.PaintingType;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.object.HangingDirection;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSpawnPaintingPacket extends IMinecraftPacket {
	public int getEntityId();

	public UUID getUUID();

	public PaintingType getPaintingType();

	public Position getPosition();

	public HangingDirection getDirection();
}
