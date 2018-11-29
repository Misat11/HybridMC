package misat11.hybrid.network.java.p401.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientRenameItemPacket;

import java.io.IOException;

@Getter
public class ClientRenameItemPacket401 extends MinecraftPacket implements ClientRenameItemPacket {
    private String name;

    @SuppressWarnings("unused")
    private ClientRenameItemPacket401() {
    }

    public ClientRenameItemPacket401(String name) {
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
