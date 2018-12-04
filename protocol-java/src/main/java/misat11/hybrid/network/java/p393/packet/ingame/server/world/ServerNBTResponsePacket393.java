package misat11.hybrid.network.java.p393.packet.ingame.server.world;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerNBTResponsePacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerNBTResponsePacket393 extends MinecraftPacket implements ServerNBTResponsePacket {
    private int transactionId;
    private CompoundTag NBT;

    @Override
    public void read(NetInput in) throws IOException {
        this.transactionId = in.readVarInt();
        this.NBT = getUtil().readNBT(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.transactionId);
        getUtil().writeNBT(out, this.NBT);
    }
}
