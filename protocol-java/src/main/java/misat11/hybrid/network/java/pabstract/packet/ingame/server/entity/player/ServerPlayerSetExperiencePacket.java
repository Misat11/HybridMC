package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerSetExperiencePacket extends IMinecraftPacket {
	public float getSlot();

	public int getLevel();

	public int getTotalExperience();
}
