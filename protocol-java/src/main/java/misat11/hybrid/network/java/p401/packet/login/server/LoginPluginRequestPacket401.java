package misat11.hybrid.network.java.p401.packet.login.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.LoginPluginRequestPacket;

import java.io.IOException;

@Getter
public class LoginPluginRequestPacket401 extends MinecraftPacket implements LoginPluginRequestPacket {
    private int messageId;
    private String channel;
    private byte[] data;

    @SuppressWarnings("unused")
    private LoginPluginRequestPacket401() {
    }

    public LoginPluginRequestPacket401(int messageId, String channel, byte[] data) {
        this.messageId = messageId;
        this.channel = channel;
        this.data = data;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.messageId = in.readVarInt();
        this.channel = in.readString();
        this.data = in.readBytes(in.available());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.messageId);
        out.writeString(this.channel);
        out.writeBytes(this.data);
    }
}
