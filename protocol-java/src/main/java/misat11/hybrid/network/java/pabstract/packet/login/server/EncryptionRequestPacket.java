package misat11.hybrid.network.java.pabstract.packet.login.server;

import java.security.PublicKey;

public interface EncryptionRequestPacket {
	public String getServerId();
	
	public PublicKey getPublicKey();
	
	public byte[] getVerifyToken();
}
