package misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.object.*;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket;

import java.io.IOException;
import java.util.UUID;

@Getter
public class ServerSpawnObjectPacket404 extends MinecraftPacket implements ServerSpawnObjectPacket {
    private int entityId;
    private UUID UUID;
    private ObjectType type;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;
    private ObjectData data;
    private double motionX;
    private double motionY;
    private double motionZ;

    @SuppressWarnings("unused")
    private ServerSpawnObjectPacket404() {
    }

    public ServerSpawnObjectPacket404(int entityId, UUID uuid, ObjectType type, double x, double y, double z, float yaw, float pitch) {
        this(entityId, uuid, type, null, x, y, z, yaw, pitch, 0, 0, 0);
    }

    public ServerSpawnObjectPacket404(int entityId, UUID uuid, ObjectType type, ObjectData data, double x, double y, double z, float yaw, float pitch) {
        this(entityId, uuid, type, data, x, y, z, yaw, pitch, 0, 0, 0);
    }

    public ServerSpawnObjectPacket404(int entityId, UUID uuid, ObjectType type, double x, double y, double z, float yaw, float pitch, double motX, double motY, double motZ) {
        this(entityId, uuid, type, new ObjectData() {
        }, x, y, z, yaw, pitch, motX, motY, motZ);
    }

    public ServerSpawnObjectPacket404(int entityId, UUID uuid, ObjectType type, ObjectData data, double x, double y, double z, float yaw, float pitch, double motX, double motY, double motZ) {
        this.entityId = entityId;
        this.UUID = uuid;
        this.type = type;
        this.data = data;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.motionX = motX;
        this.motionY = motY;
        this.motionZ = motZ;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.UUID = in.readUUID();
        this.type = MagicValues404.key(ObjectType.class, in.readByte());
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
        this.pitch = in.readByte() * 360 / 256f;
        this.yaw = in.readByte() * 360 / 256f;

        int data = in.readInt();
        if(this.type == ObjectType.MINECART) {
            this.data = MagicValues404.key(MinecartType.class, data);
        } else if(this.type == ObjectType.ITEM_FRAME) {
            this.data = MagicValues404.key(HangingDirection.class, data);
        } else if(this.type == ObjectType.FALLING_BLOCK) {
            this.data = new FallingBlockData(data & 65535, data >> 16);
        } else if(this.type == ObjectType.POTION) {
            this.data = new SplashPotionData(data);
        } else if(this.type == ObjectType.SPECTRAL_ARROW || this.type == ObjectType.TIPPED_ARROW || this.type == ObjectType.GHAST_FIREBALL || this.type == ObjectType.BLAZE_FIREBALL || this.type == ObjectType.DRAGON_FIREBALL || this.type == ObjectType.WITHER_HEAD_PROJECTILE || this.type == ObjectType.FISH_HOOK) {
            this.data = new ProjectileData(data);
        } else {
            this.data = new GenericObjectData(data);
        }

        this.motionX = in.readShort() / 8000D;
        this.motionY = in.readShort() / 8000D;
        this.motionZ = in.readShort() / 8000D;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeUUID(this.UUID);
        out.writeByte(MagicValues404.value(Integer.class, this.type));
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        out.writeByte((byte) (this.pitch * 256 / 360));
        out.writeByte((byte) (this.yaw * 256 / 360));

        int data = 0;
        if(this.data instanceof MinecartType) {
            data = MagicValues404.value(Integer.class, (Enum<?>) this.data);
        } else if(this.data instanceof HangingDirection) {
            data = MagicValues404.value(Integer.class, (Enum<?>) this.data);
        } else if(this.data instanceof FallingBlockData) {
            data = ((FallingBlockData) this.data).getId() | ((FallingBlockData) this.data).getMetadata() << 16;
        } else if(this.data instanceof SplashPotionData) {
            data = ((SplashPotionData) this.data).getPotionData();
        } else if(this.data instanceof ProjectileData) {
            data = ((ProjectileData) this.data).getOwnerId();
        } else if(this.data instanceof GenericObjectData) {
            data = ((GenericObjectData) this.data).getValue();
        }

        out.writeInt(data);

        out.writeShort((int) (this.motionX * 8000));
        out.writeShort((int) (this.motionY * 8000));
        out.writeShort((int) (this.motionZ * 8000));
    }
}
