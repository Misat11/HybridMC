package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerDeclareCommandsPacket extends IMinecraftPacket {
	@Deprecated // This packet isn't fully implemented, please send a PR if you need to use it
    public byte[] getData();
}
