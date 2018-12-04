package misat11.hybrid.network.java.p393.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.BlockBreakStage;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerBlockBreakAnimPacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerBlockBreakAnimPacket393 extends MinecraftPacket implements ServerBlockBreakAnimPacket {
    private int breakerEntityId;
    private Position position;
    private BlockBreakStage stage;

    @Override
    public void read(NetInput in) throws IOException {
        this.breakerEntityId = in.readVarInt();
        this.position = getUtil().readPosition(in);
        try {
            this.stage = getMagic().key(BlockBreakStage.class, in.readUnsignedByte());
        } catch(IllegalArgumentException e) {
            this.stage = BlockBreakStage.RESET;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.breakerEntityId);
        getUtil().writePosition(out, this.position);
        out.writeByte(getMagic().value(Integer.class, this.stage));
    }
}
