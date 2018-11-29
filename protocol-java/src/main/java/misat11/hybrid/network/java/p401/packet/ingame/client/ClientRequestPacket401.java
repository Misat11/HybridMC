package misat11.hybrid.network.java.p401.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.ClientRequest;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientRequestPacket;

import java.io.IOException;

@Getter
public class ClientRequestPacket401 extends MinecraftPacket implements ClientRequestPacket {
    private ClientRequest request;

    @SuppressWarnings("unused")
    private ClientRequestPacket401() {
    }

    public ClientRequestPacket401(ClientRequest request) {
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
