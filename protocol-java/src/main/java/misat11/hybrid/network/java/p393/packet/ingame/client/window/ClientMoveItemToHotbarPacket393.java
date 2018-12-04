package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientMoveItemToHotbarPacket;

import java.io.IOException;

@Getter
public class ClientMoveItemToHotbarPacket393 extends MinecraftPacket implements ClientMoveItemToHotbarPacket {
    private int slot;

    @SuppressWarnings("unused")
    private ClientMoveItemToHotbarPacket393() {
    }

    public ClientMoveItemToHotbarPacket393(int slot) {
        this.slot = slot;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.slot = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.slot);
    }
}
