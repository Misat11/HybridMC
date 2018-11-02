package misat11.hybrid.downstream.translators;

import java.util.LinkedHashMap;

import com.github.steveice10.mc.protocol.data.game.chunk.BlockStorage;
import com.github.steveice10.mc.protocol.data.game.chunk.Chunk;
import com.github.steveice10.mc.protocol.data.game.chunk.Column;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockState;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import com.nukkitx.network.VarInts;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.FullChunkDataPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import misat11.hybrid.blockitems.BlockEntry;
import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ChunkTranslator implements IDownstreamTranslator<ServerChunkDataPacket> {

	public static final int SUBCHUNK_VERSION = 8;
	public static final int FLAG_RUNTIME = 1;
	public static final int BLOCKS_IN_SECTION = 4096;

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerChunkDataPacket packet) {
		if (session.getDownstream().getChunkCache().isMarkedAsSent(packet.getColumn().getX(), packet.getColumn().getZ())) {
			return null; // TODO check this
		}
		FullChunkDataPacket fcdp = new FullChunkDataPacket();
		fcdp.setChunkX(packet.getColumn().getX());
		fcdp.setChunkZ(packet.getColumn().getZ());
		pcToPeChunk(session, packet.getColumn(), fcdp);
		session.getDownstream().getChunkCache().markSent(packet.getColumn().getX(), packet.getColumn().getZ());
		return new BedrockPacket[] { fcdp };
	}

	public static void pcToPeChunk(HybridSession session, Column pcChunk, FullChunkDataPacket packet) {
		ByteBuf buf = Unpooled.buffer();
		Chunk[] chunks = pcChunk.getChunks();
		buf.writeByte(chunks.length);
		for (Chunk chunk : chunks) {
			buf.writeByte(SUBCHUNK_VERSION);
			if (chunk != null) {
				buf.writeByte(2);
				BlockStorage storage = chunk.getBlocks();
				BlockPalette palette = new BlockPalette();
				int bitsPerBlock = getPocketBitsPerBlock(storage.getBitsPerEntry());
				BlockStorageWriterPE peBlocks = new BlockStorageWriterPE(bitsPerBlock, BLOCKS_IN_SECTION);
				BlockStorageWriterPE waterlogs = new BlockStorageWriterPE(1, BLOCKS_IN_SECTION);
				buf.writeByte((bitsPerBlock << 1) | FLAG_RUNTIME);
				int blockIndex = 0;
				for (int x = 0; x < 16; x++) {
					for (int z = 0; z < 16; z++) {
						for (int y = 0; y < 16; y++) {
							BlockState block = storage.get(x, y, z);
							BlockEntry peEntry = session.getServer().getBlockTranslator().blockPcToPe(block.getId());
							if (peEntry.isWaterlogged()) {
								waterlogs.setBlockState(blockIndex, 1);
							}
							peBlocks.setBlockState(blockIndex++, palette.getRuntimeId(session.getServer().getPaletteManager().fromLegacy(peEntry.getId(),(byte) peEntry.getData())));
						}
					}
				}
				for (int word : peBlocks.getBlockData()) {
					buf.writeIntLE(word);
				}
				Integer[] states = palette.getBlockStates();
				VarInts.writeInt(buf, states.length);
				for (int state : states) {
					VarInts.writeInt(buf, state);
				}
				buf.writeByte((1 << 1) | FLAG_RUNTIME);
				for (int word : waterlogs.getBlockData()) {
					buf.writeIntLE(word);
				}
				VarInts.writeInt(buf, 2);
				VarInts.writeInt(buf, 0);
				VarInts.writeInt(buf, 54);
			} else {
				buf.writeByte(1); // blockstorage count.
				buf.writeByte((1 << 1) | FLAG_RUNTIME);
				buf.writeZero(512);
				VarInts.writeInt(buf, 1); // Palette size
				VarInts.writeInt(buf, 0); // Palette: Air
			}
		}
		buf.writeZero(512); // heightmap (will be recalculated by client anyway)
		int[] biomeData = pcChunk.getBiomeData();
		for (int i = 0; i < biomeData.length; i++) {
			buf.writeByte(biomeData[i]);
		}
		buf.writeByte(0); // borders
		// TODO tile entities
		packet.setData(buf.array());
	}

	public static class BlockPalette {

		protected final LinkedHashMap<Integer, Integer> blockstateToRuntimeId = new LinkedHashMap<Integer, Integer>();
		protected int nextRuntimeId = 0;

		public BlockPalette() {
			// always register air
			getRuntimeId(0);
		}

		public int getRuntimeId(int blockstate) {
			return blockstateToRuntimeId.computeIfAbsent(blockstate, k -> nextRuntimeId++);
		}

		public Integer[] getBlockStates() {
			return blockstateToRuntimeId.keySet().toArray(new Integer[blockstateToRuntimeId.keySet().size()]);
		}

	}

	protected static int getPocketBitsPerBlock(int pcBitsPerBlock) {
		if (pcBitsPerBlock == 7) {
			return 8;
		} else if (pcBitsPerBlock > 8) {
			return 16;
		}
		return pcBitsPerBlock;
	}

	public static class BlockStorageWriterPE {

		private static final int bitsPerWord = 4 * 8;
		private final int[] blockdata;
		private final int bitsPerBlock;
		private final double blocksPerWord;
		private final int singleValMask;

		public BlockStorageWriterPE(int bitsPerBlock, int blocks) {
			this.bitsPerBlock = bitsPerBlock;
			this.blocksPerWord = Math.floor(bitsPerWord / bitsPerBlock);
			this.blockdata = new int[(int) Math.ceil(blocks / this.blocksPerWord)];
			this.singleValMask = (1 << bitsPerBlock) - 1;
		}

		public void setBlockState(int index, int blockstate) {
			final int arrIndex = (int) (index / blocksPerWord);
			final int bitIndex = ((int) ((index % blocksPerWord))) * bitsPerBlock;
			this.blockdata[arrIndex] = ((this.blockdata[arrIndex] & ~(this.singleValMask << bitIndex))
					| ((blockstate & this.singleValMask) << bitIndex));
		}

		public int[] getBlockData() {
			return blockdata;
		}

	}
}
