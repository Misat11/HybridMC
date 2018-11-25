package misat11.hybrid.network.java.pabstract.packet.login.client;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface LoginStartPacket extends IMinecraftPacket {
	public String getUsername();
}
