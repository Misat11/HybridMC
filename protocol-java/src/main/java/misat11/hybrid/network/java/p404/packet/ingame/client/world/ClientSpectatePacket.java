package misat11.hybrid.network.java.p404.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;
import java.util.UUID;

public class ClientSpectatePacket extends MinecraftPacket {
    private UUID target;

    @SuppressWarnings("unused")
    private ClientSpectatePacket() {
    }

    public ClientSpectatePacket(UUID target) {
        this.target = target;
    }

    public UUID getTarget() {
        return this.target;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.target = in.readUUID();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeUUID(this.target);
    }
}
