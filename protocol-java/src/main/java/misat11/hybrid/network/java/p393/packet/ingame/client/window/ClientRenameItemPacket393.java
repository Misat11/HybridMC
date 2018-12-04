package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientRenameItemPacket;

import java.io.IOException;

@Getter
public class ClientRenameItemPacket393 extends MinecraftPacket implements ClientRenameItemPacket {
    private String name;

    @SuppressWarnings("unused")
    private ClientRenameItemPacket393() {
    }

    public ClientRenameItemPacket393(String name) {
        this.name = name;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.name = in.readString();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.name);
    }
}
