package misat11.hybrid.network.java.p404.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientKeepAlivePacket;

import java.io.IOException;

@Getter
public class ClientKeepAlivePacket404 extends MinecraftPacket implements ClientKeepAlivePacket {
    private long pingId;

    @SuppressWarnings("unused")
    private ClientKeepAlivePacket404() {
    }

    public ClientKeepAlivePacket404(long pingId) {
        this.pingId = pingId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.pingId = in.readLong();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeLong(this.pingId);
    }
}
