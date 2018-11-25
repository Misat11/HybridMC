package misat11.hybrid.network.java.pabstract.packet.ingame.client;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPluginMessagePacket extends IMinecraftPacket {

    public String getChannel();

    public byte[] getData();
}
