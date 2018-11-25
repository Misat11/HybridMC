package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockFace;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPlayerPlaceBlockPacket extends IMinecraftPacket {

	public Position getPosition();

	public BlockFace getFace();

	public Hand getHand();

	public float getCursorX();

	public float getCursorY();

	public float getCursorZ();
}
