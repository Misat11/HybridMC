package misat11.hybrid.network.java.p404.data.chunk;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.data.game.chunk.BlockStorage;
import misat11.hybrid.network.java.pabstract.data.game.chunk.FlexibleStorage;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockState;
import misat11.hybrid.network.java.pabstract.util.NetUtil;

import java.io.IOException;
import java.util.ArrayList;

public class BlockStorage404 extends BlockStorage {
	
	public BlockStorage404() {
		super();
	}

    public BlockStorage404(NetInput in, NetUtil util) throws IOException {
    	super(in, util);
    }

    public void write(NetOutput out, NetUtil util) throws IOException {
        out.writeByte(this.bitsPerEntry);

        if (this.bitsPerEntry <= 8) {
            out.writeVarInt(this.states.size());
            for (BlockState state : this.states) {
                util.writeBlockState(out, state);
            }
        }

        long[] data = this.storage.getData();
        out.writeVarInt(data.length);
        out.writeLongs(data);
    }

	@Override
	public void read(NetInput in, NetUtil util) throws IOException {
        this.bitsPerEntry = in.readUnsignedByte();

        this.states = new ArrayList<BlockState>();
        int stateCount = this.bitsPerEntry > 8 ? 0 : in.readVarInt();
        for(int i = 0; i < stateCount; i++) {
            this.states.add(util.readBlockState(in));
        }

        this.storage = new FlexibleStorage(this.bitsPerEntry, in.readLongs(in.readVarInt()));
		
	}
}
