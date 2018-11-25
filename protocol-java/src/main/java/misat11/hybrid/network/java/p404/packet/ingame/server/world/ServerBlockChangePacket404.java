package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.p404.util.NetUtil404;
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
        this.record = new BlockChangeRecord(NetUtil404.readPosition(in), NetUtil404.readBlockState(in));
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil404.writePosition(out, this.record.getPosition());
        NetUtil404.writeBlockState(out, this.record.getBlock());
    }
}
