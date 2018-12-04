package misat11.hybrid.network.java.p393.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerResourcePackSendPacket;

import java.io.IOException;

@Getter
public class ServerResourcePackSendPacket393 extends MinecraftPacket implements ServerResourcePackSendPacket {
    private String url;
    private String hash;

    @SuppressWarnings("unused")
    private ServerResourcePackSendPacket393() {
    }

    public ServerResourcePackSendPacket393(String url, String hash) {
        this.url = url;
        this.hash = hash;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.url = in.readString();
        this.hash = in.readString();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.url);
        out.writeString(this.hash);
    }
}
