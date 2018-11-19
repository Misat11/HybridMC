package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerSetCompressionPacket;

import java.io.IOException;

@Getter
public class ServerSetCompressionPacket404 extends MinecraftPacket implements ServerSetCompressionPacket {
    private int threshold;

    @SuppressWarnings("unused")
    private ServerSetCompressionPacket404() {
    }

    public ServerSetCompressionPacket404(int threshold) {
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
