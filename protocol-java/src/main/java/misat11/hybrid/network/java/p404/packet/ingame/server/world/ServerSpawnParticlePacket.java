package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.Particle;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.ParticleType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ServerSpawnParticlePacket extends MinecraftPacket {
    private Particle particle;
    private boolean longDistance;
    private float x;
    private float y;
    private float z;
    private float offsetX;
    private float offsetY;
    private float offsetZ;
    private float velocityOffset;
    private int amount;

    @SuppressWarnings("unused")
    private ServerSpawnParticlePacket() {
    }

    public ServerSpawnParticlePacket(Particle particle, boolean longDistance, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float velocityOffset, int amount) {
        this.particle = particle;
        this.longDistance = longDistance;
        this.x = x;
        this.y = y;
        this.z = z;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.velocityOffset = velocityOffset;
        this.amount = amount;
    }

    public Particle getParticle() {
        return this.particle;
    }

    public boolean isLongDistance() {
        return this.longDistance;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }

    public float getVelocityOffset() {
        return this.velocityOffset;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public void read(NetInput in) throws IOException {
        ParticleType type = MagicValues404.key(ParticleType.class, in.readInt());
        this.longDistance = in.readBoolean();
        this.x = in.readFloat();
        this.y = in.readFloat();
        this.z = in.readFloat();
        this.offsetX = in.readFloat();
        this.offsetY = in.readFloat();
        this.offsetZ = in.readFloat();
        this.velocityOffset = in.readFloat();
        this.amount = in.readInt();
        this.particle = new Particle(type, NetUtil404.readParticleData(in, type));
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(MagicValues404.value(Integer.class, this.particle.getType()));
        out.writeBoolean(this.longDistance);
        out.writeFloat(this.x);
        out.writeFloat(this.y);
        out.writeFloat(this.z);
        out.writeFloat(this.offsetX);
        out.writeFloat(this.offsetY);
        out.writeFloat(this.offsetZ);
        out.writeFloat(this.velocityOffset);
        out.writeInt(this.amount);
        NetUtil404.writeParticleData(out, this.particle.getData(), this.particle.getType());
    }
}
