package misat11.hybrid.network.java.pabstract.packet.handshake.client;

import misat11.hybrid.network.java.pabstract.data.handshake.HandshakeIntent;

public interface HandshakePacket {
	
	public int getProtocolVersion();
	
	public String getHostName();
	
	public int getPort();
	
	public HandshakeIntent getIntent();

}
