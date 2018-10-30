package misat11.hybrid.blockitems.legacy;

import misat11.hybrid.blockitems.BlockEntry;
import misat11.hybrid.blockitems.IBlockTranslator;
import misat11.hybrid.blockitems.IItemTranslator;
import misat11.hybrid.blockitems.ItemEntry;

public class LegacyItemBlockTranslator implements IBlockTranslator<BlockEntry>, IItemTranslator<ItemEntry>{

	public LegacyItemBlockTranslator() {
		// TODO Auto-generated constructor stub
	}
	
	private ItemEntry itranslateToPe(int id, int data) {
		return itranslateToPe(new ItemEntry(id, data));
	}
	
	private ItemEntry itranslateToPe(ItemEntry entry) {
		return null; // TODO
	}
	
	private ItemEntry itranslateToPc(int id, int data) {
		return itranslateToPc(new ItemEntry(id, data));
	}
	
	private ItemEntry itranslateToPc(ItemEntry entry) {
		return null; // TODO
	}
	
	private BlockEntry btranslateToPe(int id, int data) {
		return btranslateToPe(new BlockEntry(id, data));
	}
	
	private BlockEntry btranslateToPe(BlockEntry entry) {
		return null; // TODO
	}
	
	private BlockEntry btranslateToPc(int id, int data) {
		return btranslateToPc(new BlockEntry(id, data));
	}
	
	private BlockEntry btranslateToPc(BlockEntry entry) {
		return null; // TODO
	}
	
	@Override
	public ItemEntry itemPcToPe(int id) {
		return itranslateToPe(id, 0);
	}

	@Override
	public ItemEntry itemPcToPe(int id, int data) {
		return itranslateToPe(id, data);
	}

	@Override
	public ItemEntry itemPcToPe(ItemEntry item) {
		return itranslateToPe(item);
	}

	@Override
	public ItemEntry itemPeToPc(int id) {
		return itranslateToPc(id, 0);
	}

	@Override
	public ItemEntry itemPeToPc(int id, int data) {
		return itranslateToPc(id, data);
	}

	@Override
	public ItemEntry itemPeToPc(ItemEntry item) {
		return itranslateToPc(item);
	}

	@Override
	public BlockEntry blockPcToPe(int id) {
		return btranslateToPe(id, 0);
	}

	@Override
	public BlockEntry blockPcToPe(int id, int data) {
		return btranslateToPe(id, data);
	}

	@Override
	public BlockEntry blockPcToPe(BlockEntry block) {
		return btranslateToPe(block);
	}

	@Override
	public BlockEntry blockPeToPc(int id) {
		return btranslateToPc(id, 0);
	}

	@Override
	public BlockEntry blockPeToPc(int id, int data) {
		return btranslateToPc(id, data);
	}

	@Override
	public BlockEntry blockPeToPc(BlockEntry block) {
		return btranslateToPc(block);
	}

}
