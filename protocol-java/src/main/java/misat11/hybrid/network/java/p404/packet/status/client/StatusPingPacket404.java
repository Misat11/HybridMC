package misat11.hybrid.network.java.p404.packet.status.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.status.client.StatusPingPacket;

import java.io.IOException;

@Getter
public class StatusPingPacket404 extends MinecraftPacket implements StatusPingPacket {
    private long pingTime;

    @SuppressWarnings("unused")
    private StatusPingPacket404() {
    }

    public StatusPingPacket404(long pingTime) {
        this.pingTime = pingTime;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.pingTime = in.readLong();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeLong(this.pingTime);
    }
}
