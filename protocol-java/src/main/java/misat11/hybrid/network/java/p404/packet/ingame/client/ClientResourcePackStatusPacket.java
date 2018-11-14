package misat11.hybrid.network.java.p404.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues;
import misat11.hybrid.network.java.p404.data.game.ResourcePackStatus;
import misat11.hybrid.network.java.p404.packet.MinecraftPacket;

import java.io.IOException;

public class ClientResourcePackStatusPacket extends MinecraftPacket {
    private ResourcePackStatus status;

    @SuppressWarnings("unused")
    private ClientResourcePackStatusPacket() {
    }

    public ClientResourcePackStatusPacket(ResourcePackStatus status) {
        this.status = status;
    }

    public ResourcePackStatus getStatus() {
        return this.status;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.status = MagicValues.key(ResourcePackStatus.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, this.status));
    }
}
