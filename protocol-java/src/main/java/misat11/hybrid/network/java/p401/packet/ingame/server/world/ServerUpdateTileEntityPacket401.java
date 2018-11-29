package misat11.hybrid.network.java.p401.packet.ingame.server.world;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.UpdatedTileType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerUpdateTileEntityPacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerUpdateTileEntityPacket401 extends MinecraftPacket implements ServerUpdateTileEntityPacket {
    private Position position;
    private UpdatedTileType type;
    private CompoundTag NBT;

    @Override
    public void read(NetInput in) throws IOException {
        this.position = getUtil().readPosition(in);
        this.type = getMagic().key(UpdatedTileType.class, in.readUnsignedByte());
        this.NBT = getUtil().readNBT(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writePosition(out, this.position);
        out.writeByte(getMagic().value(Integer.class, this.type));
        getUtil().writeNBT(out, this.NBT);
    }
}
