package misat11.hybrid.network.java.pabstract.packet.ingame.server.scoreboard;

import misat11.hybrid.network.java.pabstract.data.game.scoreboard.ObjectiveAction;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.ScoreType;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerScoreboardObjectivePacket extends IMinecraftPacket {

	public String getName();

	public ObjectiveAction getAction();

	public Message getDisplayName();

	public ScoreType getType();
}
