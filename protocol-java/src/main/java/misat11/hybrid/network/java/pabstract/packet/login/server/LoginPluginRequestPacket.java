package misat11.hybrid.network.java.pabstract.packet.login.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface LoginPluginRequestPacket extends IMinecraftPacket {
	public int getMessageId();
	
	public String getChannel();
	
	public byte[] getData();
}
