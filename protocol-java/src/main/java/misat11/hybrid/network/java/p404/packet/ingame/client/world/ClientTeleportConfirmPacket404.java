package misat11.hybrid.network.java.p404.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.world.ClientTeleportConfirmPacket;

import java.io.IOException;

@Getter
public class ClientTeleportConfirmPacket404 extends MinecraftPacket implements ClientTeleportConfirmPacket {
    private int teleportId;

    @SuppressWarnings("unused")
    private ClientTeleportConfirmPacket404() {
    }

    public ClientTeleportConfirmPacket404(int teleportId) {
        this.teleportId = teleportId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.teleportId = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.teleportId);
    }
}