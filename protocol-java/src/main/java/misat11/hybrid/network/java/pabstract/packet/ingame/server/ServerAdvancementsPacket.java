package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import java.util.List;
import java.util.Map;

import misat11.hybrid.network.java.pabstract.data.game.advancement.Advancement;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerAdvancementsPacket extends IMinecraftPacket {
	public boolean doesReset();

	public List<Advancement> getAdvancements();

	public List<String> getRemovedAdvancements();

	public Map<String, Map<String, Long>> getProgress();

	public Map<String, Long> getProgress(String advancementId);

	public Long getAchievedDate(String advancementId, String criterionId);
}
