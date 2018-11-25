package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import java.util.Map;

import misat11.hybrid.network.java.pabstract.data.game.statistic.Statistic;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerStatisticsPacket extends IMinecraftPacket {
	public Map<Statistic, Integer> getStatistics();
}
