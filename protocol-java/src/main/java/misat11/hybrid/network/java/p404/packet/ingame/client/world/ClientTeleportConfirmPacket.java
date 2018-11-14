package misat11.hybrid.network.java.p404.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.packet.MinecraftPacket;

import java.io.IOException;

public class ClientTeleportConfirmPacket extends MinecraftPacket {
    private int id;

    @SuppressWarnings("unused")
    private ClientTeleportConfirmPacket() {
    }

    public ClientTeleportConfirmPacket(int id) {
        this.id = id;
    }

    public int getTeleportId() {
        return this.id;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.id = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.id);
    }
}
