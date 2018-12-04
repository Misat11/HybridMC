package misat11.hybrid.network.java.p393.packet.login.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.login.client.LoginStartPacket;

import java.io.IOException;

@Getter
public class LoginStartPacket393 extends MinecraftPacket implements LoginStartPacket {
    private String username;

    @SuppressWarnings("unused")
    private LoginStartPacket393() {
    }

    public LoginStartPacket393(String username) {
        this.username = username;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.username = in.readString();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.username);
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}
