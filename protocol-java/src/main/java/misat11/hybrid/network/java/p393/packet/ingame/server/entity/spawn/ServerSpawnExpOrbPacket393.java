package misat11.hybrid.network.java.p393.packet.ingame.server.entity.spawn;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket;

import java.io.IOException;

@Getter
public class ServerSpawnExpOrbPacket393 extends MinecraftPacket implements ServerSpawnExpOrbPacket {
    private int entityId;
    private double x;
    private double y;
    private double z;
    private int exp;

    @SuppressWarnings("unused")
    private ServerSpawnExpOrbPacket393() {
    }

    public ServerSpawnExpOrbPacket393(int entityId, double x, double y, double z, int exp) {
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.exp = exp;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
        this.exp = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        out.writeShort(this.exp);
    }
}
