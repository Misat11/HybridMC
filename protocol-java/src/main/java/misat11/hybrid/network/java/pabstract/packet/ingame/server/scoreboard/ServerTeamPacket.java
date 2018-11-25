package misat11.hybrid.network.java.pabstract.packet.ingame.server.scoreboard;

import misat11.hybrid.network.java.pabstract.data.game.scoreboard.CollisionRule;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.NameTagVisibility;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.TeamAction;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.TeamColor;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerTeamPacket extends IMinecraftPacket {
	public String getTeamName();

	public TeamAction getAction();

	public Message getDisplayName();

	public Message getPrefix();

	public Message getSuffix();

	public boolean getFriendlyFire();

	public boolean getSeeFriendlyInvisibles();

	public NameTagVisibility getNameTagVisibility();

	public CollisionRule getCollisionRule();

	public TeamColor getColor();

	public String[] getPlayers();
}
