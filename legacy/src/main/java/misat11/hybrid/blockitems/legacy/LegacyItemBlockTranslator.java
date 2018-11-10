package misat11.hybrid.blockitems.legacy;

import java.util.HashMap;
import java.util.Map;

import misat11.hybrid.blockitems.BlockEntry;
import misat11.hybrid.blockitems.IBlockTranslator;
import misat11.hybrid.blockitems.IItemTranslator;
import misat11.hybrid.blockitems.ItemEntry;

public class LegacyItemBlockTranslator implements IBlockTranslator<BlockEntry>, IItemTranslator<ItemEntry> {

	public static int MAX_BLOCK_VALUE = 255;

	private Map<Integer, Integer> PC_TO_PE_OVERRIDE = new HashMap<>();
	private Map<Integer, Integer> PE_TO_PC_OVERRIDE = new HashMap<>();

	public LegacyItemBlockTranslator() {
		// TODO metadata overriding !!!!!!!
		// Blocks

		override(36, 250); // Moving block

		override(125, 157); // Wooden double slab
		override(126, 158); // Wooden slab

		override(157, 126); // Activator rail
		override(158, 125); // Dropper

		override(166, 416); // Barrier

		overridePcToPe(188, 85); // Spruce fence -> Oak fence (TODO override metadata)
		overridePcToPe(189, 85); // Birch fence -> Oak fence (TODO override metadata)
		overridePcToPe(190, 85); // Jungle fence -> Oak fence (TODO override metadata)
		overridePcToPe(191, 85); // Dark oak fence -> Oak fence (TODO override metadata)
		overridePcToPe(192, 85); // Acacia fence -> Oak fence (TODO override metadata)

		swap(198, 208); // End rod <-> Grass Path
		override(199, 240); // Chorus plant

		overridePcToPe(202, 201); // Purpur pillar -> Purpur block (TODO override metadata)

		overridePcToPe(204, 181); // Purpur double slab -> Double stone slab 2 (TODO override metadata)
		overridePcToPe(205, 182); // Purpur slab -> Stone slab 2 (TODO override metadata)

		override(207, 244); // Beetroots

		override(210, 188); // Repeating command block
		override(211, 189); // Chain command block
		override(212, 207); // Frosted ice

		override(218, 251); // Observer
		override(219, 218); // White shulker box
		overridePcToPe(220, 218); // Orange shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(221, 218); // Magenta shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(222, 218); // Light blue shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(223, 218); // Yellow shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(224, 218); // Lime shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(225, 218); // Pink shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(226, 218); // Gray shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(227, 218); // Silver shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(228, 218); // Cyan shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(229, 218); // Purple shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(230, 218); // Blue shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(231, 218); // Brown shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(232, 218); // Green shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(233, 218); // Red shulker box -> White shulker box (TODO override metadata)
		overridePcToPe(234, 218); // Black shulker box -> White shulker box (TODO override metadata)
		override(235, 220); // White glazed terracotta
		override(236, 221); // Orange glazed terracotta
		override(237, 222); // Magenta glazed terracotta
		override(238, 223); // Light blue glazed terracotta
		override(239, 224); // Yellow glazed terracotta
		override(240, 225); // Lime glazed terracotta
		override(241, 226); // Pink glazed terracotta
		override(242, 227); // Gray glazed terracotta
		override(243, 228); // Silver glazed terracotta
		override(244, 229); // Cyan glazed terracotta
		override(245, 219); // Purple glazed terracotta
		override(246, 231); // Blue glazed terracotta
		override(247, 232); // Brown glazed terracotta
		override(248, 233); // Green glazed terracotta
		override(249, 234); // Red glazed terracotta
		override(250, 235); // Black glazed terracotta
		override(251, 236); // Concrete
		override(252, 237); // Concrete powder

		override(255, 252); // Structure Block

		// Items
		overridePcToPe(326, 325); // Water Bucket -> Bucket (TODO override metadata)
		overridePcToPe(327, 325); // Lava Bucket -> Bucket (TODO override metadata)

		overridePcToPe(335, 325); // Milk Bucket -> Bucket (TODO override metadata)

		overridePcToPe(343, 342); // Minecart with Furnace -> Minecart with Chest (not in PE)

		override(416, 425); // Armor stand

		override(422, 443); // Command block minecart

		override(425, 446); // Banner

		override(434, 457); // Beetroot
		override(435, 458); // Beetroot seeds
		override(436, 459); // Beetroot soup

		overridePcToPe(439, 262); // Spectral arrow -> arrow (not in PE)
		overridePcToPe(440, 262); // Tipped arro -> arrow (not in PE)

		overridePcToPe(442, 446); // Shield -> banner (not in PE)
		override(443, 444); // Elytra
		overridePcToPe(444, 333); // Spruce boat -> oak boat (TODO override metadata)
		overridePcToPe(445, 333); // Birch boat -> oak boat (TODO override metadata)
		overridePcToPe(446, 333); // Jungle boat -> oak boat (TODO override metadata)
		overridePcToPe(447, 333); // Acacia boat -> oak boat (TODO override metadata)
		overridePcToPe(448, 333); // Dark oak boat -> oak boat (TODO override metadata)
		override(449, 450); // Totem
		override(450, 445); // Shulker shell

		overridePcToPe(453, 387); // Knowledge book -> written book (not in PE)

		// RECORDS
		override(2256, 500); // 13
		override(2257, 501); // Cat
		override(2258, 502); // Blocks
		override(2259, 503); // Chirp
		override(2260, 504); // Far
		override(2261, 505); // Mall
		override(2262, 506); // Mellohi
		override(2263, 507); // Stal
		override(2264, 508); // Strad
		override(2265, 509); // Ward
		override(2266, 510); // 11
		override(2267, 511); // Wait
	}

