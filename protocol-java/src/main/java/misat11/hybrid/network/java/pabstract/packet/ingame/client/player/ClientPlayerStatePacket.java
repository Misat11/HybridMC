package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.PlayerState;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPlayerStatePacket extends IMinecraftPacket {

	public int getEntityId();

	public PlayerState getState();

	public int getJumpBoost();
}
