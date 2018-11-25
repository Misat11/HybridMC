package misat11.hybrid.network.java.pabstract.packet.ingame.server.scoreboard;

import misat11.hybrid.network.java.pabstract.data.game.scoreboard.ScoreboardAction;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerUpdateScorePacket extends IMinecraftPacket {
	public String getEntry();

	public ScoreboardAction getAction();

	public String getObjective();

	public int getValue();
}
