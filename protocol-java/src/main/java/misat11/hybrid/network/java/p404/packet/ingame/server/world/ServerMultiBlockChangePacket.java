package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockChangeRecord;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockState;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ServerMultiBlockChangePacket extends MinecraftPacket {
    private BlockChangeRecord records[];

    @SuppressWarnings("unused")
    private ServerMultiBlockChangePacket() {
    }

    public ServerMultiBlockChangePacket(BlockChangeRecord... records) {
        if(records == null || records.length == 0) {
            throw new IllegalArgumentException("Records must contain at least 1 value.");
        }

        this.records = records;
    }

    public BlockChangeRecord[] getRecords() {
        return this.records;
    }

    @Override
    public void read(NetInput in) throws IOException {
        int chunkX = in.readInt();
        int chunkZ = in.readInt();
        this.records = new BlockChangeRecord[in.readVarInt()];
        for(int index = 0; index < this.records.length; index++) {
            short pos = in.readShort();
            BlockState block = NetUtil404.readBlockState(in);
            int x = (chunkX << 4) + (pos >> 12 & 15);
            int y = pos & 255;
            int z = (chunkZ << 4) + (pos >> 8 & 15);
            this.records[index] = new BlockChangeRecord(new Position(x, y, z), block);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        int chunkX = this.records[0].getPosition().getX() >> 4;
        int chunkZ = this.records[0].getPosition().getZ() >> 4;
        out.writeInt(chunkX);
        out.writeInt(chunkZ);
        out.writeVarInt(this.records.length);
        for(BlockChangeRecord record : this.records) {
            out.writeShort((record.getPosition().getX() - (chunkX << 4)) << 12 | (record.getPosition().getZ() - (chunkZ << 4)) << 8 | record.getPosition().getY());
            NetUtil404.writeBlockState(out, record.getBlock());
        }
    }
}
