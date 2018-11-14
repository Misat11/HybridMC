package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.p404.packet.MinecraftPacket;
import misat11.hybrid.network.java.p404.util.NetUtil;

import java.io.IOException;

public class ServerSpawnPositionPacket extends MinecraftPacket {
    private Position position;

    @SuppressWarnings("unused")
    private ServerSpawnPositionPacket() {
    }

    public ServerSpawnPositionPacket(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = NetUtil.readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, this.position);
    }
}