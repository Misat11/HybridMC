package misat11.hybrid.network.java.pabstract.packet.login.client;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface LoginPluginResponsePacket extends IMinecraftPacket {
	public int getMessageId();
	
	public byte[] getData();
}
