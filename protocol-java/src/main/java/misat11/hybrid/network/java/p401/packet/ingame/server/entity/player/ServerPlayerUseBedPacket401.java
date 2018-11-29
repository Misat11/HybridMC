package misat11.hybrid.network.java.p401.packet.ingame.server.entity.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player.ServerPlayerUseBedPacket;

import java.io.IOException;

@Getter
public class ServerPlayerUseBedPacket401 extends MinecraftPacket implements ServerPlayerUseBedPacket {
    private int entityId;
    private Position position;

    @SuppressWarnings("unused")
    private ServerPlayerUseBedPacket401() {
    }

    public ServerPlayerUseBedPacket401(int entityId, Position position) {
        this.entityId = entityId;
        this.position = position;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.position = getUtil().readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        getUtil().writePosition(out, this.position);
    }
}
