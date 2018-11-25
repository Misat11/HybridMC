package misat11.hybrid.network.java.pabstract.packet.login.client;

import java.security.PrivateKey;

import javax.crypto.SecretKey;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface EncryptionResponsePacket extends IMinecraftPacket {
	public SecretKey getSecretKey(PrivateKey privateKey);

	public byte[] getVerifyToken(PrivateKey privateKey);
}
