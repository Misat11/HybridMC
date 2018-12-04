package misat11.hybrid.network.java.p393.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.Particle;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.ParticleType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerSpawnParticlePacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerSpawnParticlePacket393 extends MinecraftPacket implements ServerSpawnParticlePacket {
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

    @Override
    public void read(NetInput in) throws IOException {
        ParticleType type = getMagic().key(ParticleType.class, in.readInt());
        this.longDistance = in.readBoolean();
        this.x = in.readFloat();
        this.y = in.readFloat();
        this.z = in.readFloat();
        this.offsetX = in.readFloat();
        this.offsetY = in.readFloat();
        this.offsetZ = in.readFloat();
        this.velocityOffset = in.readFloat();
        this.amount = in.readInt();
        this.particle = new Particle(type, getUtil().readParticleData(in, type));
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(getMagic().value(Integer.class, this.particle.getType()));
        out.writeBoolean(this.longDistance);
        out.writeFloat(this.x);
        out.writeFloat(this.y);
        out.writeFloat(this.z);
        out.writeFloat(this.offsetX);
        out.writeFloat(this.offsetY);
        out.writeFloat(this.offsetZ);
        out.writeFloat(this.velocityOffset);
        out.writeInt(this.amount);
        getUtil().writeParticleData(out, this.particle.getData(), this.particle.getType());
    }
}
