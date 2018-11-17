package misat11.hybrid.network.java.p404.packet.login.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.login.client.LoginPluginResponsePacket;

import java.io.IOException;

@Getter
public class LoginPluginResponsePacket404 extends MinecraftPacket implements LoginPluginResponsePacket {
    private int messageId;
    private byte[] data;

    @SuppressWarnings("unused")
    private LoginPluginResponsePacket404() {
    }

    public LoginPluginResponsePacket404(int messageId) {
        this.messageId = messageId;
    }

    public LoginPluginResponsePacket404(int messageId, byte[] data) {
        this.messageId = messageId;
        this.data = data;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.messageId = in.readVarInt();
        if (in.readBoolean()) {
            this.data = in.readBytes(in.available());
        } else {
            this.data = null;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.messageId);
        if (data != null) {
            out.writeBoolean(true);
            out.writeBytes(this.data);
        } else {
            out.writeBoolean(false);
        }
    }
}
