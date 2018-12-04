package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityVelocityPacket;

import java.io.IOException;

@Getter
public class ServerEntityVelocityPacket393 extends MinecraftPacket implements ServerEntityVelocityPacket {
    private int entityId;
    private double motionX;
    private double motionY;
    private double motionZ;

    @SuppressWarnings("unused")
    private ServerEntityVelocityPacket393() {
    }

    public ServerEntityVelocityPacket393(int entityId, double motX, double motY, double motZ) {
        this.entityId = entityId;
        this.motionX = motX;
        this.motionY = motY;
        this.motionZ = motZ;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.motionX = in.readShort() / 8000D;
        this.motionY = in.readShort() / 8000D;
        this.motionZ = in.readShort() / 8000D;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeShort((int) (this.motionX * 8000));
        out.writeShort((int) (this.motionY * 8000));
        out.writeShort((int) (this.motionZ * 8000));
    }
}