	private void swap(int id1, int id2) {
		override(id1, id2);
		override(id2, id1);
	}

	private void override(int pcId, int peId) {
		overridePcToPe(pcId, peId);
		overridePeToPc(peId, pcId);
	}

	private void overridePcToPe(int pcId, int peId) {
		if (pcId <= MAX_BLOCK_VALUE && peId > MAX_BLOCK_VALUE) {
			peId = MAX_BLOCK_VALUE - peId;
		}
		if (!PC_TO_PE_OVERRIDE.containsKey(pcId)) {
			PC_TO_PE_OVERRIDE.put(pcId, peId);
		}
	}

	private void overridePeToPc(int peId, int pcId) {
		if (pcId <= MAX_BLOCK_VALUE && peId > MAX_BLOCK_VALUE) {
			peId = MAX_BLOCK_VALUE - peId;
		}
		if (!PE_TO_PC_OVERRIDE.containsKey(peId)) {
			PE_TO_PC_OVERRIDE.put(peId, pcId);
		}
	}

	private ItemEntry itranslateToPe(int id, int data) {
		return itranslateToPe(new ItemEntry(id, data));
	}

	private ItemEntry itranslateToPe(ItemEntry entry) {
		if (!PC_TO_PE_OVERRIDE.containsKey(entry.getId())) {
			return entry;
		}
		return new ItemEntry(PC_TO_PE_OVERRIDE.get(entry.getId()), entry.getData());
	}

	private ItemEntry itranslateToPc(int id, int data) {
		return itranslateToPc(new ItemEntry(id, data));
	}

	private ItemEntry itranslateToPc(ItemEntry entry) {
		if (!PE_TO_PC_OVERRIDE.containsKey(entry.getId())) {
			return entry;
		}
		return new ItemEntry(PE_TO_PC_OVERRIDE.get(entry.getId()), entry.getData());
	}

	private BlockEntry btranslateToPe(int id, int data) {
		return btranslateToPe(new BlockEntry(id, data));
	}

	private BlockEntry btranslateToPe(BlockEntry entry) {
		if (!PC_TO_PE_OVERRIDE.containsKey(entry.getId())) {
			return entry;
		}
		int peId = PC_TO_PE_OVERRIDE.get(entry.getId());
		if (peId < 0) {
			peId *= -1;
			peId += MAX_BLOCK_VALUE; // Only block ITEMS can have negative
		}
		return new BlockEntry(peId, entry.getData());
	}

	private BlockEntry btranslateToPc(int id, int data) {
		return btranslateToPc(new BlockEntry(id, data));
	}

	private BlockEntry btranslateToPc(BlockEntry entry) {
		int peId = entry.getId();
		if (peId > MAX_BLOCK_VALUE) {
			peId = MAX_BLOCK_VALUE - peId; // Overriding as item id
		}
		if (!PE_TO_PC_OVERRIDE.containsKey(peId)) {
			return entry;
		}
		return new BlockEntry(PE_TO_PC_OVERRIDE.get(peId), entry.getData());
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
