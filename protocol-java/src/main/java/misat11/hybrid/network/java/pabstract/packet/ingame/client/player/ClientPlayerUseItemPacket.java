package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPlayerUseItemPacket extends IMinecraftPacket {
	public Hand getHand();
}
