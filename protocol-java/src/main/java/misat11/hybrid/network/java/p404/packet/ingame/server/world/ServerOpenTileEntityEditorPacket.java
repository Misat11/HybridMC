package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ServerOpenTileEntityEditorPacket extends MinecraftPacket {
    private Position position;

    @SuppressWarnings("unused")
    private ServerOpenTileEntityEditorPacket() {
    }

    public ServerOpenTileEntityEditorPacket(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = NetUtil404.readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil404.writePosition(out, this.position);
    }
}
