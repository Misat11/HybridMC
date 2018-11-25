package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPluginMessagePacket extends IMinecraftPacket {

	public String getChannel();

	public byte[] getData();
}
