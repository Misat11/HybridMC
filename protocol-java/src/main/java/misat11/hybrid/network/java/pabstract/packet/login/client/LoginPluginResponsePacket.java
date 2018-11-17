package misat11.hybrid.network.java.pabstract.packet.login.client;

public interface LoginPluginResponsePacket {
	public int getMessageId();
	
	public byte[] getData();
}
