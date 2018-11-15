package misat11.hybrid.network.java.pabstract.data.game.world.block;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

@Getter
public class BlockState {
    private int id;
    private int data;

    /* MC 1.13 and later */
    public BlockState(int id) {
    	this(id, 0);
    }
    
    /* MC 1.8 - 1.12.2 */
    public BlockState(int id, int data) {
    	this.id = id;
    	this.data = data;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BlockState)) return false;

        BlockState that = (BlockState) o;
        return this.id == that.id && this.data == that.data;
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
