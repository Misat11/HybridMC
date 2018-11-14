package misat11.hybrid.network.java.p404.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues;
import misat11.hybrid.network.java.p404.data.game.ClientRequest;
import misat11.hybrid.network.java.p404.packet.MinecraftPacket;

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
        this.request = MagicValues.key(ClientRequest.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, this.request));
    }
}
