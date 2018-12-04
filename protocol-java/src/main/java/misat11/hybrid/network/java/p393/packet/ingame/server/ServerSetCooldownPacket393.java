package misat11.hybrid.network.java.p393.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerSetCooldownPacket;

import java.io.IOException;

@Getter
public class ServerSetCooldownPacket393 extends MinecraftPacket implements ServerSetCooldownPacket {
    private int itemId;
    private int cooldownTicks;

    @SuppressWarnings("unused")
    private ServerSetCooldownPacket393() {
    }

    public ServerSetCooldownPacket393(int itemId, int cooldownTicks) {
        this.itemId = itemId;
        this.cooldownTicks = cooldownTicks;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.itemId = in.readVarInt();
        this.cooldownTicks = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.itemId);
        out.writeVarInt(this.cooldownTicks);
    }
}
