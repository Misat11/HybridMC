package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.CombatState;
import misat11.hybrid.network.java.pabstract.data.message.Message;

public interface ServerCombatPacket {

	public CombatState getCombatState();

	public int getEntityId();

	public int getDuration();

	public int getPlayerId();

	public Message getMessage();
}
