package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.MessageType;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerChatPacket;

import java.io.IOException;

@Getter
public class ServerChatPacket404 extends MinecraftPacket implements ServerChatPacket {
    private Message message;
    private MessageType type;

    @SuppressWarnings("unused")
    private ServerChatPacket404() {
    }

    public ServerChatPacket404(String text) {
        this(Message.fromString(text));
    }

    public ServerChatPacket404(Message message) {
        this(message, MessageType.SYSTEM);
    }

    public ServerChatPacket404(String text, MessageType type) {
        this(Message.fromString(text), type);
    }

    public ServerChatPacket404(Message message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.message = Message.fromString(in.readString());
        this.type = MagicValues404.key(MessageType.class, in.readByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.message.toJsonString());
        out.writeByte(MagicValues404.value(Integer.class, this.type));
    }
}
