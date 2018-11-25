package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.BlockBreakStage;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerBlockBreakAnimPacket extends IMinecraftPacket {
	public int getBreakerEntityId();

	public Position getPosition();

	public BlockBreakStage getStage();
}
