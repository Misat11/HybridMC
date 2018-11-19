package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.pabstract.data.game.setting.Difficulty;
import misat11.hybrid.network.java.pabstract.data.game.world.WorldType;

public interface ServerRespawnPacket {

	public int getDimension();

	public Difficulty getDifficulty();

	public GameMode getGameMode();

	public WorldType getWorldType();
}
