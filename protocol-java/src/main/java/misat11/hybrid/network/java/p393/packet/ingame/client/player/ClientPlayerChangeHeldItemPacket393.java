package misat11.hybrid.network.java.p393.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerChangeHeldItemPacket;

import java.io.IOException;

@Getter
public class ClientPlayerChangeHeldItemPacket393 extends MinecraftPacket implements ClientPlayerChangeHeldItemPacket {
    private int slot;

    @SuppressWarnings("unused")
    private ClientPlayerChangeHeldItemPacket393() {
    }

    public ClientPlayerChangeHeldItemPacket393(int slot) {
        this.slot = slot;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.slot = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeShort(this.slot);
    }
}
