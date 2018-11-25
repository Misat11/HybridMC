package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSetCooldownPacket extends IMinecraftPacket {
	public int getItemId();

	public int getCooldownTicks();
}
