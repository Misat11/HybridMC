package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.BlockBreakStage;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerBlockBreakAnimPacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerBlockBreakAnimPacket404 extends MinecraftPacket implements ServerBlockBreakAnimPacket {
    private int breakerEntityId;
    private Position position;
    private BlockBreakStage stage;

    @Override
    public void read(NetInput in) throws IOException {
        this.breakerEntityId = in.readVarInt();
        this.position = NetUtil404.readPosition(in);
        try {
            this.stage = MagicValues404.key(BlockBreakStage.class, in.readUnsignedByte());
        } catch(IllegalArgumentException e) {
            this.stage = BlockBreakStage.RESET;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.breakerEntityId);
        NetUtil404.writePosition(out, this.position);
        out.writeByte(MagicValues404.value(Integer.class, this.stage));
    }
}
