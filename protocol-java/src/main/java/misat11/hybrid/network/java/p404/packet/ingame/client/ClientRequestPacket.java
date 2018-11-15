package misat11.hybrid.network.java.p404.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.ClientRequest;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ClientRequestPacket extends MinecraftPacket {
    private ClientRequest request;

    @SuppressWarnings("unused")
    private ClientRequestPacket() {
    }

    public ClientRequestPacket(ClientRequest request) {
        this.request = request;
    }

    public ClientRequest getRequest() {
        return this.request;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.request = MagicValues404.key(ClientRequest.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues404.value(Integer.class, this.request));
    }
}
