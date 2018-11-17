package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.PlayerState;

public interface ClientPlayerStatePacket {

	public int getEntityId();

	public PlayerState getState();

	public int getJumpBoost();
}
