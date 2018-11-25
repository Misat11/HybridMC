package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSwitchCameraPacket extends IMinecraftPacket {
	public int getCameraEntityId();
}
