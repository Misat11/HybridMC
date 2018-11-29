package misat11.hybrid.network.java.p401.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerMovementPacket;

import java.io.IOException;

@Getter
public class ClientPlayerMovementPacket401 extends MinecraftPacket implements ClientPlayerMovementPacket {
    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;

    protected boolean pos = false;
    protected boolean rot = false;

    protected ClientPlayerMovementPacket401() {
    }

    public ClientPlayerMovementPacket401(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public void read(NetInput in) throws IOException {
        if(this.pos) {
            this.x = in.readDouble();
            this.y = in.readDouble();
            this.z = in.readDouble();
        }

        if(this.rot) {
            this.yaw = in.readFloat();
            this.pitch = in.readFloat();
        }

        this.onGround = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        if(this.pos) {
            out.writeDouble(this.x);
            out.writeDouble(this.y);
            out.writeDouble(this.z);
        }

        if(this.rot) {
            out.writeFloat(this.yaw);
            out.writeFloat(this.pitch);
        }

        out.writeBoolean(this.onGround);
    }
}
