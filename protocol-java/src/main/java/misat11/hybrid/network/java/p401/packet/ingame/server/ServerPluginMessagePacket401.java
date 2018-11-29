package misat11.hybrid.network.java.p401.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerPluginMessagePacket;

import java.io.IOException;

@Getter
public class ServerPluginMessagePacket401 extends MinecraftPacket implements ServerPluginMessagePacket {
    private String channel;
    private byte data[];

    @SuppressWarnings("unused")
    private ServerPluginMessagePacket401() {
    }

    public ServerPluginMessagePacket401(String channel, byte data[]) {
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
