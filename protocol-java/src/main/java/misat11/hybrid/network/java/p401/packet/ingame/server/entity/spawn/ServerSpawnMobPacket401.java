package misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.MobType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn.ServerSpawnMobPacket;

import java.io.IOException;
import java.util.UUID;

@Getter
public class ServerSpawnMobPacket401 extends MinecraftPacket implements ServerSpawnMobPacket {
    private int entityId;
    private UUID UUID;
    private MobType type;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;
    private float headYaw;
    private double motionX;
    private double motionY;
    private double motionZ;
    private EntityMetadata metadata[];

    @SuppressWarnings("unused")
    private ServerSpawnMobPacket401() {
    }

    public ServerSpawnMobPacket401(int entityId, UUID uuid, MobType type, double x, double y, double z, float yaw, float pitch, float headYaw, double motX, double motY, double motZ, EntityMetadata metadata[]) {
        this.entityId = entityId;
        this.UUID = uuid;
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.headYaw = headYaw;
        this.motionX = motX;
        this.motionY = motY;
        this.motionZ = motZ;
        this.metadata = metadata;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.UUID = in.readUUID();
        this.type = getMagic().key(MobType.class, in.readVarInt());
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
        this.yaw = in.readByte() * 360 / 256f;
        this.pitch = in.readByte() * 360 / 256f;
        this.headYaw = in.readByte() * 360 / 256f;
        this.motionX = in.readShort() / 8000D;
        this.motionY = in.readShort() / 8000D;
        this.motionZ = in.readShort() / 8000D;
        this.metadata = getUtil().readEntityMetadata(in, getMagic());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeUUID(this.UUID);
        out.writeVarInt(getMagic().value(Integer.class, this.type));
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        out.writeByte((byte) (this.yaw * 256 / 360));
        out.writeByte((byte) (this.pitch * 256 / 360));
        out.writeByte((byte) (this.headYaw * 256 / 360));
        out.writeShort((int) (this.motionX * 8000));
        out.writeShort((int) (this.motionY * 8000));
        out.writeShort((int) (this.motionZ * 8000));
        getUtil().writeEntityMetadata(out, this.metadata, getMagic());
    }
}
