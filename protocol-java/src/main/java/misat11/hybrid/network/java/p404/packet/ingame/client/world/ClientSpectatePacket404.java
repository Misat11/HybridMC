package misat11.hybrid.network.java.p404.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.world.ClientSpectatePacket;

import java.io.IOException;
import java.util.UUID;

@Getter
public class ClientSpectatePacket404 extends MinecraftPacket implements ClientSpectatePacket {
    private UUID target;

    @SuppressWarnings("unused")
    private ClientSpectatePacket404() {
    }

    public ClientSpectatePacket404(UUID target) {
        this.target = target;
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
