package misat11.hybrid.network.java.p401.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerDeclareRecipesPacket;

import java.io.IOException;

@Getter
public class ServerDeclareRecipesPacket401 extends MinecraftPacket implements ServerDeclareRecipesPacket {
    private byte[] data;

    @SuppressWarnings("unused")
    private ServerDeclareRecipesPacket401() {
    }

    @Deprecated // This packet isn't fully implemented, please send a PR if you need to use it
    public ServerDeclareRecipesPacket401(byte[] data) {
        this.data = data;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.data = in.readBytes(in.available());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeBytes(this.data);
    }
}
