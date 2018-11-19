package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import java.util.Map;

import misat11.hybrid.network.java.pabstract.data.game.statistic.Statistic;

public interface ServerStatisticsPacket {
	public Map<Statistic, Integer> getStatistics();
}
