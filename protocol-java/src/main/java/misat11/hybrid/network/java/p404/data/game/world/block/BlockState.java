package misat11.hybrid.network.java.p404.data.game.world.block;

import misat11.hybrid.network.java.p404.util.ObjectUtil;

public class BlockState {
    private int id;

    public BlockState(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BlockState)) return false;

        BlockState that = (BlockState) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.id);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
