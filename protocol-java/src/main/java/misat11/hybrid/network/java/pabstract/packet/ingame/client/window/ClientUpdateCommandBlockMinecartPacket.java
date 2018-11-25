package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientUpdateCommandBlockMinecartPacket extends IMinecraftPacket {

	public int getEntityId();

	public String getCommand();

	public boolean isDoesTrackOutput();
}
