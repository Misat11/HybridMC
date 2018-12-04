package misat11.hybrid.network.java.p393.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerSwitchCameraPacket;

import java.io.IOException;

@Getter
public class ServerSwitchCameraPacket393 extends MinecraftPacket implements ServerSwitchCameraPacket {
    private int cameraEntityId;

    @SuppressWarnings("unused")
    private ServerSwitchCameraPacket393() {
    }

    public ServerSwitchCameraPacket393(int cameraEntityId) {
        this.cameraEntityId = cameraEntityId;
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
