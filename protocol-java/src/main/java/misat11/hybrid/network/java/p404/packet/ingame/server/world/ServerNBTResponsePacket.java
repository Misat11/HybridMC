package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.packet.MinecraftPacket;
import misat11.hybrid.network.java.p404.util.NetUtil;

import java.io.IOException;

public class ServerNBTResponsePacket extends MinecraftPacket {
    private int transactionId;
    private CompoundTag nbt;

    @SuppressWarnings("unused")
    private ServerNBTResponsePacket() {
    }

    public ServerNBTResponsePacket(int transactionId, CompoundTag nbt) {
        this.transactionId = transactionId;
        this.nbt = nbt;
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public CompoundTag getNBT() {
        return this.nbt;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.transactionId = in.readVarInt();
        this.nbt = NetUtil.readNBT(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.transactionId);
        NetUtil.writeNBT(out, this.nbt);
    }
}
