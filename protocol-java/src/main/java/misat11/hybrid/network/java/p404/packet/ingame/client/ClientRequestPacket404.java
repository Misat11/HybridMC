package misat11.hybrid.network.java.p404.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.ClientRequest;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientRequestPacket;

import java.io.IOException;

@Getter
public class ClientRequestPacket404 extends MinecraftPacket implements ClientRequestPacket {
    private ClientRequest request;

    @SuppressWarnings("unused")
    private ClientRequestPacket404() {
    }

    public ClientRequestPacket404(ClientRequest request) {
        this.request = request;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.request = getMagic().key(ClientRequest.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.request));
    }
}
