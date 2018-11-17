package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import java.util.Map;

public interface ServerDeclareTagsPacket {

	public Map<String, int[]> getBlockTags();

	public Map<String, int[]> getItemTags();

	public Map<String, int[]> getFluidTags();
}
