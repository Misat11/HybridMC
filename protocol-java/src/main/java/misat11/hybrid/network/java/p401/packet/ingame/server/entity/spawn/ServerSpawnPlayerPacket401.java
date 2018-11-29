package misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;

import java.io.IOException;
import java.util.UUID;

@Getter
public class ServerSpawnPlayerPacket401 extends MinecraftPacket implements ServerSpawnPlayerPacket {
    private int entityId;
    private UUID UUID;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private EntityMetadata metadata[];

    @SuppressWarnings("unused")
    private ServerSpawnPlayerPacket401() {
    }

    public ServerSpawnPlayerPacket401(int entityId, UUID uuid, double x, double y, double z, float yaw, float pitch, EntityMetadata metadata[]) {
        this.entityId = entityId;
        this.UUID = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.metadata = metadata;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.UUID = in.readUUID();
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
        this.yaw = in.readByte() * 360 / 256f;
        this.pitch = in.readByte() * 360 / 256f;
        this.metadata = getUtil().readEntityMetadata(in, getMagic());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeUUID(this.UUID);
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        out.writeByte((byte) (this.yaw * 256 / 360));
        out.writeByte((byte) (this.pitch * 256 / 360));
        getUtil().writeEntityMetadata(out, this.metadata, getMagic());
    }
}
