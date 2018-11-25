package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerHealthPacket extends IMinecraftPacket {

	public float getHealth();

	public int getFood();

	public float getSaturation();
}
