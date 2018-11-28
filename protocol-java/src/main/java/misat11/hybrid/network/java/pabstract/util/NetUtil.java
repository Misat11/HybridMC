package misat11.hybrid.network.java.pabstract.util;

import java.io.IOException;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.data.MagicValues;
import misat11.hybrid.network.java.pabstract.data.game.chunk.Column;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Rotation;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockState;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.Particle;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.ParticleData;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.ParticleType;

public interface NetUtil {
	public CompoundTag readNBT(NetInput in) throws IOException;
	
	public void writeNBT(NetOutput out, CompoundTag tag) throws IOException;
	
	public BlockState readBlockState(NetInput in) throws IOException;
	
	public void writeBlockState(NetOutput out, BlockState blockState) throws IOException;
	
	public ItemStack readItem(NetInput in) throws IOException;
	
	public void writeItem(NetOutput out, ItemStack item) throws IOException;
	
	public Position readPosition(NetInput in) throws IOException;
	
	public void writePosition(NetOutput out, Position pos) throws IOException;
	
	public Rotation readRotation(NetInput in) throws IOException;
	
	public void writeRotation(NetOutput out, Rotation rot) throws IOException;
	
	public Particle readParticle(NetInput in, MagicValues magic) throws IOException;
	
	public ParticleData readParticleData(NetInput in, ParticleType type) throws IOException;
	
	public void writeParticle(NetOutput out, Particle particle, MagicValues magic) throws IOException;
	
	public void writeParticleData(NetOutput out, ParticleData data, ParticleType type) throws IOException;
	
	public EntityMetadata[] readEntityMetadata(NetInput in, MagicValues magic) throws IOException;
	
	public void writeEntityMetadata(NetOutput out, EntityMetadata[] metadata, MagicValues magic) throws IOException;
	
	public Column readColumn(byte data[], int x, int z, boolean fullChunk, boolean hasSkylight, int mask, CompoundTag[] tileEntities) throws IOException;
	
	public int writeColumn(NetOutput out, Column column, boolean fullChunk, boolean hasSkylight) throws IOException;
}
