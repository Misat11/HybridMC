package misat11.hybrid.typeremapper;

import java.util.HashMap;
import java.util.Map;

import com.github.steveice10.mc.protocol.data.game.entity.type.MobType;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.ObjectType;
import com.nukkitx.server.entity.EntityType;

public class EntityRemapper {
	
	private static Map<MobType,Integer> mobs = new HashMap<>();
	private static Map<ObjectType,Integer> objects = new HashMap<>();
	
	static {
		//Mobs
		mobs.put(MobType.BAT, EntityType.BAT.getType());
		mobs.put(MobType.BLAZE, EntityType.BLAZE.getType());
		mobs.put(MobType.CAVE_SPIDER, EntityType.CAVE_SPIDER.getType());
		mobs.put(MobType.CHICKEN, EntityType.CHICKEN.getType());
		mobs.put(MobType.COD, EntityType.FISH.getType());
		mobs.put(MobType.COW, EntityType.COW.getType());
		mobs.put(MobType.CREEPER, EntityType.CREEPER.getType());
		mobs.put(MobType.DONKEY, EntityType.DONKEY.getType());
		mobs.put(MobType.DOLPHIN, EntityType.DOLPHIN.getType());
		mobs.put(MobType.DROWNED, 110); // Missing in EntityType
		mobs.put(MobType.ELDER_GUARDIAN, EntityType.ELDER_GUARDIAN.getType());
		mobs.put(MobType.ENDER_DRAGON, EntityType.ENDER_DRAGON.getType());
		mobs.put(MobType.ENDERMAN, EntityType.ENDERMAN.getType());
		mobs.put(MobType.ENDERMITE, EntityType.ENDERMITE.getType());
		mobs.put(MobType.EVOCATION_ILLAGER, EntityType.EVOCATION_ILLAGER.getType());
		mobs.put(MobType.GHAST, EntityType.GHAST.getType());
		mobs.put(MobType.GIANT_ZOMBIE, EntityType.ZOMBIE.getType()); // Not in BE
		mobs.put(MobType.GUARDIAN, EntityType.GUARDIAN.getType());
		mobs.put(MobType.HORSE, EntityType.HORSE.getType());
		mobs.put(MobType.HUSK, EntityType.HUSK.getType());
		mobs.put(MobType.ILLUSION_ILLAGER, EntityType.VINDICATOR.getType()); // Not in BE
		mobs.put(MobType.LLAMA, EntityType.LLAMA.getType());
		mobs.put(MobType.MAGMA_CUBE, EntityType.MAGMA_CUBE.getType());
		mobs.put(MobType.MULE, EntityType.MULE.getType());
		mobs.put(MobType.MOOSHROOM, EntityType.MOOSHROOM.getType());
		mobs.put(MobType.OCELOT, EntityType.OCELOT.getType());
		mobs.put(MobType.PARROT, EntityType.PARROT.getType());
		mobs.put(MobType.PIG, EntityType.PIG.getType());
		mobs.put(MobType.PUFFERFISH, EntityType.PUFFER_FISH.getType());
		mobs.put(MobType.ZOMBIE_PIGMAN, EntityType.ZOMBIE_PIGMAN.getType());
		mobs.put(MobType.POLAR_BEAR, EntityType.POLAR_BEAR.getType());
		mobs.put(MobType.RABBIT, EntityType.RABBIT.getType());
		mobs.put(MobType.SALMON, EntityType.SALMON.getType());
		mobs.put(MobType.SHEEP, EntityType.SHEEP.getType());
		mobs.put(MobType.SHULKER, EntityType.SHULKER.getType());
		mobs.put(MobType.SILVERFISH, EntityType.SILVERFISH.getType());
		mobs.put(MobType.SKELETON, EntityType.SKELETON.getType());
		mobs.put(MobType.SKELETON_HORSE, EntityType.SKELETON_HORSE.getType());
		mobs.put(MobType.SLIME, EntityType.SLIME.getType());
		mobs.put(MobType.SNOWMAN, EntityType.SNOW_GOLEM.getType());
		mobs.put(MobType.SPIDER, EntityType.SPIDER.getType());
		mobs.put(MobType.SQUID, EntityType.SQUID.getType());
		mobs.put(MobType.STRAY, EntityType.STRAY.getType());
		mobs.put(MobType.TROPICAL_FISH, EntityType.TROPICAL_FISH.getType());
		mobs.put(MobType.TURTLE, 74); // Missing in EntityType
		mobs.put(MobType.VEX, EntityType.VEX.getType());
		mobs.put(MobType.VILLAGER, EntityType.VILLAGER.getType());
		mobs.put(MobType.IRON_GOLEM, EntityType.IRON_GOLEM.getType());
		mobs.put(MobType.VINDICATION_ILLAGER, EntityType.VINDICATOR.getType());
		mobs.put(MobType.WITCH, EntityType.WITCH.getType());
		mobs.put(MobType.WITHER, EntityType.WITHER.getType());
		mobs.put(MobType.WITHER_SKELETON, EntityType.WITHER_SKELETON.getType());
		mobs.put(MobType.WOLF, EntityType.WOLF.getType());
		mobs.put(MobType.ZOMBIE, EntityType.ZOMBIE.getType());
		mobs.put(MobType.ZOMBIE_HORSE, EntityType.ZOMBIE_HORSE.getType());
		mobs.put(MobType.ZOMBIE_VILLAGER, EntityType.ZOMBIE_VILLAGER.getType());
		mobs.put(MobType.PHANTOM, 58);
		
		//Objects
		objects.put(ObjectType.BOAT, EntityType.BOAT.getType());
		objects.put(ObjectType.ITEM, EntityType.ITEM.getType());
		objects.put(ObjectType.AREA_EFFECT_CLOUD, EntityType.AREA_EFFECT_CLOUD.getType());
		objects.put(ObjectType.MINECART, EntityType.MINECART.getType());
		objects.put(ObjectType.PRIMED_TNT, EntityType.PRIMED_TNT.getType());
		objects.put(ObjectType.ENDER_CRYSTAL, EntityType.ENDER_CRYSTAL.getType());
		objects.put(ObjectType.SNOWBALL, EntityType.SNOWBALL.getType());
		objects.put(ObjectType.EGG, EntityType.EGG.getType());
		objects.put(ObjectType.GHAST_FIREBALL, EntityType.LARGE_FIREBALL.getType());
		objects.put(ObjectType.BLAZE_FIREBALL, EntityType.SMALL_FIREBALL.getType());
		objects.put(ObjectType.ENDER_PEARL, EntityType.ENDER_PEARL.getType());
		objects.put(ObjectType.WITHER_HEAD_PROJECTILE, EntityType.WITHER_SKULL_DANGEROUS.getType()); //?
		objects.put(ObjectType.SHULKER_BULLET, EntityType.SHULKER_BULLET.getType());
		objects.put(ObjectType.LLAMA_SPIT, EntityType.LLAMA_SPIT.getType());
		objects.put(ObjectType.FALLING_BLOCK, EntityType.FALLING_BLOCK.getType());
		objects.put(ObjectType.ITEM_FRAME, 0); // In BE it is block
		objects.put(ObjectType.EYE_OF_ENDER, EntityType.EYE_OF_ENDER.getType());
		objects.put(ObjectType.POTION, EntityType.SPLASH_POTION.getType()); //?
		objects.put(ObjectType.EXP_BOTTLE, EntityType.EXPERIENCE_BOTTLE.getType());
		objects.put(ObjectType.FIREWORK_ROCKET, EntityType.FIREWORKS_ROCKET.getType());
		objects.put(ObjectType.LEASH_KNOT, EntityType.LEASH_FENCE_KNOT.getType());
		objects.put(ObjectType.ARMOR_STAND, EntityType.ARMOR_STAND.getType());
		objects.put(ObjectType.EVOCATION_FANGS, EntityType.EVOCATION_FANG.getType());
		objects.put(ObjectType.FISH_HOOK, EntityType.FISHING_HOOK.getType());
		objects.put(ObjectType.SPECTRAL_ARROW, EntityType.ARROW.getType()); // ?
		objects.put(ObjectType.TIPPED_ARROW, EntityType.ARROW.getType()); // ?
		objects.put(ObjectType.DRAGON_FIREBALL, EntityType.DRAGON_FIREBALL.getType());
		objects.put(ObjectType.TRIDENT, EntityType.TRIDENT.getType());
	}
	
	public static Integer transformMobType(MobType mob) {
		return mobs.get(mob);
	}
	
	public static Integer transformObjectType(ObjectType obj) {
		return objects.get(obj);
	}
}
