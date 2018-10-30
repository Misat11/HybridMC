package misat11.hybrid.blockitems.flattening;

import java.util.HashMap;
import java.util.Map;

import misat11.hybrid.Platform;
import misat11.hybrid.blockitems.IItemTranslator;
import misat11.hybrid.blockitems.ItemEntry;

public class FlatteningItemTranslator implements IItemTranslator<FlattedItem>{
	private final Map<FlattedItem, ItemEntry> PC_TO_PE_OVERRIDE = new HashMap<>();
	private final Map<Integer, Map<Integer, FlattedItem>> PE_TO_PC_OVERRIDE = new HashMap<>();

	private final IFlatteningItemData flatteningData;
	
	public FlatteningItemTranslator(IFlatteningItemData flatteningData) {
		this.flatteningData = flatteningData;

		Platform.log("§f[Flattening] §aLoading item translates...");
		
		override("air", 0);
		override("stone", 1);
		override("granite", 1, 1);
		override("polished_granite", 1, 2);
		override("diorite", 1, 3);
		override("polished_diorite", 1, 4);
		override("andesite", 1, 5);
		override("polished_andesite", 1, 6);
		override("grass_block", 2);
		override("dirt", 3);
		override("coarse_dirt", 3, 1);
		override("podzol", 243);
		override("cobblestone", 4);
		override("oak_planks", 5);
		override("spruce_planks", 5, 1);
		override("birch_planks", 5, 2);
		override("jungle_planks", 5, 3);
		override("acacia_planks", 5, 4);
		override("dark_oak_planks", 5, 5);
		override("oak_sapling", 6);
		override("spruce_sapling", 6, 1);
		override("birch_sapling", 6, 2);
		override("jungle_sapling", 6, 3);
		override("acacia_sapling", 6, 4);
		override("dark_oak_sapling", 6, 5);
		override("bedrock", 7);
		override("sand", 12);
		override("red_sand", 12, 1);
		override("gravel", 13);
		override("gold_ore", 14);
		override("iron_ore", 15);
		override("coal_ore", 16);
		override("oak_log", 17);
		override("spruce_log", 17, 1);
		override("birch_log", 17, 2);
		override("jungle_log", 17, 3);
		override("acacia_log", 162);
		override("dark_oak_log", 162, 1);
		override("stripped_oak_log", 255 - 265);
		override("stripped_spruce_log", 255 - 260);
		override("stripped_birch_log", 255 - 261);
		override("stripped_jungle_log", 255 - 262);
		override("stripped_acacia_log", 255 - 263);
		override("stripped_dark_oak_log", 255 - 264);
		override("stripped_oak_wood", 255 - 265, 3);
		override("stripped_spruce_wood", 255 - 260, 3);
		override("stripped_birch_wood", 255 - 261, 3);
		override("stripped_jungle_wood", 255 - 262, 3);
		override("stripped_acacia_wood", 255 - 263, 3);
		override("stripped_dark_oak_wood", 255 - 264, 3);
		override("oak_wood", 17, 0 | 0x0c);
		override("spruce_wood", 17, 1 | 0x0c);
		override("birch_wood", 17, 2 | 0x0c);
		override("jungle_wood", 17, 3 | 0x0c);
		override("acacia_wood", 162, 0 | 0x0c);
		override("dark_oak_wood", 162, 1 | 0x0c);
		override("oak_leaves", 18);
		override("spruce_leaves", 18, 1);
		override("birch_leaves", 18, 2);
		override("jungle_leaves", 18, 3);
		override("acacia_leaves", 161);
		override("dark_oak_leaves", 161, 1);
		override("sponge", 19);
		override("wet_sponge", 19, 1);
		override("glass", 20);
		override("lapis_ore", 21);
		override("lapis_block", 22);
		override("dispenser", 23);
		override("sandstone", 24);
		override("chiseled_sandstone", 24, 1);
		override("cut_sandstone", 24, 2);
		override("note_block", 25);
		override("powered_rail", 27);
		override("detector_rail", 28);
		override("sticky_piston", 29);
		override("cobweb", 30);
		override("grass", 31);
		override("fern", 31, 1);
		override("dead_bush", 32);
		override("seagrass", 255 - 385);
		override("sea_pickle", 411);
		override("piston", 33);
		override("white_wool", 35);
		override("orange_wool", 35, 1);
		override("magenta_wool", 35, 2);
		override("light_blue_wool", 35, 3);
		override("yellow_wool", 35, 4);
		override("lime_wool", 35, 5);
		override("pink_wool", 35, 6);
		override("gray_wool", 35, 7);
		override("light_gray_wool", 35, 8);
		override("cyan_wool", 35, 9);
		override("purple_wool", 35, 10);
		override("blue_wool", 35, 11);
		override("brown_wool", 35, 12);
		override("green_wool", 35, 13);
		override("red_wool", 35, 14);
		override("black_wool", 35, 15);
		override("dandelion", 37);
		override("poppy", 38);
		override("blue_orchid", 38, 1);
		override("allium", 38, 2);
		override("azure_bluet", 38, 3);
		override("red_tulip", 38, 4);
		override("orange_tulip", 38, 5);
		override("white_tulip", 38, 6);
		override("pink_tulip", 38, 7);
		override("oxeye_daisy", 38, 8);
		override("brown_mushroom", 39);
		override("red_mushroom", 40);
		override("gold_block", 41);
		override("iron_block", 42);
		override("oak_slab", 158);
		override("spruce_slab", 158, 1);
		override("birch_slab", 158, 2);
		override("jungle_slab", 158, 3);
		override("acacia_slab", 158, 4);
		override("dark_oak_slab", 158, 5);
		override("stone_slab", 44);
		override("sandstone_slab", 44, 1);
		override("petrified_oak_slab", 44, 2);
		override("cobblestone_slab", 44, 3);
		override("brick_slab", 44, 4);
		override("stone_brick_slab", 44, 5);
		override("nether_brick_slab", 44, 6);
		override("quartz_slab", 44, 7);
		override("red_sandstone_slab", 182);
		override("purpur_slab", 182, 1);
		override("prismarine_slab", 182, 2);
		override("prismarine_brick_slab", 182, 3);
		override("dark_prismarine_slab", 182, 4);
		override("smooth_quartz", 155); // ?
		override("smooth_red_sandstone", 179); // ?
		override("smooth_sandstone", 24); // ?
		override("smooth_stone", 1); // ?
		override("bricks", 45);
		override("tnt", 46);
		override("bookshelf", 47);
		override("mossy_cobblestone", 48);
		override("obsidian", 49);
		override("torch", 50);
		override("end_rod", 208);
		override("chorus_plant", 240);
		override("chorus_flower", 200);
		override("purpur_block", 201);
		override("purpur_pillar", 201, 1);
		override("purpur_stairs", 203);
		override("spawner", 52);
		override("oak_stairs", 53);
		override("chest", 54);
		override("diamond_ore", 56);
		override("diamond_block", 57);
		override("crafting_table", 58);
		override("farmland", 60);
		override("furnace", 61);
		override("ladder", 65);
		override("rail", 66);
		override("cobblestone_stairs", 67);
		override("lever", 69);
		override("stone_pressure_plate", 70);
		override("oak_pressure_plate", 72);
		override("spruce_pressure_plate", 255 - 409);
		override("birch_pressure_plate", 255 - 406);
		override("jungle_pressure_plate", 255 - 408);
		override("acacia_pressure_plate", 255 - 405);
		override("dark_oak_pressure_plate", 255 - 407);
		override("redstone_ore", 73);
		override("redstone_torch", 76);
		override("stone_button", 77);
		override("snow", 78);
		override("ice", 79);
		override("snow_block", 80);
		override("cactus", 81);
		override("clay", 82);
		override("jukebox", 84);
		override("oak_fence", 85);
		override("spruce_fence", 85, 1);
		override("birch_fence", 85, 2);
		override("jungle_fence", 85, 3);
		override("acacia_fence", 85, 4);
		override("dark_oak_fence", 85, 5);
		override("pumpkin", 86);
		override("carved_pumpkin", 255 - 410);
		override("netherrack", 87);
		override("soul_sand", 88);
		override("glowstone", 89);
		override("jack_o_lantern", 91);
		override("oak_trapdoor", 96);
		override("spruce_trapdoor", 255 - 404);
		override("birch_trapdoor", 255 - 401);
		override("jungle_trapdoor", 255 - 403);
		override("acacia_trapdoor", 255 - 400);
		override("dark_oak_trapdoor", 255 - 402);
		override("infested_stone", 97);
		override("infested_cobblestone", 97, 1);
		override("infested_stone_bricks", 97, 2);
		override("infested_mossy_stone_bricks", 97, 3);
		override("infested_cracked_stone_bricks", 97, 4);
		override("infested_chiseled_stone_bricks", 97, 5);
		override("stone_bricks", 98);
		override("mossy_stone_bricks", 98, 1);
		override("cracked_stone_bricks", 98, 2);
		override("chiseled_stone_bricks", 98, 3);
		override("brown_mushroom_block", 99);
		override("red_mushroom_block", 100);
		override("mushroom_stem", 100, 10);
		override("iron_bars", 101);
		override("glass_pane", 102);
		override("melon", 103);
		override("vine", 106);
		override("oak_fence_gate", 107);
		override("spruce_fence_gate", 183);
		override("birch_fence_gate", 184);
		override("jungle_fence_gate", 185);
		override("acacia_fence_gate", 187);
		override("dark_oak_fence_gate", 186);
		override("brick_stairs", 108);
		override("stone_brick_stairs", 109);
		override("mycelium", 110);
		override("lily_pad", 111);
		override("nether_bricks", 112);
		override("nether_brick_fence", 113);
		override("nether_brick_stairs", 114);
		override("enchanting_table", 116);
		override("end_portal_frame", 120);
		override("end_stone", 121);
		override("end_stone_bricks", 206);
		override("dragon_egg", 122);
		override("redstone_lamp", 123);
		override("sandstone_stairs", 128);
		override("emerald_ore", 129);
		override("ender_chest", 130);
		override("tripwire_hook", 131);
		override("emerald_block", 133);
		override("spruce_stairs", 134);
		override("birch_stairs", 135);
		override("jungle_stairs", 136);
		override("command_block", 137);
		override("beacon", 138);
		override("cobblestone_wall", 139);
		override("mossy_cobblestone_wall", 139, 1);
		override("oak_button", 143);
		override("spruce_button", 255 - 399);
		override("birch_button", 255 - 396);
		override("jungle_button", 255 - 398);
		override("acacia_button", 255 - 395);
		override("dark_oak_button", 255 - 397);
		override("anvil", 145);
		override("chipped_anvil", 145, 1);
		override("damaged_anvil", 145, 2);
		override("trapped_chest", 146);
		override("light_weighted_pressure_plate", 147);
		override("heavy_weighted_pressure_plate", 148);
		override("daylight_detector", 151);
		override("redstone_block", 152);
		override("nether_quartz_ore", 153);
		override("hopper", 154);
		override("chiseled_quartz_block", 155, 1);
		override("quartz_block", 155);
		override("quartz_pillar", 155, 2);
		override("quartz_stairs", 156);
		override("activator_rail", 126);
		override("dropper", 125);
		override("white_terracotta", 159);
		override("orange_terracotta", 159, 1);
		override("magenta_terracotta", 159, 2);
		override("light_blue_terracotta", 159, 3);
		override("yellow_terracotta", 159, 4);
		override("lime_terracotta", 159, 5);
		override("pink_terracotta", 159, 6);
		override("gray_terracotta", 159, 7);
		override("light_gray_terracotta", 159, 8);
		override("cyan_terracotta", 159, 9);
		override("purple_terracotta", 159, 10);
		override("blue_terracotta", 159, 11);
		override("brown_terracotta", 159, 12);
		override("green_terracotta", 159, 13);
		override("red_terracotta", 159, 14);
		override("black_terracotta", 159, 15);
		override("barrier", 255 - 416);
		override("iron_trapdoor", 167);
		override("hay_block", 170);
		override("white_carpet", 171);
		override("orange_carpet", 171, 1);
		override("magenta_carpet", 171, 2);
		override("light_blue_carpet", 171, 3);
		override("yellow_carpet", 171, 4);
		override("lime_carpet", 171, 5);
		override("pink_carpet", 171, 6);
		override("gray_carpet", 171, 7);
		override("light_gray_carpet", 171, 8);
		override("cyan_carpet", 171, 9);
		override("purple_carpet", 171, 10);
		override("blue_carpet", 171, 11);
		override("brown_carpet", 171, 12);
		override("green_carpet", 171, 13);
		override("red_carpet", 171, 14);
		override("black_carpet", 171, 15);
		override("terracotta", 172);
		override("coal_block", 173);
		override("packed_ice", 174);
		override("acacia_stairs", 163);
		override("dark_oak_stairs", 164);
		override("slime_block", 165);
		override("grass_path", 198);
		override("sunflower", 175);
		override("lilac", 175, 1);
		override("rose_bush", 175, 4);
		override("peony", 175, 5);
		override("tall_grass", 175, 2);
		override("large_fern", 175, 3);
		override("white_stained_glass", 95);
		override("orange_stained_glass", 95, 1);
		override("magenta_stained_glass", 95, 2);
		override("light_blue_stained_glass", 95, 3);
		override("yellow_stained_glass", 95, 4);
		override("lime_stained_glass", 95, 5);
		override("pink_stained_glass", 95, 6);
		override("gray_stained_glass", 95, 7);
		override("light_gray_stained_glass", 95, 8);
		override("cyan_stained_glass", 95, 9);
		override("purple_stained_glass", 95, 10);
		override("blue_stained_glass", 95, 11);
		override("brown_stained_glass", 95, 12);
		override("green_stained_glass", 95, 13);
		override("red_stained_glass", 95, 14);
		override("black_stained_glass", 95, 15);
		override("white_stained_glass_pane", 160);
		override("orange_stained_glass_pane", 160, 1);
		override("magenta_stained_glass_pane", 160, 2);
		override("light_blue_stained_glass_pane", 160, 3);
		override("yellow_stained_glass_pane", 160, 4);
		override("lime_stained_glass_pane", 160, 5);
		override("pink_stained_glass_pane", 160, 6);
		override("gray_stained_glass_pane", 160, 7);
		override("light_gray_stained_glass_pane", 160, 8);
		override("cyan_stained_glass_pane", 160, 9);
		override("purple_stained_glass_pane", 160, 10);
		override("blue_stained_glass_pane", 160, 11);
		override("brown_stained_glass_pane", 160, 12);
		override("green_stained_glass_pane", 160, 13);
		override("red_stained_glass_pane", 160, 14);
		override("black_stained_glass_pane", 160, 15);
		override("prismarine", 168);
		override("prismarine_bricks", 168, 2);
		override("dark_prismarine", 168, 1);
		override("prismarine_stairs", 255 - 257);
		override("prismarine_brick_stairs", 255 - 259);
		override("dark_prismarine_stairs", 255 - 258);
		override("sea_lantern", 169);
		override("red_sandstone", 179);
		override("chiseled_red_sandstone", 179, 1);
		override("cut_red_sandstone", 179, 2);
		override("red_sandstone_stairs", 180);
		override("repeating_command_block", 188);
		override("chain_command_block", 189);
		override("magma_block", 213);
		override("nether_wart_block", 214);
		override("red_nether_bricks", 215);
		override("bone_block", 216);
		override("structure_void", 217);
		override("observer", 251);
		override("shulker_box", 205);
		override("white_shulker_box", 218);
		override("orange_shulker_box", 218, 1);
		override("magenta_shulker_box", 218, 2);
		override("light_blue_shulker_box", 218, 3);
		override("yellow_shulker_box", 218, 4);
		override("lime_shulker_box", 218, 5);
		override("pink_shulker_box", 218, 6);
		override("gray_shulker_box", 218, 7);
		override("light_gray_shulker_box", 218, 8);
		override("cyan_shulker_box", 218, 9);
		override("purple_shulker_box", 218, 10);
		override("blue_shulker_box", 218, 11);
		override("brown_shulker_box", 218, 12);
		override("green_shulker_box", 218, 13);
		override("red_shulker_box", 218, 14);
		override("black_shulker_box", 218, 15);
		override("white_glazed_terracotta", 220);
		override("orange_glazed_terracotta", 221);
		override("magenta_glazed_terracotta", 222);
		override("light_blue_glazed_terracotta", 223);
		override("yellow_glazed_terracotta", 224);
		override("lime_glazed_terracotta", 225);
		override("pink_glazed_terracotta", 226);
		override("gray_glazed_terracotta", 227);
		override("light_gray_glazed_terracotta", 228);
		override("cyan_glazed_terracotta", 229);
		override("purple_glazed_terracotta", 219);
		override("blue_glazed_terracotta", 231);
		override("brown_glazed_terracotta", 232);
		override("green_glazed_terracotta", 233);
		override("red_glazed_terracotta", 234);
		override("black_glazed_terracotta", 235);
		override("white_concrete", 236);
		override("orange_concrete", 236, 1);
		override("magenta_concrete", 236, 2);
		override("light_blue_concrete", 236, 3);
		override("yellow_concrete", 236, 4);
		override("lime_concrete", 236, 5);
		override("pink_concrete", 236, 6);
		override("gray_concrete", 236, 7);
		override("light_gray_concrete", 236, 8);
		override("cyan_concrete", 236, 9);
		override("purple_concrete", 236, 10);
		override("blue_concrete", 236, 11);
		override("brown_concrete", 236, 12);
		override("green_concrete", 236, 13);
		override("red_concrete", 236, 14);
		override("black_concrete", 236, 15);
		override("white_concrete_powder", 237);
		override("orange_concrete_powder", 237, 1);
		override("magenta_concrete_powder", 237, 2);
		override("light_blue_concrete_powder", 237, 3);
		override("yellow_concrete_powder", 237, 4);
		override("lime_concrete_powder", 237, 5);
		override("pink_concrete_powder", 237, 6);
		override("gray_concrete_powder", 237, 7);
		override("light_gray_concrete_powder", 237, 8);
		override("cyan_concrete_powder", 237, 9);
		override("purple_concrete_powder", 237, 10);
		override("blue_concrete_powder", 237, 11);
		override("brown_concrete_powder", 237, 12);
		override("green_concrete_powder", 237, 13);
		override("red_concrete_powder", 237, 14);
		override("black_concrete_powder", 237, 15);
		override("turtle_egg", 255 - 414);
		override("dead_tube_coral_block", 255 - 387, 5);
		override("dead_brain_coral_block", 255 - 387, 6);
		override("dead_bubble_coral_block", 255 - 387, 7);
		override("dead_fire_coral_block", 255 - 387, 8);
		override("dead_horn_coral_block", 255 - 387, 9);
		override("tube_coral_block", 255 - 387);
		override("brain_coral_block", 255 - 387, 1);
		override("bubble_coral_block", 255 - 387, 2);
		override("fire_coral_block", 255 - 387, 3);
		override("horn_coral_block", 255 - 387, 4);
		override("tube_coral", 255 - 386);
		override("brain_coral", 255 - 386, 1);
		override("bubble_coral", 255 - 386, 2);
		override("fire_coral", 255 - 386, 3);
		override("horn_coral", 255 - 386, 4);
		override("dead_brain_coral", 255 - 386); // ?
		override("dead_bubble_coral", 255 - 386, 1); // ?
		override("dead_fire_coral", 255 - 386, 2); // ?
		override("dead_horn_coral", 255 - 386, 3); // ?
		override("dead_tube_coral", 255 - 386, 4); // ?
		override("tube_coral_fan", 255 - 388);
		override("brain_coral_fan", 255 - 388, 1);
		override("bubble_coral_fan", 255 - 388, 2);
		override("fire_coral_fan", 255 - 388, 3);
		override("horn_coral_fan", 255 - 388, 4);
		override("dead_tube_coral_fan", 255 - 389);
		override("dead_brain_coral_fan", 255 - 389, 1);
		override("dead_bubble_coral_fan", 255 - 389, 2);
		override("dead_fire_coral_fan", 255 - 389, 3);
		override("dead_horn_coral_fan", 255 - 389, 4);
		override("blue_ice", 255 - 266);
		override("conduit", 255 - 412);
		override("iron_door", 330);
		override("oak_door", 324);
		override("spruce_door", 427);
		override("birch_door", 428);
		override("jungle_door", 429);
		override("acacia_door", 430);
		override("dark_oak_door", 431);
		override("repeater", 356);
		override("comparator", 404);
		override("structure_block", 252);

		override("turtle_helmet", 469);
		override("scute", 468);
		override("iron_shovel", 256);
		override("iron_pickaxe", 257);
		override("iron_axe", 258);
		override("flint_and_steel", 259);
		override("apple", 260);
		override("bow", 261);
		override("arrow", 262);
		override("coal", 263);
		override("charcoal", 263, 1);
		override("diamond", 264);
		override("iron_ingot", 265);
		override("gold_ingot", 266);
		override("iron_sword", 267);
		override("wooden_sword", 268);
		override("wooden_shovel", 269);
		override("wooden_pickaxe", 270);
		override("wooden_axe", 271);
		override("stone_sword", 272);
		override("stone_shovel", 273);
		override("stone_pickaxe", 274);
		override("stone_axe", 275);
		override("diamond_sword", 276);
		override("diamond_shovel", 277);
		override("diamond_pickaxe", 278);
		override("diamond_axe", 279);
		override("stick", 280);
		override("bowl", 281);
		override("mushroom_stew", 282);
		override("golden_sword", 283);
		override("golden_shovel", 284);
		override("golden_pickaxe", 285);
		override("golden_axe", 286);
		override("string", 287);
		override("feather", 288);
		override("gunpowder", 289);
		override("wooden_hoe", 290);
		override("stone_hoe", 291);
		override("iron_hoe", 292);
		override("diamond_hoe", 293);
		override("golden_hoe", 294);
		override("wheat_seeds", 295);
		override("wheat", 296);
		override("bread", 297);
		override("leather_helmet", 298);
		override("leather_chestplate", 299);
		override("leather_leggings", 300);
		override("leather_boots", 301);
		override("chainmail_helmet", 302);
		override("chainmail_chestplate", 303);
		override("chainmail_leggings", 304);
		override("chainmail_boots", 305);
		override("iron_helmet", 306);
		override("iron_chestplate", 307);
		override("iron_leggings", 308);
		override("iron_boots", 309);
		override("diamond_helmet", 310);
		override("diamond_chestplate", 311);
		override("diamond_leggings", 312);
		override("diamond_boots", 313);
		override("golden_helmet", 314);
		override("golden_chestplate", 315);
		override("golden_leggings", 316);
		override("golden_boots", 317);
		override("flint", 318);
		override("porkchop", 319);
		override("cooked_porkchop", 320);
		override("painting", 321);
		override("golden_apple", 322);
		override("enchanted_golden_apple", 466);
		override("sign", 323);
		override("bucket", 325);
		override("water_bucket", 325, 8);
		override("lava_bucket", 325, 10);
		override("minecart", 328);
		override("saddle", 329);
		override("redstone", 331);
		override("snowball", 332);
		override("oak_boat", 333);
		override("leather", 334);
		override("milk_bucket", 325, 1);
		override("pufferfish_bucket", 325, 5);
		override("salmon_bucket", 325, 3);
		override("cod_bucket", 325, 2);
		override("tropical_fish_bucket", 325, 4);
		override("brick", 336);
		override("clay_ball", 337);
		override("sugar_cane", 338);
		override("kelp", 335);
		override("dried_kelp_block", 255 - 394);
		override("paper", 339);
		override("book", 340);
		override("slime_ball", 341);
		override("chest_minecart", 342);
		override("furnace_minecart", 343);
		override("egg", 344);
		override("compass", 345);
		override("fishing_rod", 346);
		override("clock", 347);
		override("glowstone_dust", 348);
		override("cod", 349);
		override("salmon", 460);
		override("tropical_fish", 461);
		override("pufferfish", 462);
		override("cooked_cod", 350);
		override("cooked_salmon", 463);
		override("ink_sac", 351);
		override("rose_red", 351, 1);
		override("cactus_green", 351, 2);
		override("cocoa_beans", 351, 3);
		override("lapis_lazuli", 351, 4);
		override("purple_dye", 351, 5);
		override("cyan_dye", 351, 6);
		override("light_gray_dye", 351, 7);
		override("gray_dye", 351, 8);
		override("pink_dye", 351, 9);
		override("lime_dye", 351, 10);
		override("dandelion_yellow", 351, 11);
		override("light_blue_dye", 351, 12);
		override("magenta_dye", 351, 13);
		override("orange_dye", 351, 14);
		override("bone_meal", 351, 15);
		override("bone", 352);
		override("sugar", 353);
		override("cake", 354);
		override("white_bed", 355);
		override("orange_bed", 355, 1);
		override("magenta_bed", 355, 2);
		override("light_blue_bed", 355, 3);
		override("yellow_bed", 355, 4);
		override("lime_bed", 355, 5);
		override("pink_bed", 355, 6);
		override("gray_bed", 355, 7);
		override("light_gray_bed", 355, 8);
		override("cyan_bed", 355, 9);
		override("purple_bed", 355, 10);
		override("blue_bed", 355, 11);
		override("brown_bed", 355, 12);
		override("green_bed", 355, 13);
		override("red_bed", 355, 14);
		override("black_bed", 355, 15);
		override("cookie", 357);
		override("filled_map", 358);
		override("shears", 359);
		override("melon_slice", 360);
		override("dried_kelp", 464);
		override("pumpkin_seeds", 361);
		override("melon_seeds", 362);
		override("beef", 363);
		override("cooked_beef", 364);
		override("chicken", 365);
		override("cooked_chicken", 366);
		override("rotten_flesh", 367);
		override("ender_pearl", 368);
		override("blaze_rod", 369);
		override("ghast_tear", 370);
		override("gold_nugget", 371);
		override("nether_wart", 372);
		override("potion", 373);
		override("glass_bottle", 374);
		override("spider_eye", 375);
		override("fermented_spider_eye", 376);
		override("blaze_powder", 377);
		override("magma_cream", 378);
		override("brewing_stand", 379);
		override("cauldron", 380);
		override("ender_eye", 381);
		override("glistering_melon_slice", 382);
		override("bat_spawn_egg", 383); // TODO NBT
		override("blaze_spawn_egg", 383); // TODO NBT
		override("cave_spider_spawn_egg", 383); // TODO NBT
		override("chicken_spawn_egg", 383); // TODO NBT
		override("cod_spawn_egg", 383); // TODO NBT
		override("cow_spawn_egg", 383); // TODO NBT
		override("creeper_spawn_egg", 383); // TODO NBT
		override("dolphin_spawn_egg", 383); // TODO NBT
		override("donkey_spawn_egg", 383); // TODO NBT
		override("drowned_spawn_egg", 383); // TODO NBT
		override("elder_guardian_spawn_egg", 383); // TODO NBT
		override("enderman_spawn_egg", 383); // TODO NBT
		override("endermite_spawn_egg", 383); // TODO NBT
		override("evoker_spawn_egg", 383); // TODO NBT
		override("ghast_spawn_egg", 383); // TODO NBT
		override("guardian_spawn_egg", 383); // TODO NBT
		override("horse_spawn_egg", 383); // TODO NBT
		override("husk_spawn_egg", 383); // TODO NBT
		override("llama_spawn_egg", 383); // TODO NBT
		override("magma_cube_spawn_egg", 383); // TODO NBT
		override("mooshroom_spawn_egg", 383); // TODO NBT
		override("mule_spawn_egg", 383); // TODO NBT
		override("ocelot_spawn_egg", 383); // TODO NBT
		override("parrot_spawn_egg", 383); // TODO NBT
		override("phantom_spawn_egg", 383); // TODO NBT
		override("pig_spawn_egg", 383); // TODO NBT
		override("polar_bear_spawn_egg", 383); // TODO NBT
		override("pufferfish_spawn_egg", 383); // TODO NBT
		override("rabbit_spawn_egg", 383); // TODO NBT
		override("salmon_spawn_egg", 383); // TODO NBT
		override("sheep_spawn_egg", 383); // TODO NBT
		override("shulker_spawn_egg", 383); // TODO NBT
		override("silverfish_spawn_egg", 383); // TODO NBT
		override("skeleton_spawn_egg", 383); // TODO NBT
		override("skeleton_horse_spawn_egg", 383); // TODO NBT
		override("slime_spawn_egg", 383); // TODO NBT
		override("spider_spawn_egg", 383); // TODO NBT
		override("squid_spawn_egg", 383); // TODO NBT
		override("stray_spawn_egg", 383); // TODO NBT
		override("tropical_fish_spawn_egg", 383); // TODO NBT
		override("turtle_spawn_egg", 383); // TODO NBT
		override("vex_spawn_egg", 383); // TODO NBT
		override("villager_spawn_egg", 383); // TODO NBT
		override("vindicator_spawn_egg", 383); // TODO NBT
		override("witch_spawn_egg", 383); // TODO NBT
		override("wither_skeleton_spawn_egg", 383); // TODO NBT
		override("wolf_spawn_egg", 383); // TODO NBT
		override("zombie_spawn_egg", 383); // TODO NBT
		override("zombie_horse_spawn_egg", 383); // TODO NBT
		override("zombie_pigman_spawn_egg", 383); // TODO NBT
		override("zombie_villager_spawn_egg", 383); // TODO NBT
		override("experience_bottle", 384);
		override("fire_charge", 385);
		override("writable_book", 386);
		override("written_book", 387);
		override("emerald", 388);
		override("item_frame", 389);
		override("flower_pot", 390);
		override("carrot", 391);
		override("potato", 392);
		override("baked_potato", 393);
		override("poisonous_potato", 394);
		override("map", 395);
		override("golden_carrot", 396);
		override("skeleton_skull", 397);
		override("wither_skeleton_skull", 397, 1);
		override("player_head", 397, 2);
		override("zombie_head", 397, 3);
		override("creeper_head", 397, 4);
		override("dragon_head", 397, 5);
		override("carrot_on_a_stick", 398);
		override("nether_star", 399);
		override("pumpkin_pie", 400);
		override("firework_rocket", 401);
		override("firework_star", 402);
		override("enchanted_book", 403);
		override("nether_brick", 405);
		override("quartz", 406);
		override("tnt_minecart", 407);
		override("hopper_minecart", 408);
		override("prismarine_shard", 409);
		override("prismarine_crystals", 410);
		override("rabbit", 411);
		override("cooked_rabbit", 412);
		override("rabbit_stew", 413);
		override("rabbit_foot", 414);
		override("rabbit_hide", 415);
		override("armor_stand", 425);
		override("iron_horse_armor", 417);
		override("golden_horse_armor", 418);
		override("diamond_horse_armor", 419);
		override("lead", 420);
		override("name_tag", 421);
		override("command_block_minecart", 443);
		override("mutton", 423);
		override("cooked_mutton", 424);
		override("white_banner", 446); // ?
		override("orange_banner", 446); // ?
		override("magenta_banner", 446); // ?
		override("light_blue_banner", 446); // ?
		override("yellow_banner", 446); // ?
		override("lime_banner", 446); // ?
		override("pink_banner", 446); // ?
		override("gray_banner", 446); // ?
		override("light_gray_banner", 446); // ?
		override("cyan_banner", 446); // ?
		override("purple_banner", 446); // ?
		override("blue_banner", 446); // ?
		override("brown_banner", 446); // ?
		override("green_banner", 446); // ?
		override("red_banner", 446); // ?
		override("black_banner", 446); // ?
		override("end_crystal", 426);
		override("chorus_fruit", 432);
		override("popped_chorus_fruit", 433);
		override("beetroot", 457);
		override("beetroot_seeds", 458);
		override("beetroot_soup", 459);
		override("dragon_breath", 437);
		override("splash_potion", 438);
		override("spectral_arrow", 262); // ?
		override("tipped_arrow", 262); // ?
		override("lingering_potion", 262); // ?
		// override("shield"); //?
		override("elytra", 444);
		override("spruce_boat", 333, 1);
		override("birch_boat", 333, 2);
		override("jungle_boat", 333, 3);
		override("acacia_boat", 333, 4);
		override("dark_oak_boat", 333, 5);
		override("totem_of_undying", 450);
		override("shulker_shell", 445);
		override("iron_nugget", 452);
		override("knowledge_book", 403); // ?
		override("debug_stick", 280); // ?
		override("music_disc_13", 500);
		override("music_disc_cat", 501);
		override("music_disc_blocks", 502);
		override("music_disc_chirp", 503);
		override("music_disc_far", 504);
		override("music_disc_mall", 505);
		override("music_disc_mellohi", 506);
		override("music_disc_stal", 507);
		override("music_disc_strad", 508);
		override("music_disc_ward", 509);
		override("music_disc_11", 510);
		override("music_disc_wait", 511);
		override("trident", 455);
		override("phantom_membrane", 470);
		override("nautilus_shell", 465);
		override("heart_of_the_sea", 467);
		
		Platform.log("§f[Flattening] §aLoaded §7" + PC_TO_PE_OVERRIDE.size() + "§a item translates from pc to pe!");
	}

