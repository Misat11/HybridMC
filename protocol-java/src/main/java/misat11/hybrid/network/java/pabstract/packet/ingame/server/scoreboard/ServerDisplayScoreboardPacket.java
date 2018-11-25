package misat11.hybrid.network.java.pabstract.packet.ingame.server.scoreboard;

import misat11.hybrid.network.java.pabstract.data.game.scoreboard.ScoreboardPosition;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerDisplayScoreboardPacket extends IMinecraftPacket {

	public ScoreboardPosition getPosition();

	public String getScoreboardName();
}
