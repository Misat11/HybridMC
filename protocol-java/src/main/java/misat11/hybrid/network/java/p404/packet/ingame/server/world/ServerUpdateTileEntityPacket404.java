package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.UpdatedTileType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerUpdateTileEntityPacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerUpdateTileEntityPacket404 extends MinecraftPacket implements ServerUpdateTileEntityPacket {
    private Position position;
    private UpdatedTileType type;
    private CompoundTag NBT;

    @Override
    public void read(NetInput in) throws IOException {
        this.position = NetUtil404.readPosition(in);
        this.type = MagicValues404.key(UpdatedTileType.class, in.readUnsignedByte());
        this.NBT = NetUtil404.readNBT(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil404.writePosition(out, this.position);
        out.writeByte(MagicValues404.value(Integer.class, this.type));
        NetUtil404.writeNBT(out, this.NBT);
    }
}
