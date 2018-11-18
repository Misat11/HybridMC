package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerDisconnectPacket;

import java.io.IOException;

@Getter
public class ServerDisconnectPacket404 extends MinecraftPacket implements ServerDisconnectPacket {
    private Message reason;

    @SuppressWarnings("unused")
    private ServerDisconnectPacket404() {
    }

    public ServerDisconnectPacket404(String text) {
        this(Message.fromString(text));
    }

    public ServerDisconnectPacket404(Message reason) {
        this.reason = reason;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.reason = Message.fromString(in.readString());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.reason.toJsonString());
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}