	private void override(String pcName, int id) {
		override(pcName, id, 0);
	}

	private void override(String pcName, int id, int peData) {
		try {
			FlattedItem item = flatteningData.fromName("minecraft:" + pcName);
			override(item, new ItemEntry(id, peData));
		} catch (Exception e) {
			Platform.log("§cOverriding " + pcName + " failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void override(FlattedItem item, ItemEntry entry) {
		if (!PC_TO_PE_OVERRIDE.containsKey(item)) {
			PC_TO_PE_OVERRIDE.put(item, entry);
		}
		if (!PE_TO_PC_OVERRIDE.containsKey(entry.getId())) {
			PE_TO_PC_OVERRIDE.put(entry.getId(), new HashMap<>());
		}
		if (!PE_TO_PC_OVERRIDE.get(entry.getId()).containsKey(entry.getData())) {
			PE_TO_PC_OVERRIDE.get(entry.getId()).put(entry.getData(), item);
		}
	}

	private ItemEntry translateToPE(int protocolID) {
		return translateToPE(flatteningData.fromID(protocolID));
	}

	private ItemEntry translateToPE(FlattedItem item) {
		if (!PC_TO_PE_OVERRIDE.containsKey(item)) {
			return new ItemEntry(1);
		}
		return PC_TO_PE_OVERRIDE.get(item);
	}

	private FlattedItem translateToPC(int peId, int damage) {
		if (!PE_TO_PC_OVERRIDE.containsKey(peId)) {
			return flatteningData.fromID(1);
		}
		if (!PE_TO_PC_OVERRIDE.get(peId).containsKey(damage)) {
			return flatteningData.fromID(1);
		}
		return PE_TO_PC_OVERRIDE.get(peId).get(damage);
	}

	@Override
	public ItemEntry itemPcToPe(int id) {
		return translateToPE(id);
	}

	@Override
	public ItemEntry itemPcToPe(int id, int data) {
		return translateToPE(id); // data is not supported since 1.13
	}

	@Override
	public ItemEntry itemPcToPe(FlattedItem item) {
		return translateToPE(item);
	}

	@Override
	public FlattedItem itemPeToPc(int id) {
		return translateToPC(id, 0);
	}

	@Override
	public FlattedItem itemPeToPc(int id, int data) {
		return translateToPC(id, data);
	}

	@Override
	public FlattedItem itemPeToPc(ItemEntry item) {
		return translateToPC(item.getId(), item.getData());
	}

}
