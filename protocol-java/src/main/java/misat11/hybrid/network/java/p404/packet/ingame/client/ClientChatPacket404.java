package misat11.hybrid.network.java.p404.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientChatPacket;

import java.io.IOException;

@Getter
public class ClientChatPacket404 extends MinecraftPacket implements ClientChatPacket {
    private String message;

    @SuppressWarnings("unused")
    private ClientChatPacket404() {
    }

    public ClientChatPacket404(String message) {
        this.message = message;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.message = in.readString();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.message);
    }
}
