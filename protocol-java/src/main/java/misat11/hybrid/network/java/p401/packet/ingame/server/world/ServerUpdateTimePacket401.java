package misat11.hybrid.network.java.p401.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerUpdateTimePacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerUpdateTimePacket401 extends MinecraftPacket implements ServerUpdateTimePacket {
    private long worldAge;
    private long time;

    @Override
    public void read(NetInput in) throws IOException {
        this.worldAge = in.readLong();
        this.time = in.readLong();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeLong(this.worldAge);
        out.writeLong(this.time);
    }
}
