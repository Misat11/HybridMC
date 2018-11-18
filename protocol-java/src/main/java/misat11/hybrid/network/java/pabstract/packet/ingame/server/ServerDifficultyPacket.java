package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.setting.Difficulty;

public interface ServerDifficultyPacket {
	public Difficulty getDifficulty();
}
