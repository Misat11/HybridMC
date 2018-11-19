package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.pabstract.data.game.setting.Difficulty;
import misat11.hybrid.network.java.pabstract.data.game.world.WorldType;

public interface ServerJoinGamePacket {

	public int getEntityId();

	public boolean getHardcore();

	public GameMode getGameMode();

	public int getDimension();

	public Difficulty getDifficulty();

	public int getMaxPlayers();

	public WorldType getWorldType();

	public boolean getReducedDebugInfo();
}
