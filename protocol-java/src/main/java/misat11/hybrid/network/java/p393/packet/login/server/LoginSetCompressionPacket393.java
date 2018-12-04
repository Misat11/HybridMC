package misat11.hybrid.network.java.p393.packet.login.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.login.server.LoginSetCompressionPacket;

import java.io.IOException;

@Getter
public class LoginSetCompressionPacket393 extends MinecraftPacket implements LoginSetCompressionPacket {
    private int threshold;

    @SuppressWarnings("unused")
    private LoginSetCompressionPacket393() {
    }

    public LoginSetCompressionPacket393(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.threshold = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.threshold);
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}
