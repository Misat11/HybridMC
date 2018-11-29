package misat11.hybrid.network.java.p401.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.world.ClientSteerVehiclePacket;

import java.io.IOException;

@Getter
public class ClientSteerVehiclePacket401 extends MinecraftPacket implements ClientSteerVehiclePacket {
    private float sideways;
    private float forward;
    private boolean jump;
    private boolean dismount;

    @SuppressWarnings("unused")
    private ClientSteerVehiclePacket401() {
    }

    public ClientSteerVehiclePacket401(float sideways, float forward, boolean jump, boolean dismount) {
        this.sideways = sideways;
        this.forward = forward;
        this.jump = jump;
        this.dismount = dismount;
    }

    public boolean getJumping() {
        return this.jump;
    }

    public boolean getDismounting() {
        return this.dismount;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.sideways = in.readFloat();
        this.forward = in.readFloat();
        int flags = in.readUnsignedByte();
        this.jump = (flags & 1) > 0;
        this.dismount = (flags & 2) > 0;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat(this.sideways);
        out.writeFloat(this.forward);
        byte flags = 0;
        if(this.jump) {
            flags = (byte) (flags | 1);
        }

        if(this.dismount) {
            flags = (byte) (flags | 2);
        }

        out.writeByte(flags);
    }
}
