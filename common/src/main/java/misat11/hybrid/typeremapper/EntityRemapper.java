package misat11.hybrid.typeremapper;

import java.util.HashMap;
import java.util.Map;

import com.flowpowered.math.vector.Vector3f;

import misat11.hybrid.entity.EntityType;
import misat11.hybrid.network.java.p404.data.game.entity.type.MobType;
import misat11.hybrid.network.java.p404.data.game.entity.type.object.ObjectType;

public class EntityRemapper {

	private static Map<MobType, Integer> mobs = new HashMap<>();
	private static Map<ObjectType, Integer> objects = new HashMap<>();

	private static Map<Integer, EntityType> peIdToPeEntity = new HashMap<>();

	static {
		// Mobs
		put(MobType.BAT, EntityType.BAT);
		put(MobType.BLAZE, EntityType.BLAZE);
		put(MobType.CAVE_SPIDER, EntityType.CAVE_SPIDER);
		put(MobType.CHICKEN, EntityType.CHICKEN);
		put(MobType.COD, EntityType.FISH);
		put(MobType.COW, EntityType.COW);
		put(MobType.CREEPER, EntityType.CREEPER);
		put(MobType.DONKEY, EntityType.DONKEY);
		put(MobType.DOLPHIN, EntityType.DOLPHIN);
		put(MobType.DROWNED, 110); // Missing in EntityType
		put(MobType.ELDER_GUARDIAN, EntityType.ELDER_GUARDIAN);
		put(MobType.ENDER_DRAGON, EntityType.ENDER_DRAGON);
		put(MobType.ENDERMAN, EntityType.ENDERMAN);
		put(MobType.ENDERMITE, EntityType.ENDERMITE);
		put(MobType.EVOCATION_ILLAGER, EntityType.EVOCATION_ILLAGER);
		put(MobType.GHAST, EntityType.GHAST);
		put(MobType.GIANT_ZOMBIE, EntityType.ZOMBIE); // Not in BE
		put(MobType.GUARDIAN, EntityType.GUARDIAN);
		put(MobType.HORSE, EntityType.HORSE);
		put(MobType.HUSK, EntityType.HUSK);
		put(MobType.ILLUSION_ILLAGER, EntityType.VINDICATOR); // Not in BE
		put(MobType.LLAMA, EntityType.LLAMA);
		put(MobType.MAGMA_CUBE, EntityType.MAGMA_CUBE);
		put(MobType.MULE, EntityType.MULE);
		put(MobType.MOOSHROOM, EntityType.MOOSHROOM);
		put(MobType.OCELOT, EntityType.OCELOT);
		put(MobType.PARROT, EntityType.PARROT);
		put(MobType.PIG, EntityType.PIG);
		put(MobType.PUFFERFISH, EntityType.PUFFER_FISH);
		put(MobType.ZOMBIE_PIGMAN, EntityType.ZOMBIE_PIGMAN);
		put(MobType.POLAR_BEAR, EntityType.POLAR_BEAR);
		put(MobType.RABBIT, EntityType.RABBIT);
		put(MobType.SALMON, EntityType.SALMON);
		put(MobType.SHEEP, EntityType.SHEEP);
		put(MobType.SHULKER, EntityType.SHULKER);
		put(MobType.SILVERFISH, EntityType.SILVERFISH);
		put(MobType.SKELETON, EntityType.SKELETON);
		put(MobType.SKELETON_HORSE, EntityType.SKELETON_HORSE);
		put(MobType.SLIME, EntityType.SLIME);
		put(MobType.SNOWMAN, EntityType.SNOW_GOLEM);
		put(MobType.SPIDER, EntityType.SPIDER);
		put(MobType.SQUID, EntityType.SQUID);
		put(MobType.STRAY, EntityType.STRAY);
		put(MobType.TROPICAL_FISH, EntityType.TROPICAL_FISH);
		put(MobType.TURTLE, 74); // Missing in EntityType
		put(MobType.VEX, EntityType.VEX);
		put(MobType.VILLAGER, EntityType.VILLAGER);
		put(MobType.IRON_GOLEM, EntityType.IRON_GOLEM);
		put(MobType.VINDICATION_ILLAGER, EntityType.VINDICATOR);
		put(MobType.WITCH, EntityType.WITCH);
		put(MobType.WITHER, EntityType.WITHER);
		put(MobType.WITHER_SKELETON, EntityType.WITHER_SKELETON);
		put(MobType.WOLF, EntityType.WOLF);
		put(MobType.ZOMBIE, EntityType.ZOMBIE);
		put(MobType.ZOMBIE_HORSE, EntityType.ZOMBIE_HORSE);
		put(MobType.ZOMBIE_VILLAGER, EntityType.ZOMBIE_VILLAGER);
		put(MobType.PHANTOM, 58);

		// Objects
		put(ObjectType.BOAT, EntityType.BOAT);
		put(ObjectType.ITEM, EntityType.ITEM);
		put(ObjectType.AREA_EFFECT_CLOUD, EntityType.AREA_EFFECT_CLOUD);
		put(ObjectType.MINECART, EntityType.MINECART);
		put(ObjectType.PRIMED_TNT, EntityType.PRIMED_TNT);
		put(ObjectType.ENDER_CRYSTAL, EntityType.ENDER_CRYSTAL);
		put(ObjectType.SNOWBALL, EntityType.SNOWBALL);
		put(ObjectType.EGG, EntityType.EGG);
		put(ObjectType.GHAST_FIREBALL, EntityType.LARGE_FIREBALL);
		put(ObjectType.BLAZE_FIREBALL, EntityType.SMALL_FIREBALL);
		put(ObjectType.ENDER_PEARL, EntityType.ENDER_PEARL);
		put(ObjectType.WITHER_HEAD_PROJECTILE, EntityType.WITHER_SKULL_DANGEROUS); // ?
		put(ObjectType.SHULKER_BULLET, EntityType.SHULKER_BULLET);
		put(ObjectType.LLAMA_SPIT, EntityType.LLAMA_SPIT);
		put(ObjectType.FALLING_BLOCK, EntityType.FALLING_BLOCK);
		put(ObjectType.ITEM_FRAME, 0); // In BE it is block
		put(ObjectType.EYE_OF_ENDER, EntityType.EYE_OF_ENDER);
		put(ObjectType.POTION, EntityType.SPLASH_POTION); // ?
		put(ObjectType.EXP_BOTTLE, EntityType.EXPERIENCE_BOTTLE);
		put(ObjectType.FIREWORK_ROCKET, EntityType.FIREWORKS_ROCKET);
		put(ObjectType.LEASH_KNOT, EntityType.LEASH_FENCE_KNOT);
		put(ObjectType.ARMOR_STAND, EntityType.ARMOR_STAND);
		put(ObjectType.EVOCATION_FANGS, EntityType.EVOCATION_FANG);
		put(ObjectType.FISH_HOOK, EntityType.FISHING_HOOK);
		put(ObjectType.SPECTRAL_ARROW, EntityType.ARROW); // ?
		put(ObjectType.TIPPED_ARROW, EntityType.ARROW); // ?
		put(ObjectType.DRAGON_FIREBALL, EntityType.DRAGON_FIREBALL);
		put(ObjectType.TRIDENT, EntityType.TRIDENT);
	}

	public static int transformMobType(MobType mob) {
		return mobs.get(mob);
	}

	public static int transformObjectType(ObjectType obj) {
		return objects.get(obj);
	}

	public static EntityType getEntityTypeFromId(int id) {
		return peIdToPeEntity.get(id);
	}
	
	public static Vector3f makeOffset(int id) {
		float y = 0;
		if (peIdToPeEntity.containsKey(id)) {
			EntityType type = peIdToPeEntity.get(id);
			y = type.getOffset();
		}
		return new Vector3f(0, y, 0);
	}

	private static void put(MobType obj, EntityType en) {
		mobs.put(obj, en.getType());
		peIdToPeEntity.put(en.getType(), en);
	}

	private static void put(MobType obj, int id) {
		mobs.put(obj, id);
	}

	private static void put(ObjectType obj, EntityType en) {
		objects.put(obj, en.getType());
		peIdToPeEntity.put(en.getType(), en);
	}

	private static void put(ObjectType obj, int id) {
		objects.put(obj, id);
	}
}
