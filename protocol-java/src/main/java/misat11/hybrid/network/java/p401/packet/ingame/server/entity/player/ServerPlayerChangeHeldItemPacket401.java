package misat11.hybrid.network.java.p401.packet.ingame.server.entity.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player.ServerPlayerChangeHeldItemPacket;

import java.io.IOException;

@Getter
public class ServerPlayerChangeHeldItemPacket401 extends MinecraftPacket implements ServerPlayerChangeHeldItemPacket {
    private int slot;

    @SuppressWarnings("unused")
    private ServerPlayerChangeHeldItemPacket401() {
    }

    public ServerPlayerChangeHeldItemPacket401(int slot) {
        this.slot = slot;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.slot = in.readByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.slot);
    }
}
