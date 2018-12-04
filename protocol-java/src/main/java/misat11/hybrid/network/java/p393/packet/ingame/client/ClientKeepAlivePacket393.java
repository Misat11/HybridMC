package misat11.hybrid.network.java.p393.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientKeepAlivePacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientKeepAlivePacket393 extends MinecraftPacket implements ClientKeepAlivePacket {
    private long pingId;

    @Override
    public void read(NetInput in) throws IOException {
        this.pingId = in.readLong();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeLong(this.pingId);
    }
}
