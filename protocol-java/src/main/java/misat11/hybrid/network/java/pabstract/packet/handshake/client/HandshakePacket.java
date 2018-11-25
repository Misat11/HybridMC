package misat11.hybrid.network.java.pabstract.packet.handshake.client;

import misat11.hybrid.network.java.pabstract.data.handshake.HandshakeIntent;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface HandshakePacket extends IMinecraftPacket {
	
	public int getProtocolVersion();
	
	public String getHostName();
	
	public int getPort();
	
	public HandshakeIntent getIntent();

}
