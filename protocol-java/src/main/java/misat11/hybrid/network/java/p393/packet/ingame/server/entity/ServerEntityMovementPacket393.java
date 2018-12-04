package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityMovementPacket;

import java.io.IOException;

@Getter
public class ServerEntityMovementPacket393 extends MinecraftPacket implements ServerEntityMovementPacket {
    protected int entityId;
    protected double movementX;
    protected double movementY;
    protected double movementZ;
    protected float yaw;
    protected float pitch;
    protected boolean pos = false;
    protected boolean rot = false;
    private boolean onGround;

    protected ServerEntityMovementPacket393() {
    }

    public ServerEntityMovementPacket393(int entityId, boolean onGround) {
        this.entityId = entityId;
        this.onGround = onGround;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        if(this.pos) {
            this.movementX = in.readShort() / 4096D;
            this.movementY = in.readShort() / 4096D;
            this.movementZ = in.readShort() / 4096D;
        }

        if(this.rot) {
            this.yaw = in.readByte() * 360 / 256f;
            this.pitch = in.readByte() * 360 / 256f;
        }

        if(this.pos || this.rot) {
            this.onGround = in.readBoolean();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        if(this.pos) {
            out.writeShort((int) (this.movementX * 4096));
            out.writeShort((int) (this.movementY * 4096));
            out.writeShort((int) (this.movementZ * 4096));
        }

        if(this.rot) {
            out.writeByte((byte) (this.yaw * 256 / 360));
            out.writeByte((byte) (this.pitch * 256 / 360));
        }

        if(this.pos || this.rot) {
            out.writeBoolean(this.onGround);
        }
    }
}
