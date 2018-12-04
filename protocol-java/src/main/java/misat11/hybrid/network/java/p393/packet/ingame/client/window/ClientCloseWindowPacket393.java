package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientCloseWindowPacket;

import java.io.IOException;

@Getter
public class ClientCloseWindowPacket393 extends MinecraftPacket implements ClientCloseWindowPacket {
    private int windowId;

    @SuppressWarnings("unused")
    private ClientCloseWindowPacket393() {
    }

    public ClientCloseWindowPacket393(int windowId) {
        this.windowId = windowId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
    }
}
