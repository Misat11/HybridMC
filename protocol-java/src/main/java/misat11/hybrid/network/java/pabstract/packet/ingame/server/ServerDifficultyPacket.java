package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.setting.Difficulty;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerDifficultyPacket extends IMinecraftPacket {
	public Difficulty getDifficulty();
}
