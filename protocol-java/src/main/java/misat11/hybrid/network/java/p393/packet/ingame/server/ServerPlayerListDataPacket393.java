package misat11.hybrid.network.java.p393.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerPlayerListDataPacket;

import java.io.IOException;

@Getter
public class ServerPlayerListDataPacket393 extends MinecraftPacket implements ServerPlayerListDataPacket {
    private Message header;
    private Message footer;

    @SuppressWarnings("unused")
    private ServerPlayerListDataPacket393() {
    }

    public ServerPlayerListDataPacket393(Message header, Message footer) {
        this.header = header;
        this.footer = footer;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.header = Message.fromString(in.readString());
        this.footer = Message.fromString(in.readString());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.header.toJsonString());
        out.writeString(this.footer.toJsonString());
    }
}
