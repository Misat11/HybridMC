package misat11.hybrid.network.java.p404.util;

import com.github.steveice10.opennbt.NBTIO;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.io.stream.StreamNetInput;

import misat11.hybrid.network.java.p404.data.chunk.BlockStorage404;
import misat11.hybrid.network.java.pabstract.data.MagicValues;
import misat11.hybrid.network.java.pabstract.data.game.chunk.Chunk;
import misat11.hybrid.network.java.pabstract.data.game.chunk.Column;
import misat11.hybrid.network.java.pabstract.data.game.chunk.NibbleArray3d;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.MetadataType;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Rotation;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockFace;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockState;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.BlockParticleData;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.DustParticleData;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.FallingDustParticleData;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.ItemParticleData;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.Particle;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.ParticleData;
import misat11.hybrid.network.java.pabstract.data.game.world.particle.ParticleType;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.util.NetUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NetUtil404 implements NetUtil {
	private static final int POSITION_X_SIZE = 38;
	private static final int POSITION_Y_SIZE = 26;
	private static final int POSITION_Z_SIZE = 38;
	private static final int POSITION_Y_SHIFT = 0xFFF;
	private static final int POSITION_WRITE_SHIFT = 0x3FFFFFF;

	public static final NetUtil INSTANCE = new NetUtil404();
	
	private NetUtil404() {
	}

	public CompoundTag readNBT(NetInput in) throws IOException {
		byte b = in.readByte();
		if (b == 0) {
			return null;
		} else {
			return (CompoundTag) NBTIO.readTag(new NetInputStream(in, b));
		}
	}

	public void writeNBT(NetOutput out, CompoundTag tag) throws IOException {
		if (tag == null) {
			out.writeByte(0);
		} else {
			NBTIO.writeTag(new NetOutputStream(out), tag);
		}
	}

	public BlockState readBlockState(NetInput in) throws IOException {
		return new BlockState(in.readVarInt());
	}

	public void writeBlockState(NetOutput out, BlockState blockState) throws IOException {
		out.writeVarInt(blockState.getId());
	}

	public ItemStack readItem(NetInput in) throws IOException {
		boolean present = in.readBoolean();
		if (!present) {
			return null;
		}
		int item = in.readVarInt();
		return new ItemStack(item, in.readByte(), readNBT(in));
	}

	public void writeItem(NetOutput out, ItemStack item) throws IOException {
		out.writeBoolean(item != null);
		if (item != null) {
			out.writeVarInt(item.getId());
			out.writeByte(item.getAmount());
			writeNBT(out, item.getNBT());
		}
	}

	public Position readPosition(NetInput in) throws IOException {
		long val = in.readLong();

		int x = (int) (val >> POSITION_X_SIZE);
		int y = (int) ((val >> POSITION_Y_SIZE) & POSITION_Y_SHIFT);
		int z = (int) ((val << POSITION_Z_SIZE) >> POSITION_Z_SIZE);

		return new Position(x, y, z);
	}

	public void writePosition(NetOutput out, Position pos) throws IOException {
		long x = pos.getX() & POSITION_WRITE_SHIFT;
		long y = pos.getY() & POSITION_Y_SHIFT;
		long z = pos.getZ() & POSITION_WRITE_SHIFT;

		out.writeLong(x << POSITION_X_SIZE | y << POSITION_Y_SIZE | z);
	}

	public Rotation readRotation(NetInput in) throws IOException {
		return new Rotation(in.readFloat(), in.readFloat(), in.readFloat());
	}

	public void writeRotation(NetOutput out, Rotation rot) throws IOException {
		out.writeFloat(rot.getPitch());
		out.writeFloat(rot.getYaw());
		out.writeFloat(rot.getRoll());
	}

	public Particle readParticle(NetInput in, MagicValues magic) throws IOException {
		ParticleType type = magic.key(ParticleType.class, in.readVarInt());
		ParticleData data = readParticleData(in, type);
		return new Particle(type, data);
	}

	public ParticleData readParticleData(NetInput in, ParticleType type) throws IOException {
		switch (type) {
		case BLOCK:
			return new BlockParticleData(readBlockState(in));
		case DUST:
			float red = in.readFloat();
			float green = in.readFloat();
			float blue = in.readFloat();
			float scale = in.readFloat();
			return new DustParticleData(red, green, blue, scale);
		case FALLING_DUST:
			return new FallingDustParticleData(readBlockState(in));
		case ITEM:
			return new ItemParticleData(readItem(in));
		default:
			return null;
		}
	}

	public void writeParticle(NetOutput out, Particle particle, MagicValues magic) throws IOException {
		out.writeVarInt(magic.value(Integer.class, particle.getType()));
		writeParticleData(out, particle.getData(), particle.getType());
	}

	public void writeParticleData(NetOutput out, ParticleData data, ParticleType type) throws IOException {
		switch (type) {
		case BLOCK:
			writeBlockState(out, ((BlockParticleData) data).getBlockState());
			break;
		case DUST:
			out.writeFloat(((DustParticleData) data).getRed());
			out.writeFloat(((DustParticleData) data).getGreen());
			out.writeFloat(((DustParticleData) data).getBlue());
			out.writeFloat(((DustParticleData) data).getScale());
			break;
		case FALLING_DUST:
			writeBlockState(out, ((FallingDustParticleData) data).getBlockState());
			break;
		case ITEM:
			writeItem(out, ((ItemParticleData) data).getItemStack());
			break;
		default:
			break;
		}
	}

	public EntityMetadata[] readEntityMetadata(NetInput in, MagicValues magic) throws IOException {
		List<EntityMetadata> ret = new ArrayList<EntityMetadata>();
		int id;
		while ((id = in.readUnsignedByte()) != 255) {
			int typeId = in.readVarInt();
			MetadataType type = magic.key(MetadataType.class, typeId);
			Object value = null;
			switch (type) {
			case BYTE:
				value = in.readByte();
				break;
			case INT:
				value = in.readVarInt();
				break;
			case FLOAT:
				value = in.readFloat();
				break;
			case STRING:
				value = in.readString();
				break;
			case OPTIONAL_CHAT:
				boolean chatPresent = in.readBoolean();
				if (!chatPresent) {
					break;
				}
				// Intentional fall-through
			case CHAT:
				value = Message.fromString(in.readString());
				break;
			case ITEM:
				value = readItem(in);
				break;
			case BOOLEAN:
				value = in.readBoolean();
				break;
			case ROTATION:
				value = readRotation(in);
				break;
			case POSITION:
				value = readPosition(in);
				break;
			case OPTIONAL_POSITION:
				boolean positionPresent = in.readBoolean();
				if (positionPresent) {
					value = readPosition(in);
				}

				break;
			case BLOCK_FACE:
				value = magic.key(BlockFace.class, in.readVarInt());
				break;
			case OPTIONAL_UUID:
				boolean uuidPresent = in.readBoolean();
				if (uuidPresent) {
					value = in.readUUID();
				}

				break;
			case BLOCK_STATE:
				value = readBlockState(in);
				break;
			case NBT_TAG:
				value = readNBT(in);
				break;
			case PARTICLE:
				value = readParticle(in, magic);
				break;
			default:
				throw new IOException("Unknown metadata type id: " + typeId);
			}

			ret.add(new EntityMetadata(id, type, value));
		}

		return ret.toArray(new EntityMetadata[ret.size()]);
	}

	public void writeEntityMetadata(NetOutput out, EntityMetadata[] metadata, MagicValues magic) throws IOException {
		for (EntityMetadata meta : metadata) {
			out.writeByte(meta.getId());
			out.writeVarInt(magic.value(Integer.class, meta.getType()));
			switch (meta.getType()) {
			case BYTE:
				out.writeByte((Byte) meta.getValue());
				break;
			case INT:
				out.writeVarInt((Integer) meta.getValue());
				break;
			case FLOAT:
				out.writeFloat((Float) meta.getValue());
				break;
			case STRING:
				out.writeString((String) meta.getValue());
				break;
			case OPTIONAL_CHAT:
				out.writeBoolean(meta.getValue() != null);
				if (meta.getValue() == null) {
					break;
				}
				// Intentional fall-through
			case CHAT:
				out.writeString(((Message) meta.getValue()).toJsonString());
				break;
			case ITEM:
				writeItem(out, (ItemStack) meta.getValue());
				break;
			case BOOLEAN:
				out.writeBoolean((Boolean) meta.getValue());
				break;
			case ROTATION:
				writeRotation(out, (Rotation) meta.getValue());
				break;
			case POSITION:
				writePosition(out, (Position) meta.getValue());
				break;
			case OPTIONAL_POSITION:
				out.writeBoolean(meta.getValue() != null);
				if (meta.getValue() != null) {
					writePosition(out, (Position) meta.getValue());
				}

				break;
			case BLOCK_FACE:
				out.writeVarInt(magic.value(Integer.class, (BlockFace) meta.getValue()));
				break;
			case OPTIONAL_UUID:
				out.writeBoolean(meta.getValue() != null);
				if (meta.getValue() != null) {
					out.writeUUID((UUID) meta.getValue());
				}

				break;
			case BLOCK_STATE:
				writeBlockState(out, (BlockState) meta.getValue());
				break;
			case NBT_TAG:
				writeNBT(out, (CompoundTag) meta.getValue());
				break;
			case PARTICLE:
				writeParticle(out, (Particle) meta.getValue(), magic);
				break;
			default:
				throw new IOException("Unknown metadata type: " + meta.getType());
			}
		}

		out.writeByte(255);
	}

	public Column readColumn(byte data[], int x, int z, boolean fullChunk, boolean hasSkylight, int mask,
			CompoundTag[] tileEntities) throws IOException {
		NetInput in = new StreamNetInput(new ByteArrayInputStream(data));
		Throwable ex = null;
		Column column = null;
		try {
			Chunk[] chunks = new Chunk[16];
			for (int index = 0; index < chunks.length; index++) {
				if ((mask & (1 << index)) != 0) {
					BlockStorage404 blocks = new BlockStorage404(in, this);
					NibbleArray3d blocklight = new NibbleArray3d(in, 2048);
					NibbleArray3d skylight = hasSkylight ? new NibbleArray3d(in, 2048) : null;
					chunks[index] = new Chunk(blocks, blocklight, skylight);
				}
			}

			int biomeData[] = null;
			if (fullChunk) {
				biomeData = in.readInts(256);
			}

			column = new Column(x, z, chunks, biomeData, tileEntities);
		} catch (Throwable e) {
			ex = e;
		}

		// Unfortunately, this is needed to detect whether the chunks contain skylight
		// or not.
		if ((in.available() > 0 || ex != null) && !hasSkylight) {
			return readColumn(data, x, z, fullChunk, true, mask, tileEntities);
		} else if (ex != null) {
			throw new IOException("Failed to read chunk data.", ex);
		}

		return column;
	}

	public int writeColumn(NetOutput out, Column column, boolean fullChunk, boolean hasSkylight) throws IOException {
		int mask = 0;
		Chunk chunks[] = column.getChunks();
		for (int index = 0; index < chunks.length; index++) {
			Chunk chunk = chunks[index];
			if (chunk != null && (!fullChunk || !chunk.isEmpty())) {
				mask |= 1 << index;
				chunk.getBlocks().write(out, this);
				chunk.getBlockLight().write(out);
				if (hasSkylight) {
					chunk.getSkyLight().write(out);
				}
			}
		}

		if (fullChunk) {
			out.writeInts(column.getBiomeData());
		}

		return mask;
	}

	private static class NetInputStream extends InputStream {
		private NetInput in;
		private boolean readFirst;
		private byte firstByte;

		public NetInputStream(NetInput in, byte firstByte) {
			this.in = in;
			this.firstByte = firstByte;
		}

		@Override
		public int read() throws IOException {
			if (!this.readFirst) {
				this.readFirst = true;
				return this.firstByte;
			} else {
				return this.in.readUnsignedByte();
			}
		}
	}

	private static class NetOutputStream extends OutputStream {
		private NetOutput out;

		public NetOutputStream(NetOutput out) {
			this.out = out;
		}

		@Override
		public void write(int b) throws IOException {
			this.out.writeByte(b);
		}
	}
}
