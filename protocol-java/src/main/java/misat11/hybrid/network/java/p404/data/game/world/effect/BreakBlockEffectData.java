package misat11.hybrid.network.java.p404.data.game.world.effect;

import java.util.Objects;

import misat11.hybrid.network.java.p404.data.game.world.block.BlockState;
import misat11.hybrid.network.java.p404.util.ObjectUtil;

public class BreakBlockEffectData implements WorldEffectData {
    private BlockState blockState;

    public BreakBlockEffectData(BlockState blockState) {
        this.blockState = blockState;
    }

    public BlockState getBlockState() {
        return this.blockState;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BreakBlockEffectData)) return false;

        BreakBlockEffectData that = (BreakBlockEffectData) o;
        return Objects.equals(this.blockState, that.blockState);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.blockState);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
