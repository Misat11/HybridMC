package misat11.hybrid.network.java.pabstract.data.game.chunk;

import java.util.Objects;

import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public class Chunk {
    private BlockStorage blocks;
    private NibbleArray3d blocklight;
    private NibbleArray3d skylight;

    public Chunk(boolean skylight) {
        this(new EmptyBlockStorage(), new NibbleArray3d(4096), skylight ? new NibbleArray3d(4096) : null);
    }

    public Chunk(BlockStorage blocks, NibbleArray3d blocklight, NibbleArray3d skylight) {
        this.blocks = blocks;
        this.blocklight = blocklight;
        this.skylight = skylight;
    }

    public BlockStorage getBlocks() {
        return this.blocks;
    }

    public NibbleArray3d getBlockLight() {
        return this.blocklight;
    }

    public NibbleArray3d getSkyLight() {
        return this.skylight;
    }

    public boolean isEmpty() {
        return this.blocks.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Chunk)) return false;

        Chunk that = (Chunk) o;
        return Objects.equals(this.blocks, that.blocks) &&
                Objects.equals(this.blocklight, that.blocklight) &&
                Objects.equals(this.skylight, that.skylight);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.blocks, this.blocklight, this.skylight);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
