package misat11.hybrid.blockitems.flattening;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import misat11.hybrid.Platform;
import misat11.hybrid.blockitems.BlockEntry;
import misat11.hybrid.blockitems.IBlockTranslator;

public class FlatteningBlockTranslator implements IBlockTranslator<FlattedBlockState> {

	public static final int AXIS_X = 0x04;
	public static final int AXIS_Y = 0x00;
	public static final int AXIS_Z = 0x08;
	public static final int AXIS_ALL_SIDE = 0x0c; // All side logs

	private final Map<FlattedBlockState, BlockEntry> PC_TO_PE_OVERRIDE = new HashMap<>();
	private final Map<Integer, Map<Integer, FlattedBlockState>> PE_TO_PC_OVERRIDE = new HashMap<>();

	private final Map<Integer, String> peIdToName = new HashMap<>();
	private final Map<String, Integer> peNameToId = new HashMap<>();

	private final IFlatteningBlockData flatteningData;

	private static class TableEntry {
		private int id;
		private int data;
		private String name;
	}

	public FlatteningBlockTranslator(IFlatteningBlockData flatteningData) {
		this.flatteningData = flatteningData;

		Platform.log("§f[Flattening] §aLoading block translates...");

		Gson gson = new Gson();
		Reader reader = new InputStreamReader(
				FlatteningBlockTranslator.class.getResourceAsStream("/runtimeid_table.json"), StandardCharsets.UTF_8);
		Type collectionType = new TypeToken<Collection<TableEntry>>() {
		}.getType();
		Collection<TableEntry> entries = gson.fromJson(reader, collectionType);

		for (TableEntry entry : entries) {
			peNameToId.put(entry.name.toLowerCase(), entry.id);
			peIdToName.put(entry.id, entry.name.toLowerCase());
		}

		override("granite", "stone", 1);
		override("polished_granite", "stone", 2);
		override("diorite", "stone", 3);
		override("polished_diorite", "stone", 4);
		override("andesite", "stone", 5);
		override("polished_andesite", "stone", 6);
		
		override("grass_block", "grass");

		override("coarse_dirt", "dirt", 1);

		override("oak_planks", "planks");
		override("spruce_planks", "planks", 1);
		override("birch_planks", "planks", 2);
		override("jungle_planks", "planks", 3);
		override("acacia_planks", "planks", 4);
		override("dark_oak_planks", "planks", 5);

		override("oak_sapling", "sapling");
		override("oak_sapling", properties("stage", "1"), "sapling");
		override("spruce_sapling", "sapling", 1);
		override("spruce_sapling", properties("stage", "1"), "sapling", 1);
		override("birch_sapling", "sapling", 2);
		override("birch_sapling", properties("stage", "1"), "sapling", 2);
		override("jungle_sapling", "sapling", 3);
		override("jungle_sapling", properties("stage", "1"), "sapling", 3);
		override("acacia_sapling", "sapling", 4);
		override("acacia_sapling", properties("stage", "1"), "sapling", 4);
		override("dark_oak_sapling", "sapling", 5);
		override("dark_oak_sapling", properties("stage", "1"), "sapling", 5);

		overrideAgeable("water", "level", 15);
		overrideAgeable("lava", "level", 15);

		override("red_sand", "sand", 1);

		override("oak_log", properties("axis", "y"), "log", 0 | AXIS_Y);
		override("oak_log", properties("axis", "x"), "log", 0 | AXIS_X);
		override("oak_log", properties("axis", "z"), "log", 0 | AXIS_Z);

		override("spruce_log", properties("axis", "y"), "log", 1 | AXIS_Y);
		override("spruce_log", properties("axis", "x"), "log", 1 | AXIS_X);
		override("spruce_log", properties("axis", "z"), "log", 1 | AXIS_Z);

		override("birch_log", properties("axis", "y"), "log", 2 | AXIS_Y);
		override("birch_log", properties("axis", "x"), "log", 2 | AXIS_X);
		override("birch_log", properties("axis", "z"), "log", 2 | AXIS_Z);

		override("jungle_log", properties("axis", "y"), "log", 3 | AXIS_Y);
		override("jungle_log", properties("axis", "x"), "log", 3 | AXIS_X);
		override("jungle_log", properties("axis", "z"), "log", 3 | AXIS_Z);

		override("acacia_log", properties("axis", "y"), "log2", 0 | AXIS_Y);
		override("acacia_log", properties("axis", "x"), "log2", 0 | AXIS_X);
		override("acacia_log", properties("axis", "z"), "log2", 0 | AXIS_Z);

		override("dark_oak_log", properties("axis", "y"), "log2", 1 | AXIS_Y);
		override("dark_oak_log", properties("axis", "x"), "log2", 1 | AXIS_X);
		override("dark_oak_log", properties("axis", "z"), "log2", 1 | AXIS_Z);

		override("stripped_spruce_log", properties("axis", "x"), 1);
		override("stripped_spruce_log", properties("axis", "y"), 0);
		override("stripped_spruce_log", properties("axis", "z"), 2);

		override("stripped_birch_log", properties("axis", "x"), 1);
		override("stripped_birch_log", properties("axis", "y"), 0);
		override("stripped_birch_log", properties("axis", "z"), 2);

		override("stripped_jungle_log", properties("axis", "x"), 1);
		override("stripped_jungle_log", properties("axis", "y"), 0);
		override("stripped_jungle_log", properties("axis", "z"), 2);

		override("stripped_acacia_log", properties("axis", "x"), 1);
		override("stripped_acacia_log", properties("axis", "y"), 0);
		override("stripped_acacia_log", properties("axis", "z"), 2);

		override("stripped_dark_oak_log", properties("axis", "x"), 1);
		override("stripped_dark_oak_log", properties("axis", "y"), 0);
		override("stripped_dark_oak_log", properties("axis", "z"), 2);

		override("stripped_oak_log", properties("axis", "x"), 1);
		override("stripped_oak_log", properties("axis", "y"), 0);
		override("stripped_oak_log", properties("axis", "z"), 2);

		override("oak_wood", "log", 0 | AXIS_ALL_SIDE);
		override("spruce_wood", "log", 1 | AXIS_ALL_SIDE);
		override("birch_wood", "log", 2 | AXIS_ALL_SIDE);
		override("jungle_wood", "log", 3 | AXIS_ALL_SIDE);
		override("acacia_wood", "log2", 0 | AXIS_ALL_SIDE);
		override("dark_oak_wood", "log2", 1 | AXIS_ALL_SIDE);

		override("stripped_oak_wood", "stripped_oak_log", 3);
		override("stripped_spruce_wood", "stripped_spruce_log", 3);
		override("stripped_birch_wood", "stripped_birch_log", 3);
		override("stripped_jungle_wood", "stripped_jungle_log", 3);
		override("stripped_acacia_wood", "stripped_acacia_log", 3);
		override("stripped_dark_oak_wood", "stripped_dark_oak_log", 3);

		// TODO check decay and no decay
		override("oak_leaves", "leaves");
		override("spruce_leaves", "leaves", 1);
		override("birch_leaves", "leaves", 2);
		override("jungle_leaves", "leaves", 3);
		override("acacia_leaves", "leaves2");
		override("dark_oak_leaves", "leaves2", 1);

		override("wet_sponge", "sponge", 1);

		override("dispenser", properties("facing", "north", "triggered", "false"), 2);
		override("dispenser", properties("facing", "south", "triggered", "false"), 3);
		override("dispenser", properties("facing", "east", "triggered", "false"), 5);
		override("dispenser", properties("facing", "west", "triggered", "false"), 4);
		override("dispenser", properties("facing", "up", "triggered", "false"), 1);
		override("dispenser", properties("facing", "down", "triggered", "false"), 0);
		override("dispenser", properties("facing", "north", "triggered", "true"), 2 | 0x08);
		override("dispenser", properties("facing", "south", "triggered", "true"), 3 | 0x08);
		override("dispenser", properties("facing", "east", "triggered", "true"), 5 | 0x08);
		override("dispenser", properties("facing", "west", "triggered", "true"), 4 | 0x08);
		override("dispenser", properties("facing", "up", "triggered", "true"), 1 | 0x08);
		override("dispenser", properties("facing", "down", "triggered", "true"), 0 | 0x08);

		override("chiseled_sandstone", "sandstone", 1);
		override("cut_sandstone", "sandstone", 2);

		override("note_block", "noteblock");

		overrideBed("white_bed", "bed");
		overrideBed("orange_bed", "bed");
		overrideBed("magenta_bed", "bed");
		overrideBed("light_blue_bed", "bed");
		overrideBed("yellow_bed", "bed");
		overrideBed("lime_bed", "bed");
		overrideBed("pink_bed", "bed");
		overrideBed("gray_bed", "bed");
		overrideBed("light_gray_bed", "bed");
		overrideBed("cyan_bed", "bed");
		overrideBed("purple_bed", "bed");
		overrideBed("blue_bed", "bed");
		overrideBed("brown_bed", "bed");
		overrideBed("green_bed", "bed");
		overrideBed("red_bed", "bed");
		overrideBed("black_bed", "bed");

		override("powered_rail", properties("shape", "north_south", "powered", "false"), "golden_rail", 0);
		override("powered_rail", properties("shape", "east_west", "powered", "false"), "golden_rail", 1);
		override("powered_rail", properties("shape", "ascending_north", "powered", "false"), "golden_rail", 4);
		override("powered_rail", properties("shape", "ascending_south", "powered", "false"), "golden_rail", 5);
		override("powered_rail", properties("shape", "ascending_east", "powered", "false"), "golden_rail", 2);
		override("powered_rail", properties("shape", "ascending_west", "powered", "false"), "golden_rail", 3);
		override("powered_rail", properties("shape", "north_south", "powered", "true"), "golden_rail", 0 | 0x08);
		override("powered_rail", properties("shape", "east_west", "powered", "true"), "golden_rail", 1 | 0x08);
		override("powered_rail", properties("shape", "ascending_north", "powered", "true"), "golden_rail", 4 | 0x08);
		override("powered_rail", properties("shape", "ascending_south", "powered", "true"), "golden_rail", 5 | 0x08);
		override("powered_rail", properties("shape", "ascending_east", "powered", "true"), "golden_rail", 2 | 0x08);
		override("powered_rail", properties("shape", "ascending_west", "powered", "true"), "golden_rail", 3 | 0x08);

		override("detector_rail", properties("shape", "north_south", "powered", "false"), 0);
		override("detector_rail", properties("shape", "east_west", "powered", "false"), 1);
		override("detector_rail", properties("shape", "ascending_north", "powered", "false"), 4);
		override("detector_rail", properties("shape", "ascending_south", "powered", "false"), 5);
		override("detector_rail", properties("shape", "ascending_east", "powered", "false"), 2);
		override("detector_rail", properties("shape", "ascending_west", "powered", "false"), 3);
		override("detector_rail", properties("shape", "north_south", "powered", "true"), 0 | 0x08);
		override("detector_rail", properties("shape", "east_west", "powered", "true"), 1 | 0x08);
		override("detector_rail", properties("shape", "ascending_north", "powered", "true"), 4 | 0x08);
		override("detector_rail", properties("shape", "ascending_south", "powered", "true"), 5 | 0x08);
		override("detector_rail", properties("shape", "ascending_east", "powered", "true"), 2 | 0x08);
		override("detector_rail", properties("shape", "ascending_west", "powered", "true"), 3 | 0x08);

		override("sticky_piston", properties("facing", "north", "extended", "false"), 2);
		override("sticky_piston", properties("facing", "south", "extended", "false"), 3);
		override("sticky_piston", properties("facing", "east", "extended", "false"), 5);
		override("sticky_piston", properties("facing", "west", "extended", "false"), 4);
		override("sticky_piston", properties("facing", "up", "extended", "false"), 1);
		override("sticky_piston", properties("facing", "down", "extended", "false"), 0);
		override("sticky_piston", properties("facing", "north", "extended", "true"), 2 | 0x08);
		override("sticky_piston", properties("facing", "south", "extended", "true"), 3 | 0x08);
		override("sticky_piston", properties("facing", "east", "extended", "true"), 5 | 0x08);
		override("sticky_piston", properties("facing", "west", "extended", "true"), 4 | 0x08);
		override("sticky_piston", properties("facing", "up", "extended", "true"), 1 | 0x08);
		override("sticky_piston", properties("facing", "down", "extended", "true"), 0 | 0x08);

		override("cobweb", "web");
		override("tall_grass", "grass");
		override("fern", "grass", 1);

		override("dead_bush", "deadbush");

		override("tall_seagrass", properties("half", "upper"), "seagrass", 1);
		override("tall_seagrass", properties("half", "lower"), "seagrass", 2);

		override("piston", properties("facing", "north", "extended", "false"), 2);
		override("piston", properties("facing", "south", "extended", "false"), 3);
		override("piston", properties("facing", "east", "extended", "false"), 5);
		override("piston", properties("facing", "west", "extended", "false"), 4);
		override("piston", properties("facing", "up", "extended", "false"), 1);
		override("piston", properties("facing", "down", "extended", "false"), 0);
		override("piston", properties("facing", "north", "extended", "true"), 2 | 0x08);
		override("piston", properties("facing", "south", "extended", "true"), 3 | 0x08);
		override("piston", properties("facing", "east", "extended", "true"), 5 | 0x08);
		override("piston", properties("facing", "west", "extended", "true"), 4 | 0x08);
		override("piston", properties("facing", "up", "extended", "true"), 1 | 0x08);
		override("piston", properties("facing", "down", "extended", "true"), 0 | 0x08);

		override("piston_head", properties("facing", "north"), "pistonarmcollision", 2); // ?
		override("piston_head", properties("facing", "south"), "pistonarmcollision", 3); // ?
		override("piston_head", properties("facing", "east"), "pistonarmcollision", 5); // ?
		override("piston_head", properties("facing", "west"), "pistonarmcollision", 4); // ?
		override("piston_head", properties("facing", "up"), "pistonarmcollision", 1); // ?
		override("piston_head", properties("facing", "down"), "pistonarmcollision", 0); // ?

		override("white_wool", "wool");
		override("orange_wool", "wool", 1);
		override("magenta_wool", "wool", 2);
		override("light_blue_wool", "wool", 3);
		override("yellow_wool", "wool", 4);
		override("lime_wool", "wool", 5);
		override("pink_wool", "wool", 6);
		override("gray_wool", "wool", 7);
		override("light_gray_wool", "wool", 8);
		override("cyan_wool", "wool", 9);
		override("purple_wool", "wool", 10);
		override("blue_wool", "wool", 11);
		override("brown_wool", "wool", 12);
		override("green_wool", "wool", 13);
		override("red_wool", "wool", 14);
		override("black_wool", "wool", 15);

		override("moving_piston", properties("facing", "north"), "movingblock", 2); // ?
		override("moving_piston", properties("facing", "south"), "movingblock", 3); // ?
		override("moving_piston", properties("facing", "east"), "movingblock", 5); // ?
		override("moving_piston", properties("facing", "west"), "movingblock", 4); // ?
		override("moving_piston", properties("facing", "up"), "movingblock", 1); // ?
		override("moving_piston", properties("facing", "down"), "movingblock", 0); // ?

		override("dandelion", "yellow_flower");
		override("poppy", "red_flower");
		override("blue_orchid", "red_flower", 1);
		override("allium", "red_flower", 2);
		override("azure_bluet", "red_flower", 3);
		override("red_tulip", "red_flower", 4);
		override("orange_tulip", "red_flower", 5);
		override("white_tulip", "red_flower", 6);
		override("pink_tulip", "red_flower", 7);
		override("oxeye_daisy", "red_flower", 8);

		override("bricks", "brick_block");

		override("torch", "torch", 5);
		override("wall_torch", properties("facing", "north"), "torch", 4);
		override("wall_torch", properties("facing", "south"), "torch", 3);
		override("wall_torch", properties("facing", "west"), "torch", 2);
		override("wall_torch", properties("facing", "east"), "torch", 1);

		overrideAgeable("fire", "age", 15);

		override("spawner", "mob_spawner");

		overrideStairs("oak_stairs");

		override("chest", properties("facing", "north"), 2);
		override("chest", properties("facing", "south"), 3);
		override("chest", properties("facing", "east"), 5);
		override("chest", properties("facing", "west"), 4);

		overrideAgeable("redstone_wire", "power", 15);

		overrideAgeable("wheat", "age", 7);

		overrideAgeable("farmland", "moisture", 7);

		override("furnace", properties("facing", "north", "lit", "false"), 2);
		override("furnace", properties("facing", "south", "lit", "false"), 3);
		override("furnace", properties("facing", "east", "lit", "false"), 5);
		override("furnace", properties("facing", "west", "lit", "false"), 4);
		override("furnace", properties("facing", "north", "lit", "true"), "lit_furnace", 2);
		override("furnace", properties("facing", "south", "lit", "true"), "lit_furnace", 3);
		override("furnace", properties("facing", "east", "lit", "true"), "lit_furnace", 5);
		override("furnace", properties("facing", "west", "lit", "true"), "lit_furnace", 4);

		overrideAgeable("sign", "rotation", 15, "standing_sign");

		overrideDoor("oak_door", "wooden_door");

		override("ladder", properties("facing", "north"), 2);
		override("ladder", properties("facing", "south"), 3);
		override("ladder", properties("facing", "east"), 5);
		override("ladder", properties("facing", "west"), 4);

		override("rail", properties("shape", "north_south"), 0);
		override("rail", properties("shape", "east_west"), 1);
		override("rail", properties("shape", "ascending_east"), 2);
		override("rail", properties("shape", "ascending_west"), 3);
		override("rail", properties("shape", "ascending_north"), 4);
		override("rail", properties("shape", "ascending_south"), 5);
		override("rail", properties("shape", "south_east"), 6);
		override("rail", properties("shape", "south_west"), 7);
		override("rail", properties("shape", "north_west"), 8);
		override("rail", properties("shape", "north_east"), 9);

		overrideStairs("cobblestone_stairs", "stone_stairs");

		override("wall_sign", properties("facing", "north"), 2);
		override("wall_sign", properties("facing", "south"), 3);
		override("wall_sign", properties("facing", "east"), 5);
		override("wall_sign", properties("facing", "west"), 4);

		override("lever", properties("face", "floor", "facing", "north", "powered", "false"), 7);
		override("lever", properties("face", "floor", "facing", "west", "powered", "false"), 0);
		override("lever", properties("face", "floor", "facing", "south", "powered", "false"), 7);
		override("lever", properties("face", "floor", "facing", "east", "powered", "false"), 0);
		override("lever", properties("face", "ceiling", "facing", "north", "powered", "false"), 4);
		override("lever", properties("face", "ceiling", "facing", "west", "powered", "false"), 2);
		override("lever", properties("face", "ceiling", "facing", "south", "powered", "false"), 3);
		override("lever", properties("face", "ceiling", "facing", "east", "powered", "false"), 1);
		override("lever", properties("face", "wall", "facing", "north", "powered", "false"), 5);
		override("lever", properties("face", "wall", "facing", "west", "powered", "false"), 6);
		override("lever", properties("face", "wall", "facing", "south", "powered", "false"), 5);
		override("lever", properties("face", "wall", "facing", "east", "powered", "false"), 6);
		override("lever", properties("face", "floor", "facing", "north", "powered", "true"), 7 | 0x08);
		override("lever", properties("face", "floor", "facing", "west", "powered", "true"), 0 | 0x08);
		override("lever", properties("face", "floor", "facing", "south", "powered", "true"), 7 | 0x08);
		override("lever", properties("face", "floor", "facing", "east", "powered", "true"), 0 | 0x08);
		override("lever", properties("face", "ceiling", "facing", "north", "powered", "true"), 4 | 0x08);
		override("lever", properties("face", "ceiling", "facing", "west", "powered", "true"), 2 | 0x08);
		override("lever", properties("face", "ceiling", "facing", "south", "powered", "true"), 3 | 0x08);
		override("lever", properties("face", "ceiling", "facing", "east", "powered", "true"), 1 | 0x08);
		override("lever", properties("face", "wall", "facing", "north", "powered", "true"), 5 | 0x08);
		override("lever", properties("face", "wall", "facing", "west", "powered", "true"), 6 | 0x08);
		override("lever", properties("face", "wall", "facing", "south", "powered", "true"), 5 | 0x08);
		override("lever", properties("face", "wall", "facing", "east", "powered", "true"), 6 | 0x08);

		override("stone_pressure_plate", properties("powered", "true"), 1);

		overrideDoor("iron_door");

		override("oak_pressure_plate", "wooden_pressure_plate");

		override("oak_pressure_plate", properties("powered", "true"), "wooden_pressure_plate", 1);
		override("spruce_pressure_plate", properties("powered", "true"), 1);
		override("birch_pressure_plate", properties("powered", "true"), 1);
		override("jungle_pressure_plate", properties("powered", "true"), 1);
		override("acacia_pressure_plate", properties("powered", "true"), 1);
		override("dark_oak_pressure_plate", properties("powered", "true"), 1);

		override("redstone_ore", properties("lit", "true"), "lit_redstone_ore");

		override("redstone_torch", properties("lit", "true"), "redstone_torch", 5);
		override("redstone_torch", properties("lit", "false"), "unlit_redstone_torch", 5);
		override("redstone_wall_torch", properties("lit", "true", "facing", "north"), "redstone_torch", 4);
		override("redstone_wall_torch", properties("lit", "false", "facing", "north"), "unlit_redstone_torch", 4);
		override("redstone_wall_torch", properties("lit", "true", "facing", "east"), "redstone_torch", 1);
		override("redstone_wall_torch", properties("lit", "false", "facing", "east"), "unlit_redstone_torch", 1);
		override("redstone_wall_torch", properties("lit", "true", "facing", "south"), "redstone_torch", 3);
		override("redstone_wall_torch", properties("lit", "false", "facing", "south"), "unlit_redstone_torch", 3);
		override("redstone_wall_torch", properties("lit", "true", "facing", "west"), "redstone_torch", 2);
		override("redstone_wall_torch", properties("lit", "false", "facing", "west"), "unlit_redstone_torch", 2);

		overrideButton("stone_button");

		overrideAgeable("snow", "layers", 1, 8, "snow_layer", -1);

		override("snow_block", "snow");

		overrideAgeable("cactus", "age", 15);

		overrideAgeable("sugar_cane", "age", 15, "reeds");

		override("jukebox", properties("has_record", "true"), 1);

		override("oak_fence", "fence");
		override("spruce_fence", "fence", 1);
		override("birch_fence", "fence", 2);
		override("jungle_fence", "fence", 3);
		override("acacia_fence", "fence", 4);
		override("dark_oak_fence", "fence", 5);

		override("pumpkin", properties("facing", "south"), 0);
		override("pumpkin", properties("facing", "west"), 1);
		override("pumpkin", properties("facing", "north"), 2);
		override("pumpkin", properties("facing", "east"), 3);

		override("nether_portal", "portal");

		override("carved_pumpkin", properties("facing", "south"), 0);
		override("carved_pumpkin", properties("facing", "west"), 1);
		override("carved_pumpkin", properties("facing", "north"), 2);
		override("carved_pumpkin", properties("facing", "east"), 3);

		override("jack_o_lantern", properties("facing", "south"), "lit_pumpkin", 0);
		override("jack_o_lantern", properties("facing", "west"), "lit_pumpkin", 1);
		override("jack_o_lantern", properties("facing", "north"), "lit_pumpkin", 2);
		override("jack_o_lantern", properties("facing", "east"), "lit_pumpkin", 3);

		overrideAgeable("cake", "bites", 6);

		overrideAgeable("repeater", properties("powered", "true", "facing", "north"), "delay", 1, 4, "powered_repeater",
				x -> 4 * (x - 1));
		overrideAgeable("repeater", properties("powered", "true", "facing", "south"), "delay", 1, 4, "powered_repeater",
				x -> 2 + 4 * (x - 1));
		overrideAgeable("repeater", properties("powered", "true", "facing", "east"), "delay", 1, 4, "powered_repeater",
				x -> 1 + 4 * (x - 1));
		overrideAgeable("repeater", properties("powered", "true", "facing", "west"), "delay", 1, 4, "powered_repeater",
				x -> 3 + 4 * (x - 1));
		overrideAgeable("repeater", properties("powered", "false", "facing", "north"), "delay", 1, 4,
				"unpowered_repeater", x -> 4 * (x - 1));
		overrideAgeable("repeater", properties("powered", "false", "facing", "south"), "delay", 1, 4,
				"unpowered_repeater", x -> 2 + 4 * (x - 1));
		overrideAgeable("repeater", properties("powered", "false", "facing", "east"), "delay", 1, 4,
				"unpowered_repeater", x -> 1 + 4 * (x - 1));
		overrideAgeable("repeater", properties("powered", "false", "facing", "west"), "delay", 1, 4,
				"unpowered_repeater", x -> 3 + 4 * (x - 1));

		override("white_stained_glass", "stained_glass");
		override("orange_stained_glass", "stained_glass", 1);
		override("magenta_stained_glass", "stained_glass", 2);
		override("light_blue_stained_glass", "stained_glass", 3);
		override("yellow_stained_glass", "stained_glass", 4);
		override("lime_stained_glass", "stained_glass", 5);
		override("pink_stained_glass", "stained_glass", 6);
		override("gray_stained_glass", "stained_glass", 7);
		override("light_gray_stained_glass", "stained_glass", 8);
		override("cyan_stained_glass", "stained_glass", 9);
		override("purple_stained_glass", "stained_glass", 10);
		override("blue_stained_glass", "stained_glass", 11);
		override("brown_stained_glass", "stained_glass", 12);
		override("green_stained_glass", "stained_glass", 13);
		override("red_stained_glass", "stained_glass", 14);
		override("black_stained_glass", "stained_glass", 15);

		overrideTrapdoor("oak_trapdoor", "trapdoor");
		overrideTrapdoor("spruce_trapdoor");
		overrideTrapdoor("birch_trapdoor");
		overrideTrapdoor("jungle_trapdoor");
		overrideTrapdoor("acacia_trapdoor");
		overrideTrapdoor("dark_oak_trapdoor");

		override("infested_stone", "monster_egg");
		override("infested_cobblestone", "monster_egg", 1);
		override("infested_stone_bricks", "monster_egg", 2);
		override("infested_mossy_stone_bricks", "monster_egg", 3);
		override("infested_cracked_stone_bricks", "monster_egg", 4);
		override("infested_chiseled_stone_bricks", "monster_egg", 5);

		override("stone_bricks", "stonebrick");
		override("mossy_stone_bricks", "stonebrick", 1);
		override("cracked_stone_bricks", "stonebrick", 2);
		override("chiseled_stone_bricks", "stonebrick", 3);

		// TODO brown_mushroom_block and red_mushroom_block

		override("mushroom_stem", "brown_mushroom_block", 10); // TODO facing

		override("melon", "melon_block");

		override("attached_pumpkin_stem", "pumpkin_stem", 7);

		override("attached_melon_stem", "melon_stem", 7);

		overrideAgeable("pumpkin_stem", "age", 7);

		overrideAgeable("melon_stem", "age", 7);

		// TODO vine

		overrideFenceGate("oak_fence_gate", "fence_gate");

		overrideStairs("brick_stairs");

		overrideStairs("stone_brick_stairs");

		override("lily_pad", "waterlily");

		override("nether_bricks", "nether_brick");

		overrideStairs("nether_brick_stairs");

		overrideAgeable("nether_wart", "age", 3);

		override("brewing_stand", properties("has_bottle_0", "true", "has_bottle_1", "false", "has_bottle_2", "false"),
				0x01);
		override("brewing_stand", properties("has_bottle_0", "false", "has_bottle_1", "true", "has_bottle_2", "false"),
				0x02);
		override("brewing_stand", properties("has_bottle_0", "false", "has_bottle_1", "false", "has_bottle_2", "true"),
				0x04);
		override("brewing_stand", properties("has_bottle_0", "true", "has_bottle_1", "true", "has_bottle_2", "false"),
				0x01 | 0x02);
		override("brewing_stand", properties("has_bottle_0", "true", "has_bottle_1", "false", "has_bottle_2", "true"),
				0x01 | 0x04);
		override("brewing_stand", properties("has_bottle_0", "true", "has_bottle_1", "true", "has_bottle_2", "true"),
				0x01 | 0x02 | 0x04);
		override("brewing_stand", properties("has_bottle_0", "false", "has_bottle_1", "true", "has_bottle_2", "true"),
				0x02 | 0x04);
		override("brewing_stand", properties("has_bottle_0", "false", "has_bottle_1", "false", "has_bottle_2", "false"),
				0x00);

		overrideAgeable("cauldron", "level", 3);

		override("end_portal_frame", properties("facing", "north", "eye", "false"), 2);
		override("end_portal_frame", properties("facing", "south", "eye", "false"), 0);
		override("end_portal_frame", properties("facing", "east", "eye", "false"), 3);
		override("end_portal_frame", properties("facing", "west", "eye", "false"), 1);
		override("end_portal_frame", properties("facing", "north", "eye", "true"), 2 | 0x04);
		override("end_portal_frame", properties("facing", "south", "eye", "true"), 0 | 0x04);
		override("end_portal_frame", properties("facing", "east", "eye", "true"), 3 | 0x04);
		override("end_portal_frame", properties("facing", "west", "eye", "true"), 1 | 0x04);

		override("redstone_lamp", properties("lit", "true"), "lit_redstone_lamp");

		override("cocoa", properties("facing", "north", "age", "0"), 0);
		override("cocoa", properties("facing", "south", "age", "0"), 1);
		override("cocoa", properties("facing", "east", "age", "0"), 2);
		override("cocoa", properties("facing", "west", "age", "0"), 3);
		override("cocoa", properties("facing", "north", "age", "1"), 0 | 0x04);
		override("cocoa", properties("facing", "south", "age", "1"), 1 | 0x04);
		override("cocoa", properties("facing", "east", "age", "1"), 2 | 0x04);
		override("cocoa", properties("facing", "west", "age", "1"), 3 | 0x04);
		override("cocoa", properties("facing", "north", "age", "2"), 0 | 0x08);
		override("cocoa", properties("facing", "south", "age", "2"), 1 | 0x08);
		override("cocoa", properties("facing", "east", "age", "2"), 2 | 0x08);
		override("cocoa", properties("facing", "west", "age", "2"), 3 | 0x08);

		overrideStairs("sandstone_stairs");

		override("ender_chest", properties("facing", "north"), 2);
		override("ender_chest", properties("facing", "south"), 3);
		override("ender_chest", properties("facing", "east"), 5);
		override("ender_chest", properties("facing", "west"), 4);

		override("tripwire_hook", properties("facing", "north", "attached", "false", "powered", "false"), 2);
		override("tripwire_hook", properties("facing", "south", "attached", "false", "powered", "false"), 0);
		override("tripwire_hook", properties("facing", "east", "attached", "false", "powered", "false"), 3);
		override("tripwire_hook", properties("facing", "west", "attached", "false", "powered", "false"), 1);
		override("tripwire_hook", properties("facing", "north", "attached", "true", "powered", "false"), 2 | 0x04);
		override("tripwire_hook", properties("facing", "south", "attached", "true", "powered", "false"), 0 | 0x04);
		override("tripwire_hook", properties("facing", "east", "attached", "true", "powered", "false"), 3 | 0x04);
		override("tripwire_hook", properties("facing", "west", "attached", "true", "powered", "false"), 1 | 0x04);
		override("tripwire_hook", properties("facing", "north", "attached", "false", "powered", "true"), 2 | 0x08);
		override("tripwire_hook", properties("facing", "south", "attached", "false", "powered", "true"), 0 | 0x08);
		override("tripwire_hook", properties("facing", "east", "attached", "false", "powered", "true"), 3 | 0x08);
		override("tripwire_hook", properties("facing", "west", "attached", "false", "powered", "true"), 1 | 0x08);
		override("tripwire_hook", properties("facing", "north", "attached", "true", "powered", "true"),
				2 | 0x04 | 0x08);
		override("tripwire_hook", properties("facing", "south", "attached", "true", "powered", "true"),
				0 | 0x04 | 0x08);
		override("tripwire_hook", properties("facing", "east", "attached", "true", "powered", "true"), 3 | 0x04 | 0x08);
		override("tripwire_hook", properties("facing", "west", "attached", "true", "powered", "true"), 1 | 0x04 | 0x08);

		override("tripwire", properties("attached", "false", "disarmed", "false", "powered", "false"), 0);
		override("tripwire", properties("attached", "true", "disarmed", "false", "powered", "false"), 0x04);
		override("tripwire", properties("attached", "false", "disarmed", "true", "powered", "false"), 0x08);
		override("tripwire", properties("attached", "false", "disarmed", "false", "powered", "true"), 0x01);
		override("tripwire", properties("attached", "true", "disarmed", "true", "powered", "false"), 0x04 | 0x08);
		override("tripwire", properties("attached", "false", "disarmed", "true", "powered", "true"), 0x08 | 0x01);
		override("tripwire", properties("attached", "true", "disarmed", "true", "powered", "true"), 0x01 | 0x04 | 0x08);
		override("tripwire", properties("attached", "true", "disarmed", "false", "powered", "true"), 0x04 | 0x01);

		overrideStairs("spruce_stairs");

		overrideStairs("birch_stairs");

		overrideStairs("jungle_stairs");

		override("command_block", properties("facing", "north", "confitional", "false"), 2);
		override("command_block", properties("facing", "south", "confitional", "false"), 3);
		override("command_block", properties("facing", "east", "confitional", "false"), 5);
		override("command_block", properties("facing", "west", "confitional", "false"), 4);
		override("command_block", properties("facing", "up", "confitional", "false"), 1);
		override("command_block", properties("facing", "down", "confitional", "false"), 0);
		override("command_block", properties("facing", "north", "confitional", "true"), 2 | 0x08);
		override("command_block", properties("facing", "south", "confitional", "true"), 3 | 0x08);
		override("command_block", properties("facing", "east", "confitional", "true"), 5 | 0x08);
		override("command_block", properties("facing", "west", "confitional", "true"), 4 | 0x08);
		override("command_block", properties("facing", "up", "confitional", "true"), 1 | 0x08);
		override("command_block", properties("facing", "down", "confitional", "true"), 0 | 0x08);

		override("mossy_cobblestone_wall", "cobblestone_wall", 1);

		// TODO flower pot with flowers
		override("potted_dandelion", "flower_pot", 1);
		override("potted_poppy", "flower_pot", 1);
		override("potted_blue_orchid", "flower_pot", 1);
		override("potted_allium", "flower_pot", 1);
		override("potted_azure_bluet", "flower_pot", 1);
		override("potted_red_tulip", "flower_pot", 1);
		override("potted_orange_tulip", "flower_pot", 1);
		override("potted_white_tulip", "flower_pot", 1);
		override("potted_pink_tulip", "flower_pot", 1);
		override("potted_oxeye_daisy", "flower_pot", 1);
		override("potted_oak_sapling", "flower_pot", 1);
		override("potted_spruce_sapling", "flower_pot", 1);
		override("potted_birch_sapling", "flower_pot", 1);
		override("potted_jungle_sapling", "flower_pot", 1);
		override("potted_acacia_sapling", "flower_pot", 1);
		override("potted_dark_oak_sapling", "flower_pot", 1);
		override("potted_fern", "flower_pot", 1);
		override("potted_dead_bush", "flower_pot", 1);
		override("potted_red_mushroom", "flower_pot", 1);
		override("potted_brown_mushroom", "flower_pot", 1);
		override("potted_cactus", "flower_pot", 1);

		overrideAgeable("carrots", "age", 7);
		overrideAgeable("potatoes", "age", 7);

		overrideButton("oak_button", "wooden_button");
		overrideButton("spruce_button");
		overrideButton("birch_button");
		overrideButton("jungle_button");
		overrideButton("acacia_button");
		overrideButton("dark_oak_button");

		override("skeleton_wall_skull", properties("facing", "north"), "skull", 2);
		override("skeleton_wall_skull", properties("facing", "south"), "skull", 3);
		override("skeleton_wall_skull", properties("facing", "east"), "skull", 4);
		override("skeleton_wall_skull", properties("facing", "west"), "skull", 5);
		override("skeleton_skull", "skull", 1);

		override("wither_skeleton_wall_skull", properties("facing", "north"), "skull", 2);
		override("wither_skeleton_wall_skull", properties("facing", "south"), "skull", 3);
		override("wither_skeleton_wall_skull", properties("facing", "east"), "skull", 4);
		override("wither_skeleton_wall_skull", properties("facing", "west"), "skull", 5);
		override("wither_skeleton_skull", "skull", 1);

		override("zombie_wall_head", properties("facing", "north"), "skull", 2);
		override("zombie_wall_head", properties("facing", "south"), "skull", 3);
		override("zombie_wall_head", properties("facing", "east"), "skull", 4);
		override("zombie_wall_head", properties("facing", "west"), "skull", 5);
		override("zombie_head", "skull", 1);

		override("player_wall_head", properties("facing", "north"), "skull", 2);
		override("player_wall_head", properties("facing", "south"), "skull", 3);
		override("player_wall_head", properties("facing", "east"), "skull", 4);
		override("player_wall_head", properties("facing", "west"), "skull", 5);
		override("player_head", "skull", 1);

		override("creeper_wall_head", properties("facing", "north"), "skull", 2);
		override("creeper_wall_head", properties("facing", "south"), "skull", 3);
		override("creeper_wall_head", properties("facing", "east"), "skull", 4);
		override("creeper_wall_head", properties("facing", "west"), "skull", 5);
		override("creeper_head", "skull", 1);

		override("dragon_wall_head", properties("facing", "north"), "skull", 2);
		override("dragon_wall_head", properties("facing", "south"), "skull", 3);
		override("dragon_wall_head", properties("facing", "east"), "skull", 4);
		override("dragon_wall_head", properties("facing", "west"), "skull", 5);
		override("dragon_head", "skull", 1);

		override("anvil", properties("facing", "north"), 0);
		override("anvil", properties("facing", "south"), 2);
		override("anvil", properties("facing", "west"), 1);
		override("anvil", properties("facing", "east"), 3);

		override("chipped_anvil", properties("facing", "north"), "anvil", 4);
		override("chipped_anvil", properties("facing", "south"), "anvil", 7);
		override("chipped_anvil", properties("facing", "west"), "anvil", 6);
		override("chipped_anvil", properties("facing", "east"), "anvil", 5);

		override("damaged_anvil", properties("facing", "north"), "anvil", 8);
		override("damaged_anvil", properties("facing", "south"), "anvil", 11);
		override("damaged_anvil", properties("facing", "west"), "anvil", 10);
		override("damaged_anvil", properties("facing", "east"), "anvil", 9);

		override("trapped_chest", properties("facing", "north"), 2);
		override("trapped_chest", properties("facing", "south"), 3);
		override("trapped_chest", properties("facing", "east"), 5);
		override("trapped_chest", properties("facing", "west"), 4);

		overrideAgeable("light_weighted_pressure_plate", "power", 15);
		overrideAgeable("heavy_weighted_pressure_plate", "power", 15);

		override("comparator", properties("facing", "north", "mode", "compare", "powered", "false"),
				"unpowered_comparator", 0);
		override("comparator", properties("facing", "south", "mode", "compare", "powered", "false"),
				"unpowered_comparator", 2);
		override("comparator", properties("facing", "east", "mode", "compare", "powered", "false"),
				"unpowered_comparator", 1);
		override("comparator", properties("facing", "west", "mode", "compare", "powered", "false"),
				"unpowered_comparator", 3);
		override("comparator", properties("facing", "north", "mode", "subtract", "powered", "false"),
				"unpowered_comparator", 0 | 0x04);
		override("comparator", properties("facing", "south", "mode", "subtract", "powered", "false"),
				"unpowered_comparator", 2 | 0x04);
		override("comparator", properties("facing", "east", "mode", "subtract", "powered", "false"),
				"unpowered_comparator", 1 | 0x04);
		override("comparator", properties("facing", "west", "mode", "subtract", "powered", "false"),
				"unpowered_comparator", 3 | 0x04);
		override("comparator", properties("facing", "north", "mode", "compare", "powered", "true"),
				"powered_comparator", 0 | 0x08);
		override("comparator", properties("facing", "south", "mode", "compare", "powered", "true"),
				"powered_comparator", 2 | 0x08);
		override("comparator", properties("facing", "east", "mode", "compare", "powered", "true"), "powered_comparator",
				1 | 0x08);
		override("comparator", properties("facing", "west", "mode", "compare", "powered", "true"), "powered_comparator",
				3 | 0x08);
		override("comparator", properties("facing", "north", "mode", "subtract", "powered", "true"),
				"powered_comparator", 0 | 0x04 | 0x08);
		override("comparator", properties("facing", "south", "mode", "subtract", "powered", "true"),
				"powered_comparator", 2 | 0x04 | 0x08);
		override("comparator", properties("facing", "east", "mode", "subtract", "powered", "true"),
				"powered_comparator", 1 | 0x04 | 0x08);
		override("comparator", properties("facing", "west", "mode", "subtract", "powered", "true"),
				"powered_comparator", 3 | 0x04 | 0x08);

		overrideAgeable("daylight_detector", properties("inverted", "false"), "power", 15, "daylight_detector");
		overrideAgeable("daylight_detector", properties("inverted", "true"), "power", 15, "daylight_detector_inverted");

		override("nether_quartz_ore", "quartz_ore");

		override("hopper", properties("facing", "north", "enabled", "false"), 2);
		override("hopper", properties("facing", "south", "enabled", "false"), 3);
		override("hopper", properties("facing", "east", "enabled", "false"), 5);
		override("hopper", properties("facing", "west", "enabled", "false"), 4);
		override("hopper", properties("facing", "down", "enabled", "false"), 0);
		override("hopper", properties("facing", "north", "enabled", "true"), 2 | 0x08);
		override("hopper", properties("facing", "south", "enabled", "true"), 3 | 0x08);
		override("hopper", properties("facing", "east", "enabled", "true"), 5 | 0x08);
		override("hopper", properties("facing", "west", "enabled", "true"), 4 | 0x08);
		override("hopper", properties("facing", "down", "enabled", "true"), 0 | 0x08);

		override("chiseled_quartz_block", "quartz_block", 1);

		override("quartz_pillar", properties("axis", "x"), "quartz_block", 2 | AXIS_X);
		override("quartz_pillar", properties("axis", "y"), "quartz_block", 2 | AXIS_Y);
		override("quartz_pillar", properties("axis", "z"), "quartz_block", 2 | AXIS_Z);

		overrideStairs("quartz_stairs");

		override("activator_rail", properties("shape", "north_south", "powered", "false"), 0);
		override("activator_rail", properties("shape", "east_west", "powered", "false"), 1);
		override("activator_rail", properties("shape", "ascending_north", "powered", "false"), 4);
		override("activator_rail", properties("shape", "ascending_south", "powered", "false"), 5);
		override("activator_rail", properties("shape", "ascending_east", "powered", "false"), 2);
		override("activator_rail", properties("shape", "ascending_west", "powered", "false"), 3);
		override("activator_rail", properties("shape", "north_south", "powered", "true"), 0 | 0x08);
		override("activator_rail", properties("shape", "east_west", "powered", "true"), 1 | 0x08);
		override("activator_rail", properties("shape", "ascending_north", "powered", "true"), 4 | 0x08);
		override("activator_rail", properties("shape", "ascending_south", "powered", "true"), 5 | 0x08);
		override("activator_rail", properties("shape", "ascending_east", "powered", "true"), 2 | 0x08);
		override("activator_rail", properties("shape", "ascending_west", "powered", "true"), 3 | 0x08);

		override("dropper", properties("facing", "north", "triggered", "false"), 2);
		override("dropper", properties("facing", "south", "triggered", "false"), 3);
		override("dropper", properties("facing", "east", "triggered", "false"), 5);
		override("dropper", properties("facing", "west", "triggered", "false"), 4);
		override("dropper", properties("facing", "up", "triggered", "false"), 1);
		override("dropper", properties("facing", "down", "triggered", "false"), 0);
		override("dropper", properties("facing", "north", "triggered", "true"), 2 | 0x08);
		override("dropper", properties("facing", "south", "triggered", "true"), 3 | 0x08);
		override("dropper", properties("facing", "east", "triggered", "true"), 5 | 0x08);
		override("dropper", properties("facing", "west", "triggered", "true"), 4 | 0x08);
		override("dropper", properties("facing", "up", "triggered", "true"), 1 | 0x08);
		override("dropper", properties("facing", "down", "triggered", "true"), 0 | 0x08);

		override("white_terracotta", "stained_hardened_clay");
		override("orange_terracotta", "stained_hardened_clay", 1);
		override("magenta_terracotta", "stained_hardened_clay", 2);
		override("light_blue_terracotta", "stained_hardened_clay", 3);
		override("yellow_terracotta", "stained_hardened_clay", 4);
		override("lime_terracotta", "stained_hardened_clay", 5);
		override("pink_terracotta", "stained_hardened_clay", 6);
		override("gray_terracotta", "stained_hardened_clay", 7);
		override("light_gray_terracotta", "stained_hardened_clay", 8);
		override("cyan_terracotta", "stained_hardened_clay", 9);
		override("purple_terracotta", "stained_hardened_clay", 10);
		override("blue_terracotta", "stained_hardened_clay", 11);
		override("brown_terracotta", "stained_hardened_clay", 12);
		override("green_terracotta", "stained_hardened_clay", 13);
		override("red_terracotta", "stained_hardened_clay", 14);
		override("black_terracotta", "stained_hardened_clay", 15);

		override("white_stained_glass_pane", "stained_glass_pane", 0);
		override("orange_stained_glass_pane", "stained_glass_pane", 1);
		override("magenta_stained_glass_pane", "stained_glass_pane", 2);
		override("light_blue_stained_glass_pane", "stained_glass_pane", 3);
		override("yellow_stained_glass_pane", "stained_glass_pane", 4);
		override("lime_stained_glass_pane", "stained_glass_pane", 5);
		override("pink_stained_glass_pane", "stained_glass_pane", 6);
		override("gray_stained_glass_pane", "stained_glass_pane", 7);
		override("light_gray_stained_glass_pane", "stained_glass_pane", 8);
		override("cyan_stained_glass_pane", "stained_glass_pane", 9);
		override("purple_stained_glass_pane", "stained_glass_pane", 10);
		override("blue_stained_glass_pane", "stained_glass_pane", 11);
		override("brown_stained_glass_pane", "stained_glass_pane", 12);
		override("green_stained_glass_pane", "stained_glass_pane", 13);
		override("red_stained_glass_pane", "stained_glass_pane", 14);
		override("black_stained_glass_pane", "stained_glass_pane", 15);

		overrideStairs("acacia_stairs");
		overrideStairs("dark_oak_stairs");

		overrideTrapdoor("iron_trapdoor");

		override("dark_prismarine", "prismarine", 1);
		override("prismarine_bricks", "prismarine", 2);

		overrideStairs("prismarine_stairs");
		overrideStairs("prismarine_brick_stairs", "prismarine_bricks_stairs");
		overrideStairs("dark_prismarine_stairs");

		overrideSlab("prismarine_slab", "stone_slab2", 2, "double_stone_slab2");
		overrideSlab("prismarine_brick_slab", "stone_slab2", 3, "double_stone_slab2");
		overrideSlab("dark_prismarine_slab", "stone_slab2", 4, "double_stone_slab2");

		override("sea_lantern", "sealantern");

		override("hay_block", properties("axis", "x"), 4);
		override("hay_block", properties("axis", "y"), 0);
		override("hay_block", properties("axis", "z"), 8);

		override("white_carpet", "carpet");
		override("orange_carpet", "carpet", 1);
		override("magenta_carpet", "carpet", 2);
		override("light_blue_carpet", "carpet", 3);
		override("yellow_carpet", "carpet", 4);
		override("lime_carpet", "carpet", 5);
		override("pink_carpet", "carpet", 6);
		override("gray_carpet", "carpet", 7);
		override("light_gray_carpet", "carpet", 8);
		override("cyan_carpet", "carpet", 9);
		override("purple_carpet", "carpet", 10);
		override("blue_carpet", "carpet", 11);
		override("brown_carpet", "carpet", 12);
		override("green_carpet", "carpet", 13);
		override("red_carpet", "carpet", 14);
		override("black_carpet", "carpet", 15);

		override("terracotta", "hardened_clay");

		override("sunflower", properties("half", "upper"), "double_plant", 0 | 0x08); // ?
		override("sunflower", properties("half", "lower"), "double_plant", 0);

		override("lilac", properties("half", "upper"), "double_plant", 1 | 0x08); // ?
		override("lilac", properties("half", "lower"), "double_plant", 1);

		override("rose_bush", properties("half", "upper"), "double_plant", 4 | 0x08); // ?
		override("rose_bush", properties("half", "lower"), "double_plant", 4);

		override("peony", properties("half", "upper"), "double_plant", 5 | 0x08); // ?
		override("peony", properties("half", "lower"), "double_plant", 5);

		override("tall_grass", properties("half", "upper"), "double_plant", 2 | 0x08); // ?
		override("tall_grass", properties("half", "lower"), "double_plant", 2);

		override("large_fern", properties("half", "upper"), "double_plant", 3 | 0x08); // ?
		override("large_fern", properties("half", "lower"), "double_plant", 3);

		overrideAgeable("white_banner", "rotation", 15, "standing_banner");
		overrideAgeable("orange_banner", "rotation", 15, "standing_banner");
		overrideAgeable("magenta_banner", "rotation", 15, "standing_banner");
		overrideAgeable("light_blue_banner", "rotation", 15, "standing_banner");
		overrideAgeable("yellow_banner", "rotation", 15, "standing_banner");
		overrideAgeable("lime_banner", "rotation", 15, "standing_banner");
		overrideAgeable("pink_banner", "rotation", 15, "standing_banner");
		overrideAgeable("gray_banner", "rotation", 15, "standing_banner");
		overrideAgeable("light_gray_banner", "rotation", 15, "standing_banner");
		overrideAgeable("cyan_banner", "rotation", 15, "standing_banner");
		overrideAgeable("purple_banner", "rotation", 15, "standing_banner");
		overrideAgeable("blue_banner", "rotation", 15, "standing_banner");
		overrideAgeable("brown_banner", "rotation", 15, "standing_banner");
		overrideAgeable("green_banner", "rotation", 15, "standing_banner");
		overrideAgeable("red_banner", "rotation", 15, "standing_banner");
		overrideAgeable("black_banner", "rotation", 15, "standing_banner");

		override("white_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("white_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("white_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("white_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("orange_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("orange_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("orange_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("orange_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("magenta_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("magenta_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("magenta_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("magenta_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("light_blue_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("light_blue_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("light_blue_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("light_blue_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("yellow_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("yellow_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("yellow_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("yellow_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("lime_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("lime_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("lime_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("lime_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("pink_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("pink_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("pink_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("pink_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("gray_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("gray_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("gray_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("gray_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("light_gray_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("light_gray_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("light_gray_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("light_gray_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("cyan_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("cyan_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("cyan_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("cyan_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("purple_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("purple_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("purple_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("purple_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("blue_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("blue_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("blue_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("blue_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("brown_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("brown_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("brown_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("brown_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("green_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("green_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("green_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("green_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("red_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("red_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("red_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("red_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("black_wall_banner", properties("facing", "north"), "wall_banner", 2);
		override("black_wall_banner", properties("facing", "south"), "wall_banner", 3);
		override("black_wall_banner", properties("facing", "east"), "wall_banner", 5);
		override("black_wall_banner", properties("facing", "west"), "wall_banner", 4);

		override("chiseled_red_sandstone", "red_sandstone", 1);
		override("cut_red_sandstone", "red_sandstone", 2);

		overrideStairs("red_sandstone_stairs");

		overrideSlab("oak_slab", "wooden_slab", 0, "double_wooden_slab");
		overrideSlab("spruce_slab", "wooden_slab", 1, "double_wooden_slab");
		overrideSlab("birch_slab", "wooden_slab", 2, "double_wooden_slab");
		overrideSlab("jungle_slab", "wooden_slab", 3, "double_wooden_slab");
		overrideSlab("acacia_slab", "wooden_slab", 4, "double_wooden_slab");
		overrideSlab("dark_oak_slab", "wooden_slab", 5, "double_wooden_slab");

		overrideSlab("stone_slab", "stone_slab", 0, "double_stone_slab");
		overrideSlab("sandstone_slab", "stone_slab", 1, "double_stone_slab");
		overrideSlab("petrified_oak_slab", "stone_slab", 2, "double_stone_slab");
		overrideSlab("cobblestone_slab", "stone_slab", 3, "double_stone_slab");
		overrideSlab("brick_slab", "stone_slab", 4, "double_stone_slab");
		overrideSlab("stone_brick_slab", "stone_slab", 5, "double_stone_slab");
		overrideSlab("nether_brick_slab", "stone_slab", 6, "double_stone_slab", 7);
		overrideSlab("quartz_slab", "stone_slab", 7, "double_stone_slab", 6);

		overrideSlab("red_sandstone_slab", "stone_slab2", 0, "double_stone_slab2");
		overrideSlab("purpur_slab", "stone_slab2", 1, "double_stone_slab2");

		override("smooth_stone", "stone"); // ?
		override("smooth_sandstone", "sandstone"); // ?
		override("smooth_quartz", "quartz_block"); // ?
		override("smooth_red_sandstone", "red_sandstone"); // ?

		overrideFenceGate("spruce_fence_gate");
		overrideFenceGate("birch_fence_gate");
		overrideFenceGate("jungle_fence_gate");
		overrideFenceGate("acacia_fence_gate");
		overrideFenceGate("dark_oak_fence_gate");

		override("spruce_fence", "fence", 1);
		override("birch_fence", "fence", 2);
		override("jungle_fence", "fence", 3);
		override("acacia_fence", "fence", 4);
		override("dark_oak_fence", "fence", 5);

		overrideDoor("spruce_door");
		overrideDoor("birch_door");
		overrideDoor("jungle_door");
		overrideDoor("acacia_door");
		overrideDoor("dark_oak_door");

		override("end_rod", properties("facing", "north"), 2);
		override("end_rod", properties("facing", "south"), 3);
		override("end_rod", properties("facing", "east"), 5);
		override("end_rod", properties("facing", "west"), 4);
		override("end_rod", properties("facing", "up"), 1);
		override("end_rod", properties("facing", "down"), 0);

		overrideAgeable("chorus_flower", "age", 5);

		override("purpur_pillar", properties("axis", "x"), "purpur_block", 6);
		override("purpur_pillar", properties("axis", "y"), "purpur_block", 2);
		override("purpur_pillar", properties("axis", "z"), "purpur_block", 10);

		overrideStairs("purpur_stairs");

		override("end_stone_bricks", "end_bricks");

		overrideAgeable("beetroots", "age", 3, "beetroot");

		override("repeating_command_block", properties("facing", "north", "confitional", "false"), 2);
		override("repeating_command_block", properties("facing", "south", "confitional", "false"), 3);
		override("repeating_command_block", properties("facing", "east", "confitional", "false"), 5);
		override("repeating_command_block", properties("facing", "west", "confitional", "false"), 4);
		override("repeating_command_block", properties("facing", "up", "confitional", "false"), 1);
		override("repeating_command_block", properties("facing", "down", "confitional", "false"), 0);
		override("repeating_command_block", properties("facing", "north", "confitional", "true"), 2 | 0x08);
		override("repeating_command_block", properties("facing", "south", "confitional", "true"), 3 | 0x08);
		override("repeating_command_block", properties("facing", "east", "confitional", "true"), 5 | 0x08);
		override("repeating_command_block", properties("facing", "west", "confitional", "true"), 4 | 0x08);
		override("repeating_command_block", properties("facing", "up", "confitional", "true"), 1 | 0x08);
		override("repeating_command_block", properties("facing", "down", "confitional", "true"), 0 | 0x08);

		override("chain_command_block", properties("facing", "north", "confitional", "false"), 2);
		override("chain_command_block", properties("facing", "south", "confitional", "false"), 3);
		override("chain_command_block", properties("facing", "east", "confitional", "false"), 5);
		override("chain_command_block", properties("facing", "west", "confitional", "false"), 4);
		override("chain_command_block", properties("facing", "up", "confitional", "false"), 1);
		override("chain_command_block", properties("facing", "down", "confitional", "false"), 0);
		override("chain_command_block", properties("facing", "north", "confitional", "true"), 2 | 0x08);
		override("chain_command_block", properties("facing", "south", "confitional", "true"), 3 | 0x08);
		override("chain_command_block", properties("facing", "east", "confitional", "true"), 5 | 0x08);
		override("chain_command_block", properties("facing", "west", "confitional", "true"), 4 | 0x08);
		override("chain_command_block", properties("facing", "up", "confitional", "true"), 1 | 0x08);
		override("chain_command_block", properties("facing", "down", "confitional", "true"), 0 | 0x08);

		overrideAgeable("frosted_ice", "age", 3);

		override("magma_block", "magma");

		override("red_nether_bricks", "red_nether_brick");

		override("bone_block", properties("axis", "x"), 4); // ?
		override("bone_block", properties("axis", "y"), 0);
		override("bone_block", properties("axis", "z"), 8); // ?

		override("structure_void", "air"); // ?

		override("observer", properties("facing", "north"), 2);
		override("observer", properties("facing", "south"), 3);
		override("observer", properties("facing", "east"), 5);
		override("observer", properties("facing", "west"), 4);
		override("observer", properties("facing", "up"), 1);
		override("observer", properties("facing", "down"), 0);

		override("shulker_box", "undyed_shulker_box");
		override("white_shulker_box", "shulker_box", 0);
		override("orange_shulker_box", "shulker_box", 1);
		override("magenta_shulker_box", "shulker_box", 2);
		override("light_blue_shulker_box", "shulker_box", 3);
		override("yellow_shulker_box", "shulker_box", 4);
		override("lime_shulker_box", "shulker_box", 5);
		override("pink_shulker_box", "shulker_box", 6);
		override("gray_shulker_box", "shulker_box", 7);
		override("light_gray_shulker_box", "shulker_box", 8);
		override("cyan_shulker_box", "shulker_box", 9);
		override("purple_shulker_box", "shulker_box", 10);
		override("blue_shulker_box", "shulker_box", 11);
		override("brown_shulker_box", "shulker_box", 12);
		override("green_shulker_box", "shulker_box", 13);
		override("red_shulker_box", "shulker_box", 14);
		override("black_shulker_box", "shulker_box", 15);

		override("white_glazed_terracotta", properties("facing", "north"), 2);
		override("white_glazed_terracotta", properties("facing", "south"), 0);
		override("white_glazed_terracotta", properties("facing", "east"), 3);
		override("white_glazed_terracotta", properties("facing", "west"), 1);

		override("orange_glazed_terracotta", properties("facing", "north"), 2);
		override("orange_glazed_terracotta", properties("facing", "south"), 0);
		override("orange_glazed_terracotta", properties("facing", "east"), 3);
		override("orange_glazed_terracotta", properties("facing", "west"), 1);

		override("magenta_glazed_terracotta", properties("facing", "north"), 2);
		override("magenta_glazed_terracotta", properties("facing", "south"), 0);
		override("magenta_glazed_terracotta", properties("facing", "east"), 3);
		override("magenta_glazed_terracotta", properties("facing", "west"), 1);

		override("light_blue_glazed_terracotta", properties("facing", "north"), 2);
		override("light_blue_glazed_terracotta", properties("facing", "south"), 0);
		override("light_blue_glazed_terracotta", properties("facing", "east"), 3);
		override("light_blue_glazed_terracotta", properties("facing", "west"), 1);

		override("yellow_glazed_terracotta", properties("facing", "north"), 2);
		override("yellow_glazed_terracotta", properties("facing", "south"), 0);
		override("yellow_glazed_terracotta", properties("facing", "east"), 3);
		override("yellow_glazed_terracotta", properties("facing", "west"), 1);

		override("lime_glazed_terracotta", properties("facing", "north"), 2);
		override("lime_glazed_terracotta", properties("facing", "south"), 0);
		override("lime_glazed_terracotta", properties("facing", "east"), 3);
		override("lime_glazed_terracotta", properties("facing", "west"), 1);

		override("pink_glazed_terracotta", properties("facing", "north"), 2);
		override("pink_glazed_terracotta", properties("facing", "south"), 0);
		override("pink_glazed_terracotta", properties("facing", "east"), 3);
		override("pink_glazed_terracotta", properties("facing", "west"), 1);

		override("gray_glazed_terracotta", properties("facing", "north"), 2);
		override("gray_glazed_terracotta", properties("facing", "south"), 0);
		override("gray_glazed_terracotta", properties("facing", "east"), 3);
		override("gray_glazed_terracotta", properties("facing", "west"), 1);

		override("light_gray_glazed_terracotta", properties("facing", "north"), "silver_glazed_terracotta", 2);
		override("light_gray_glazed_terracotta", properties("facing", "south"), "silver_glazed_terracotta", 0);
		override("light_gray_glazed_terracotta", properties("facing", "east"), "silver_glazed_terracotta", 3);
		override("light_gray_glazed_terracotta", properties("facing", "west"), "silver_glazed_terracotta", 1);

		override("cyan_glazed_terracotta", properties("facing", "north"), 2);
		override("cyan_glazed_terracotta", properties("facing", "south"), 0);
		override("cyan_glazed_terracotta", properties("facing", "east"), 3);
		override("cyan_glazed_terracotta", properties("facing", "west"), 1);

		override("purple_glazed_terracotta", properties("facing", "north"), 2);
		override("purple_glazed_terracotta", properties("facing", "south"), 0);
		override("purple_glazed_terracotta", properties("facing", "east"), 3);
		override("purple_glazed_terracotta", properties("facing", "west"), 1);

		override("blue_glazed_terracotta", properties("facing", "north"), 2);
		override("blue_glazed_terracotta", properties("facing", "south"), 0);
		override("blue_glazed_terracotta", properties("facing", "east"), 3);
		override("blue_glazed_terracotta", properties("facing", "west"), 1);

		override("brown_glazed_terracotta", properties("facing", "north"), 2);
		override("brown_glazed_terracotta", properties("facing", "south"), 0);
		override("brown_glazed_terracotta", properties("facing", "east"), 3);
		override("brown_glazed_terracotta", properties("facing", "west"), 1);

		override("green_glazed_terracotta", properties("facing", "north"), 2);
		override("green_glazed_terracotta", properties("facing", "south"), 0);
		override("green_glazed_terracotta", properties("facing", "east"), 3);
		override("green_glazed_terracotta", properties("facing", "west"), 1);

		override("red_glazed_terracotta", properties("facing", "north"), 2);
		override("red_glazed_terracotta", properties("facing", "south"), 0);
		override("red_glazed_terracotta", properties("facing", "east"), 3);
		override("red_glazed_terracotta", properties("facing", "west"), 1);

		override("black_glazed_terracotta", properties("facing", "north"), 2);
		override("black_glazed_terracotta", properties("facing", "south"), 0);
		override("black_glazed_terracotta", properties("facing", "east"), 3);
		override("black_glazed_terracotta", properties("facing", "west"), 1);

		override("white_concrete", "concrete");
		override("orange_concrete", "concrete", 1);
		override("magenta_concrete", "concrete", 2);
		override("light_blue_concrete", "concrete", 3);
		override("yellow_concrete", "concrete", 4);
		override("lime_concrete", "concrete", 5);
		override("pink_concrete", "concrete", 6);
		override("gray_concrete", "concrete", 7);
		override("light_gray_concrete", "concrete", 8);
		override("cyan_concrete", "concrete", 9);
		override("purple_concrete", "concrete", 10);
		override("blue_concrete", "concrete", 11);
		override("brown_concrete", "concrete", 12);
		override("green_concrete", "concrete", 13);
		override("red_concrete", "concrete", 14);
		override("black_concrete", "concrete", 15);

		override("white_concrete_powder", "concretepowder");
		override("orange_concrete_powder", "concretepowder", 1);
		override("magenta_concrete_powder", "concretepowder", 2);
		override("light_blue_concrete_powder", "concretepowder", 3);
		override("yellow_concrete_powder", "concretepowder", 4);
		override("lime_concrete_powder", "concretepowder", 5);
		override("pink_concrete_powder", "concretepowder", 6);
		override("gray_concrete_powder", "concretepowder", 7);
		override("light_gray_concrete_powder", "concretepowder", 8);
		override("cyan_concrete_powder", "concretepowder", 9);
		override("purple_concrete_powder", "concretepowder", 10);
		override("blue_concrete_powder", "concretepowder", 11);
		override("brown_concrete_powder", "concretepowder", 12);
		override("green_concrete_powder", "concretepowder", 13);
		override("red_concrete_powder", "concretepowder", 14);
		override("black_concrete_powder", "concretepowder", 15);

		override("kelp_plant", "kelp"); // TODO kelp and kelp_plant age

		// TODO turtle_eggs

		override("tube_coral_block", "coral_block");
		override("brain_coral_block", "coral_block", 1);
		override("bubble_coral_block", "coral_block", 2);
		override("fire_coral_block", "coral_block", 3);
		override("horn_coral_block", "coral_block", 4);
		override("dead_tube_coral_block", "coral_block", 5);
		override("dead_brain_coral_block", "coral_block", 6);
		override("dead_bubble_coral_block", "coral_block", 7);
		override("dead_fire_coral_block", "coral_block", 8);
		override("dead_horn_coral_block", "coral_block", 9);

		override("tube_coral", "coral");
		override("brain_coral", "coral", 1);
		override("bubble_coral", "coral", 2);
		override("fire_coral", "coral", 3);
		override("horn_coral", "coral", 4);

		override("tube_coral_fan", "coral_fan");
		override("brain_coral_fan", "coral_fan", 1);
		override("bubble_coral_fan", "coral_fan", 2);
		override("fire_coral_fan", "coral_fan", 3);
		override("horn_coral_fan", "coral_fan", 4);

		override("dead_tube_coral_fan", "coral_fan_dead");
		override("dead_brain_coral_fan", "coral_fan_dead", 1);
		override("dead_bubble_coral_fan", "coral_fan_dead", 2);
		override("dead_fire_coral_fan", "coral_fan_dead", 3);
		override("dead_horn_coral_fan", "coral_fan_dead", 4);

		override("tube_coral_wall_fan", properties("facing", "north"), "coral_fan_hang", 0);
		override("tube_coral_wall_fan", properties("facing", "south"), "coral_fan_hang", 4);
		override("tube_coral_wall_fan", properties("facing", "east"), "coral_fan_hang", 8);
		override("tube_coral_wall_fan", properties("facing", "west"), "coral_fan_hang", 12);

		override("brain_coral_wall_fan", properties("facing", "north"), "coral_fan_hang", 1);
		override("brain_coral_wall_fan", properties("facing", "south"), "coral_fan_hang", 5);
		override("brain_coral_wall_fan", properties("facing", "east"), "coral_fan_hang", 9);
		override("brain_coral_wall_fan", properties("facing", "west"), "coral_fan_hang", 13);

		override("bubble_coral_wall_fan", properties("facing", "north"), "coral_fan_hang2", 0);
		override("bubble_coral_wall_fan", properties("facing", "south"), "coral_fan_hang2", 4);
		override("bubble_coral_wall_fan", properties("facing", "east"), "coral_fan_hang2", 8);
		override("bubble_coral_wall_fan", properties("facing", "west"), "coral_fan_hang2", 12);

		override("fire_coral_wall_fan", properties("facing", "north"), "coral_fan_hang2", 1);
		override("fire_coral_wall_fan", properties("facing", "south"), "coral_fan_hang2", 5);
		override("fire_coral_wall_fan", properties("facing", "east"), "coral_fan_hang2", 9);
		override("fire_coral_wall_fan", properties("facing", "west"), "coral_fan_hang2", 13);

		override("horn_coral_wall_fan", properties("facing", "north"), "coral_fan_hang3", 0);
		override("horn_coral_wall_fan", properties("facing", "south"), "coral_fan_hang3", 4);
		override("horn_coral_wall_fan", properties("facing", "east"), "coral_fan_hang3", 8);
		override("horn_coral_wall_fan", properties("facing", "west"), "coral_fan_hang3", 12);

		override("dead_tube_coral_wall_fan", properties("facing", "north"), "coral_fan_hang", 2);
		override("dead_tube_coral_wall_fan", properties("facing", "south"), "coral_fan_hang", 6);
		override("dead_tube_coral_wall_fan", properties("facing", "east"), "coral_fan_hang", 10);
		override("dead_tube_coral_wall_fan", properties("facing", "west"), "coral_fan_hang", 14);

		override("dead_brain_coral_wall_fan", properties("facing", "north"), "coral_fan_hang", 3);
		override("dead_brain_coral_wall_fan", properties("facing", "south"), "coral_fan_hang", 7);
		override("dead_brain_coral_wall_fan", properties("facing", "east"), "coral_fan_hang", 11);
		override("dead_brain_coral_wall_fan", properties("facing", "west"), "coral_fan_hang", 15);

		override("dead_bubble_coral_wall_fan", properties("facing", "north"), "coral_fan_hang2", 2);
		override("dead_bubble_coral_wall_fan", properties("facing", "south"), "coral_fan_hang2", 6);
		override("dead_bubble_coral_wall_fan", properties("facing", "east"), "coral_fan_hang2", 10);
		override("dead_bubble_coral_wall_fan", properties("facing", "west"), "coral_fan_hang2", 14);

		override("dead_fire_coral_wall_fan", properties("facing", "north"), "coral_fan_hang2", 3);
		override("dead_fire_coral_wall_fan", properties("facing", "south"), "coral_fan_hang2", 7);
		override("dead_fire_coral_wall_fan", properties("facing", "east"), "coral_fan_hang2", 11);
		override("dead_fire_coral_wall_fan", properties("facing", "west"), "coral_fan_hang2", 15);

		override("dead_horn_coral_wall_fan", properties("facing", "north"), "coral_fan_hang3", 2);
		override("dead_horn_coral_wall_fan", properties("facing", "south"), "coral_fan_hang3", 6);
		override("dead_horn_coral_wall_fan", properties("facing", "east"), "coral_fan_hang3", 10);
		override("dead_horn_coral_wall_fan", properties("facing", "west"), "coral_fan_hang3", 14);

		// Replace missing death variants of coral with death variants of death fan
		// coral
		override("dead_tube_coral", "coral_fan_dead");
		override("dead_brain_coral", "coral_fan_dead", 1);
		override("dead_bubble_coral", "coral_fan_dead", 2);
		override("dead_fire_coral", "coral_fan_dead", 3);
		override("dead_horn_coral", "coral_fan_dead", 4);

		overrideAgeable("sea_pickle", properties("waterlogged", "true"), "pickles", 1, 4, "sea_pickle", -1);
		overrideAgeable("sea_pickle", properties("waterlogged", "false"), "pickles", 1, 4, "sea_pickle", 3);

		override("air", "air");
		override("void_air", "air");
		override("cave_air", "air");

		override("structure_block", properties("mode", "save"), 2);
		override("structure_block", properties("mode", "load"), 3);
		override("structure_block", properties("mode", "corner"), 4);
		override("structure_block", properties("mode", "data"), 1);

		Platform.log("§f[Flattening] §aLoaded §7" + PC_TO_PE_OVERRIDE.size() + "§a block translates from pc to pe!");
	}

	private BlockEntry translateToPE(int stateID) {
		return translateToPE(flatteningData.fromStateID(stateID));
	}

	private BlockEntry translateToPE(FlattedBlockState blockState) {
		try {
			if (!PC_TO_PE_OVERRIDE.containsKey(blockState)) {
				FlattedBlockState defaultState = blockState.block.defaultState;
				if (!PC_TO_PE_OVERRIDE.containsKey(defaultState)) {
					return new BlockEntry(peNameToId.get(blockState.block.name), 0,
							blockState.properties.containsKey("waterlogged"),
							blockState.properties.containsKey("waterlogged")
									? blockState.properties.get("waterlogged").equals("true")
									: false); // check
				}
				return PC_TO_PE_OVERRIDE.get(defaultState);
			}
			return PC_TO_PE_OVERRIDE.get(blockState);
		} catch (Exception e) {
			Platform.log("§cAn error occurred while overriding " + blockState.block.name);
			return new BlockEntry(1); // Stone
		}
	}

	private FlattedBlockState translateToPC(int peId, int damage) {
		try {
			if (!PE_TO_PC_OVERRIDE.containsKey(peId)) {
				String name = peIdToName.get(peId);
				return flatteningData.fromNameDefault(name);
			}
			if (!PE_TO_PC_OVERRIDE.get(peId).containsKey(damage)) {
				String name = peIdToName.get(peId);
				return flatteningData.fromNameDefault(name);
			}
			return PE_TO_PC_OVERRIDE.get(peId).get(damage);
		} catch (Exception e) {
			return null; // Nothing
		}
	}

	private void override(String pcName, String peName) {
		override(pcName, peName, 0);
	}

	private void override(String pcName, String peName, int peData) {
		try {
			FlattedBlockState blockState = flatteningData.fromNameDefault("minecraft:" + pcName);
			int peId = peNameToId.get("minecraft:" + peName);
			override(blockState, new BlockEntry(peId, peData));
		} catch (Exception e) {
			Platform.log("§cOverriding " + pcName + " failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void override(String pcName, Map<String, String> properties, String peName) {
		override(pcName, properties, peName, 0);
	}

	private void override(String pcName, Map<String, String> properties, String peName, int peData) {
		try {
			List<FlattedBlockState> blockStates = flatteningData.fromNameProperties("minecraft:" + pcName, properties);
			int peId = peNameToId.get("minecraft:" + peName);
			for (FlattedBlockState blockState : blockStates) {
				override(blockState,
						new BlockEntry(peId, peData, blockState.properties.containsKey("waterlogged"),
								blockState.properties.containsKey("waterlogged")
										? blockState.properties.get("waterlogged").equals("true")
										: false)); // Check
			}
		} catch (Exception e) {
			Platform.log("§cOverriding " + pcName + " failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void override(String pcName, Map<String, String> properties, int peData) {
		override(pcName, properties, pcName, peData);
	}

	private void override(FlattedBlockState blockState, BlockEntry entry) {
		if (!PC_TO_PE_OVERRIDE.containsKey(blockState)) {
			PC_TO_PE_OVERRIDE.put(blockState, entry);
		}
		if (!PE_TO_PC_OVERRIDE.containsKey(entry.getId())) {
			PE_TO_PC_OVERRIDE.put(entry.getId(), new HashMap<>());
		}
		if (!PE_TO_PC_OVERRIDE.get(entry.getId()).containsKey(entry.getData())) {
			PE_TO_PC_OVERRIDE.get(entry.getId()).put(entry.getData(), blockState);
		}
	}

	private void overrideStairs(String pcName) {
		overrideStairs(pcName, pcName);
	}

	private void overrideStairs(String pcName, String peName) {
		override(pcName, properties("facing", "north", "half", "bottom"), peName, 3);
		override(pcName, properties("facing", "south", "half", "bottom"), peName, 2);
		override(pcName, properties("facing", "east", "half", "bottom"), peName, 0);
		override(pcName, properties("facing", "west", "half", "bottom"), peName, 1);
		override(pcName, properties("facing", "north", "half", "top"), peName, 3 | 0x04);
		override(pcName, properties("facing", "south", "half", "top"), peName, 2 | 0x04);
		override(pcName, properties("facing", "east", "half", "top"), peName, 0 | 0x04);
		override(pcName, properties("facing", "west", "half", "top"), peName, 1 | 0x04);
	}

	private void overrideDoor(String pcName) {
		overrideDoor(pcName, pcName);
	}

	private void overrideDoor(String pcName, String peName) {
		// Lower half
		override(pcName, properties("facing", "north", "half", "lower", "open", "false"), peName, 3);
		override(pcName, properties("facing", "south", "half", "lower", "open", "false"), peName, 1);
		override(pcName, properties("facing", "east", "half", "lower", "open", "false"), peName, 0);
		override(pcName, properties("facing", "west", "half", "lower", "open", "false"), peName, 2);
		override(pcName, properties("facing", "north", "half", "lower", "open", "true"), peName, 3 | 0x04);
		override(pcName, properties("facing", "south", "half", "lower", "open", "true"), peName, 1 | 0x04);
		override(pcName, properties("facing", "east", "half", "lower", "open", "true"), peName, 0 | 0x04);
		override(pcName, properties("facing", "west", "half", "lower", "open", "true"), peName, 2 | 0x04);
		// Upper half
		override(pcName, properties("half", "upper", "hinge", "left", "powered", "false"), peName, 0 | 0x08);
		override(pcName, properties("half", "upper", "hinge", "right", "powered", "false"), peName, 1 | 0x08);
		override(pcName, properties("half", "upper", "hinge", "left", "powered", "true"), peName, 0 | 0x02 | 0x08);
		override(pcName, properties("half", "upper", "hinge", "right", "powered", "true"), peName, 1 | 0x02 | 0x08);
	}

	private void overrideBed(String pcName) {
		overrideBed(pcName, pcName);
	}

	private void overrideBed(String pcName, String peName) {
		override(pcName, properties("facing", "north", "occupied", "false", "part", "foot"), peName, 2);
		override(pcName, properties("facing", "south", "occupied", "false", "part", "foot"), peName, 0);
		override(pcName, properties("facing", "east", "occupied", "false", "part", "foot"), peName, 3);
		override(pcName, properties("facing", "west", "occupied", "false", "part", "foot"), peName, 1);
		override(pcName, properties("facing", "north", "occupied", "false", "part", "head"), peName, 2 | 0x08);
		override(pcName, properties("facing", "south", "occupied", "false", "part", "head"), peName, 0 | 0x08);
		override(pcName, properties("facing", "east", "occupied", "false", "part", "head"), peName, 3 | 0x08);
		override(pcName, properties("facing", "west", "occupied", "false", "part", "head"), peName, 1 | 0x08);
		override(pcName, properties("facing", "north", "occupied", "true", "part", "foot"), peName, 2 | 0x04);
		override(pcName, properties("facing", "south", "occupied", "true", "part", "foot"), peName, 0 | 0x04);
		override(pcName, properties("facing", "east", "occupied", "true", "part", "foot"), peName, 3 | 0x04);
		override(pcName, properties("facing", "west", "occupied", "true", "part", "foot"), peName, 1 | 0x04);
		override(pcName, properties("facing", "north", "occupied", "true", "part", "head"), peName, 2 | 0x08 | 0x04);
		override(pcName, properties("facing", "south", "occupied", "true", "part", "head"), peName, 0 | 0x08 | 0x04);
		override(pcName, properties("facing", "east", "occupied", "true", "part", "head"), peName, 3 | 0x08 | 0x04);
		override(pcName, properties("facing", "west", "occupied", "true", "part", "head"), peName, 1 | 0x08 | 0x04);
	}

	private void overrideFenceGate(String pcName) {
		overrideFenceGate(pcName, pcName);
	}

	private void overrideFenceGate(String pcName, String peName) {
		override(pcName, properties("facing", "north", "open", "false"), peName, 2);
		override(pcName, properties("facing", "south", "open", "false"), peName, 0);
		override(pcName, properties("facing", "east", "open", "false"), peName, 3);
		override(pcName, properties("facing", "west", "open", "false"), peName, 1);
		override(pcName, properties("facing", "north", "open", "true"), peName, 2 | 0x04);
		override(pcName, properties("facing", "south", "open", "true"), peName, 0 | 0x04);
		override(pcName, properties("facing", "east", "open", "true"), peName, 3 | 0x04);
		override(pcName, properties("facing", "west", "open", "true"), peName, 1 | 0x04);
	}

	private void overrideTrapdoor(String pcName) {
		overrideTrapdoor(pcName, pcName);
	}

	private void overrideTrapdoor(String pcName, String peName) {
		override(pcName, properties("facing", "north", "open", "false", "half", "bottom"), peName, 1);
		override(pcName, properties("facing", "south", "open", "false", "half", "bottom"), peName, 0);
		override(pcName, properties("facing", "east", "open", "false", "half", "bottom"), peName, 2);
		override(pcName, properties("facing", "west", "open", "false", "half", "bottom"), peName, 3);
		override(pcName, properties("facing", "north", "open", "false", "half", "top"), peName, 1 | 0x08);
		override(pcName, properties("facing", "south", "open", "false", "half", "top"), peName, 0 | 0x08);
		override(pcName, properties("facing", "east", "open", "false", "half", "top"), peName, 2 | 0x08);
		override(pcName, properties("facing", "west", "open", "false", "half", "top"), peName, 3 | 0x08);
		override(pcName, properties("facing", "north", "open", "true", "half", "bottom"), peName, 1 | 0x04);
		override(pcName, properties("facing", "south", "open", "true", "half", "bottom"), peName, 0 | 0x04);
		override(pcName, properties("facing", "east", "open", "true", "half", "bottom"), peName, 2 | 0x04);
		override(pcName, properties("facing", "west", "open", "true", "half", "bottom"), peName, 3 | 0x04);
		override(pcName, properties("facing", "north", "open", "true", "half", "top"), peName, 1 | 0x08 | 0x04);
		override(pcName, properties("facing", "south", "open", "true", "half", "top"), peName, 0 | 0x08 | 0x04);
		override(pcName, properties("facing", "east", "open", "true", "half", "top"), peName, 2 | 0x08 | 0x04);
		override(pcName, properties("facing", "west", "open", "true", "half", "top"), peName, 3 | 0x08 | 0x04);
	}

	private void overrideButton(String pcName) {
		overrideButton(pcName, pcName);
	}

	private void overrideButton(String pcName, String peName) {
		override(pcName, properties("face", "ceiling", "powered", "false"), peName, 5);
		override(pcName, properties("face", "floor", "powered", "false"), peName, 0);
		override(pcName, properties("face", "wall", "facing", "north", "powered", "false"), peName, 4);
		override(pcName, properties("face", "wall", "facing", "west", "powered", "false"), peName, 2);
		override(pcName, properties("face", "wall", "facing", "south", "powered", "false"), peName, 3);
		override(pcName, properties("face", "wall", "facing", "east", "powered", "false"), peName, 1);
		override(pcName, properties("face", "ceiling", "powered", "true"), peName, 5 | 0x08);
		override(pcName, properties("face", "floor", "powered", "true"), peName, 0 | 0x08);
		override(pcName, properties("face", "wall", "facing", "north", "powered", "true"), peName, 4 | 0x08);
		override(pcName, properties("face", "wall", "facing", "west", "powered", "true"), peName, 2 | 0x08);
		override(pcName, properties("face", "wall", "facing", "south", "powered", "true"), peName, 3 | 0x08);
		override(pcName, properties("face", "wall", "facing", "east", "powered", "true"), peName, 1 | 0x08);
	}

	private void overrideSlab(String pcName, String peName, int peData, String peDoubleName) {
		overrideSlab(pcName, peName, peData, peDoubleName, peData);
	}

	private void overrideSlab(String pcName, String peName, int peData, String peDoubleName, int peDoubleData) {
		override(pcName, properties("type", "top"), peName, peData + 8);
		override(pcName, properties("type", "bottom"), peName, peData);
		override(pcName, properties("type", "double"), peDoubleName, peDoubleData);
	}

	private void overrideAgeable(String pcName, String property, int end) {
		overrideAgeable(pcName, null, property, 0, end, pcName, 0);
	}

	private void overrideAgeable(String pcName, String property, int end, String peName) {
		overrideAgeable(pcName, null, property, 0, end, peName, 0);
	}

	private void overrideAgeable(String pcName, String property, int end, String peName, int peOffset) {
		overrideAgeable(pcName, null, property, 0, end, peName, peOffset);
	}

	private void overrideAgeable(String pcName, String property, int end, String peName, Counter func) {
		overrideAgeable(pcName, null, property, 0, end, peName, func);
	}

	private void overrideAgeable(String pcName, Map<String, String> properties, String property, int end,
			String peName) {
		overrideAgeable(pcName, properties, property, 0, end, peName, 0);
	}

	private void overrideAgeable(String pcName, Map<String, String> properties, String property, int end, String peName,
			int peOffset) {
		overrideAgeable(pcName, properties, property, 0, end, peName, peOffset);
	}

	private void overrideAgeable(String pcName, Map<String, String> properties, String property, int end, String peName,
			Counter func) {
		overrideAgeable(pcName, properties, property, 0, end, peName, func);
	}

	private void overrideAgeable(String pcName, String property, int start, int end, String peName) {
		overrideAgeable(pcName, null, property, start, end, peName, 0);
	}

	private void overrideAgeable(String pcName, Map<String, String> properties, String property, int start, int end,
			String peName) {
		overrideAgeable(pcName, properties, property, start, end, peName, 0);
	}

	private void overrideAgeable(String pcName, String property, int start, int end, String peName, int peOffset) {
		overrideAgeable(pcName, null, property, start, end, peName, peOffset);
	}

	private void overrideAgeable(String pcName, String property, int start, int end, String peName, Counter func) {
		overrideAgeable(pcName, null, property, start, end, peName, func);
	}

	private void overrideAgeable(String pcName, Map<String, String> properties, String property, int start, int end,
			String peName, int peOffset) {
		overrideAgeable(pcName, properties, property, start, end, peName, x -> peOffset + x);
	}

	private void overrideAgeable(String pcName, Map<String, String> properties, String property, int start, int end,
			String peName, Counter func) {
		if (properties == null) {
			properties = new HashMap<>();
		}
		for (int k = start; k <= end; k++) {
			Map<String, String> itemProperties = new HashMap<>(properties);
			itemProperties.put(property, Integer.toString(k));
			override(pcName, itemProperties, peName, func.count(k));
		}
	}

	private Map<String, String> properties(String... args) {
		Map<String, String> map = new HashMap<>();
		String last = null;
		for (String string : args) {
			if (last == null) {
				last = string;
			} else {
				map.put(last, string);
				last = null;
			}
		}
		return map;
	}

	public interface Counter {
		public int count(int x);
	}

	@Override
	public BlockEntry blockPcToPe(int id) {
		return translateToPE(id);
	}

	@Override
	public BlockEntry blockPcToPe(int id, int data) {
		return translateToPE(id); // data is not supported since 1.13
	}

	@Override
	public BlockEntry blockPcToPe(FlattedBlockState block) {
		return translateToPE(block);
	}

	@Override
	public FlattedBlockState blockPeToPc(int id) {
		return translateToPC(id, 0);
	}

	@Override
	public FlattedBlockState blockPeToPc(int id, int data) {
		return translateToPC(id, data);
	}

	@Override
	public FlattedBlockState blockPeToPc(BlockEntry block) {
		return translateToPC(block.getId(), block.getData());
	}

}
