package misat11.hybrid.network.java.p404.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientPluginMessagePacket;

import java.io.IOException;

@Getter
public class ClientPluginMessagePacket404 extends MinecraftPacket implements ClientPluginMessagePacket {
    private String channel;
    private byte data[];

    @SuppressWarnings("unused")
    private ClientPluginMessagePacket404() {
    }

    public ClientPluginMessagePacket404(String channel, byte data[]) {
        this.channel = channel;
        this.data = data;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.channel = in.readString();
        this.data = in.readBytes(in.available());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.channel);
        out.writeBytes(this.data);
    }
}
