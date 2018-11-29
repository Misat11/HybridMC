package misat11.hybrid.network.java.p401.packet.status.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.status.client.StatusQueryPacket;

import java.io.IOException;

public class StatusQueryPacket401 extends MinecraftPacket implements StatusQueryPacket {
    public StatusQueryPacket401() {
    }

    @Override
    public void read(NetInput in) throws IOException {
    }

    @Override
    public void write(NetOutput out) throws IOException {
    }
}
