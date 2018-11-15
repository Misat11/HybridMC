package misat11.hybrid.network.java.pabstract.data.game.world.block.value;

import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public class ChestValue implements BlockValue {
    private int viewers;

    public ChestValue(int viewers) {
        this.viewers = viewers;
    }

    public int getViewers() {
        return this.viewers;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ChestValue)) return false;

        ChestValue that = (ChestValue) o;
        return this.viewers == that.viewers;
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.viewers);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
