package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.packet.MinecraftPacket;

import java.io.IOException;

public class ServerSwitchCameraPacket extends MinecraftPacket {
    private int cameraEntityId;

    @SuppressWarnings("unused")
    private ServerSwitchCameraPacket() {
    }

    public ServerSwitchCameraPacket(int cameraEntityId) {
        this.cameraEntityId = cameraEntityId;
    }

    public int getCameraEntityId() {
        return this.cameraEntityId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.cameraEntityId = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.cameraEntityId);
    }
}
