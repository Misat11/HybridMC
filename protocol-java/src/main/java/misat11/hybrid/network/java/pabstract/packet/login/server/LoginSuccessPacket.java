package misat11.hybrid.network.java.pabstract.packet.login.server;

import com.github.steveice10.mc.auth.data.GameProfile;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface LoginSuccessPacket extends IMinecraftPacket {
	public GameProfile getProfile();
}
