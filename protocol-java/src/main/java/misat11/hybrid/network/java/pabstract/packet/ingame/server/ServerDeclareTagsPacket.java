package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import java.util.Map;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerDeclareTagsPacket extends IMinecraftPacket {

	public Map<String, int[]> getBlockTags();

	public Map<String, int[]> getItemTags();

	public Map<String, int[]> getFluidTags();
}
