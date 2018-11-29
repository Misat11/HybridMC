package misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.GlobalEntityType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket;

import java.io.IOException;

@Getter
public class ServerSpawnGlobalEntityPacket401 extends MinecraftPacket implements ServerSpawnGlobalEntityPacket {
    private int entityId;
    private GlobalEntityType type;
    private double x;
    private double y;
    private double z;

    @SuppressWarnings("unused")
    private ServerSpawnGlobalEntityPacket401() {
    }

    public ServerSpawnGlobalEntityPacket401(int entityId, GlobalEntityType type, double x, double y, double z) {
        this.entityId = entityId;
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.type = getMagic().key(GlobalEntityType.class, in.readByte());
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeByte(getMagic().value(Integer.class, this.type));
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
    }
}
