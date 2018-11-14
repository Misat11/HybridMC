package misat11.hybrid.network.java.p404.data.game.world.particle;

import java.util.Objects;

import misat11.hybrid.network.java.p404.data.game.world.block.BlockState;
import misat11.hybrid.network.java.p404.util.ObjectUtil;

public class FallingDustParticleData implements ParticleData {
    private final BlockState blockState;

    public FallingDustParticleData(BlockState blockState) {
        this.blockState = blockState;
    }

    public BlockState getBlockState() {
        return this.blockState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof FallingDustParticleData)) return false;

        FallingDustParticleData that = (FallingDustParticleData) o;
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
