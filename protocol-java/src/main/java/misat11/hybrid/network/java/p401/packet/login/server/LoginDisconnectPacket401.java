package misat11.hybrid.network.java.p401.packet.login.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.LoginDisconnectPacket;

import java.io.IOException;

@Getter
public class LoginDisconnectPacket401 extends MinecraftPacket implements LoginDisconnectPacket {
    private Message reason;

    @SuppressWarnings("unused")
    private LoginDisconnectPacket401() {
    }

    public LoginDisconnectPacket401(String text) {
        this(Message.fromString(text));
    }

    public LoginDisconnectPacket401(Message reason) {
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
