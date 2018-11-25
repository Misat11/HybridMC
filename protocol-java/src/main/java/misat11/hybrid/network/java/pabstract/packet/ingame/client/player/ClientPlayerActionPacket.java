package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.PlayerAction;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockFace;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPlayerActionPacket extends IMinecraftPacket {

	public PlayerAction getAction();

	public Position getPosition();

	public BlockFace getFace();
}
