package misat11.hybrid.network.java.pabstract.data.game.chunk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockState;
import misat11.hybrid.network.java.pabstract.util.NetUtil;
import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public abstract class BlockStorage {

    protected static final BlockState AIR = new BlockState(0);

    protected int bitsPerEntry;

    protected List<BlockState> states;
    protected FlexibleStorage storage;

    public BlockStorage() {
        this.bitsPerEntry = 4;

        this.states = new ArrayList<BlockState>();
        this.states.add(AIR);

        this.storage = new FlexibleStorage(this.bitsPerEntry, 4096);
    }
    
    public BlockStorage(NetInput in, NetUtil util) throws IOException{
    	this.read(in, util);
    }

    private static int index(int x, int y, int z) {
        return y << 8 | z << 4 | x;
    }

    private static BlockState rawToState(int raw) {
        return new BlockState(raw);
    }

    private static int stateToRaw(BlockState state) {
        return state.getId();
    }


    public int getBitsPerEntry() {
        return this.bitsPerEntry;
    }

    public List<BlockState> getStates() {
        return Collections.unmodifiableList(this.states);
    }

    public FlexibleStorage getStorage() {
        return this.storage;
    }

    public BlockState get(int x, int y, int z) {
        int id = this.storage.get(index(x, y, z));
        return this.bitsPerEntry <= 8 ? (id >= 0 && id < this.states.size() ? this.states.get(id) : AIR) : rawToState(id);
    }

    public void set(int x, int y, int z, BlockState state) {
        int id = this.bitsPerEntry <= 8 ? this.states.indexOf(state) : stateToRaw(state);
        if(id == -1) {
            this.states.add(state);
            if(this.states.size() > 1 << this.bitsPerEntry) {
                this.bitsPerEntry++;

                List<BlockState> oldStates = this.states;
                if(this.bitsPerEntry > 8) {
                    oldStates = new ArrayList<BlockState>(this.states);
                    this.states.clear();
                    this.bitsPerEntry = 13;
                }

                FlexibleStorage oldStorage = this.storage;
                this.storage = new FlexibleStorage(this.bitsPerEntry, this.storage.getSize());
                for(int index = 0; index < this.storage.getSize(); index++) {
                    this.storage.set(index, this.bitsPerEntry <= 8 ? oldStorage.get(index) : stateToRaw(oldStates.get(index)));
                }
            }

            id = this.bitsPerEntry <= 8 ? this.states.indexOf(state) : stateToRaw(state);
        }

        this.storage.set(index(x, y, z), id);
    }

    public boolean isEmpty() {
        for(int index = 0; index < this.storage.getSize(); index++) {
            if(this.storage.get(index) != 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BlockStorage)) return false;

        BlockStorage that = (BlockStorage) o;
        return this.bitsPerEntry == that.bitsPerEntry &&
                Objects.equals(this.states, that.states) &&
                Objects.equals(this.storage, that.storage);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.bitsPerEntry, this.states, this.storage);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
    
    public abstract void write(NetOutput out, NetUtil util) throws IOException;
    
    public abstract void read(NetInput in, NetUtil util) throws IOException;
}
