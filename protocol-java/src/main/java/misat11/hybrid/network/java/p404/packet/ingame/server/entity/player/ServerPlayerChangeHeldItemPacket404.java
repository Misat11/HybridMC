package misat11.hybrid.network.java.p404.packet.ingame.server.entity.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player.ServerPlayerChangeHeldItemPacket;

import java.io.IOException;

@Getter
public class ServerPlayerChangeHeldItemPacket404 extends MinecraftPacket implements ServerPlayerChangeHeldItemPacket {
    private int slot;

    @SuppressWarnings("unused")
    private ServerPlayerChangeHeldItemPacket404() {
    }

    public ServerPlayerChangeHeldItemPacket404(int slot) {
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
