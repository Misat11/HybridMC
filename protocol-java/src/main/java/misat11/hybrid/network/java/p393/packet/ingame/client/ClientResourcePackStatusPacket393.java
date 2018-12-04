package misat11.hybrid.network.java.p393.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.ResourcePackStatus;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientResourcePackStatusPacket;

import java.io.IOException;

@Getter
public class ClientResourcePackStatusPacket393 extends MinecraftPacket implements ClientResourcePackStatusPacket {
    private ResourcePackStatus status;

    @SuppressWarnings("unused")
    private ClientResourcePackStatusPacket393() {
    }

    public ClientResourcePackStatusPacket393(ResourcePackStatus status) {
        this.status = status;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.status = getMagic().key(ResourcePackStatus.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.status));
    }
}
