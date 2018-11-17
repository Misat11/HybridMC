package misat11.hybrid.network.java.pabstract.packet.login.server;

public interface LoginPluginRequestPacket {
	public int getMessageId();
	
	public String getChannel();
	
	public byte[] getData();
}
