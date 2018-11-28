package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockChangeRecord;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerBlockChangePacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerBlockChangePacket404 extends MinecraftPacket implements ServerBlockChangePacket {
    private BlockChangeRecord record;

    @Override
    public void read(NetInput in) throws IOException {
        this.record = new BlockChangeRecord(getUtil().readPosition(in), getUtil().readBlockState(in));
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writePosition(out, this.record.getPosition());
    	getUtil().writeBlockState(out, this.record.getBlock());
    }
}
