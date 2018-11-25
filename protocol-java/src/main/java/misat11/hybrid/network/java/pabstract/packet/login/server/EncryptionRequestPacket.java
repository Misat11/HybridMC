package misat11.hybrid.network.java.pabstract.packet.login.server;

import java.security.PublicKey;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface EncryptionRequestPacket extends IMinecraftPacket {
	public String getServerId();
	
	public PublicKey getPublicKey();
	
	public byte[] getVerifyToken();
}
