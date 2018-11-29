package misat11.hybrid.network.java.p401.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerVehicleMovePacket;

import java.io.IOException;

@Getter
public class ServerVehicleMovePacket401 extends MinecraftPacket implements ServerVehicleMovePacket {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    @SuppressWarnings("unused")
    private ServerVehicleMovePacket401() {
    }

    public ServerVehicleMovePacket401(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
        this.yaw = in.readFloat();
        this.pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        out.writeFloat(this.yaw);
        out.writeFloat(this.pitch);
    }
}
