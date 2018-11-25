package misat11.hybrid.network.java.p404.data.chunk;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.chunk.BlockStorage;
import misat11.hybrid.network.java.pabstract.data.game.chunk.FlexibleStorage;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockState;

import java.io.IOException;
import java.util.ArrayList;

public class BlockStorage404 extends BlockStorage {
	
	public BlockStorage404() {
		super();
	}

    public BlockStorage404(NetInput in) throws IOException {
    	super(in);
    }

    public void write(NetOutput out) throws IOException {
        out.writeByte(this.bitsPerEntry);

        if (this.bitsPerEntry <= 8) {
            out.writeVarInt(this.states.size());
            for (BlockState state : this.states) {
                NetUtil404.writeBlockState(out, state);
            }
        }

        long[] data = this.storage.getData();
        out.writeVarInt(data.length);
        out.writeLongs(data);
    }

	@Override
	public void read(NetInput in) throws IOException {
        this.bitsPerEntry = in.readUnsignedByte();

        this.states = new ArrayList<BlockState>();
        int stateCount = this.bitsPerEntry > 8 ? 0 : in.readVarInt();
        for(int i = 0; i < stateCount; i++) {
            this.states.add(NetUtil404.readBlockState(in));
        }

        this.storage = new FlexibleStorage(this.bitsPerEntry, in.readLongs(in.readVarInt()));
		
	}
}
