package misat11.hybrid.network.java.pabstract.packet.login.client;

import java.security.PrivateKey;

import javax.crypto.SecretKey;

public interface EncryptionResponsePacket {
	public SecretKey getSecretKey(PrivateKey privateKey);

	public byte[] getVerifyToken(PrivateKey privateKey);
}
