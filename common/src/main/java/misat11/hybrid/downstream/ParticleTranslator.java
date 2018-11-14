package misat11.hybrid.downstream;

import java.util.HashMap;

import misat11.hybrid.level.ParticleEffects;
import misat11.hybrid.network.java.p404.data.game.world.particle.ParticleType;

public class ParticleTranslator {
    private static HashMap<ParticleType, Integer> pctope = new HashMap<ParticleType, Integer>();

    static {
        register(ParticleType.EXPLOSION, ParticleEffects.TYPE_EXPLODE);
        register(ParticleType.EXPLOSION_EMITTER, ParticleEffects.TYPE_HUGE_EXPLODE);
        register(ParticleType.BUBBLE, ParticleEffects.TYPE_BUBBLE);
        register(ParticleType.SPLASH, ParticleEffects.TYPE_SPLASH);
        register(ParticleType.FISHING, ParticleEffects.TYPE_WATER_WAKE);
        register(ParticleType.CRIT, ParticleEffects.TYPE_CRITICAL);
        register(ParticleType.ENCHANTED_HIT, ParticleEffects.TYPE_CRITICAL);
        register(ParticleType.SMOKE, ParticleEffects.TYPE_SMOKE);
        register(ParticleType.LARGE_SMOKE, ParticleEffects.TYPE_LARGE_SMOKE);
        register(ParticleType.ENTITY_EFFECT, ParticleEffects.TYPE_MOB_SPELL);
        register(ParticleType.AMBIENT_ENTITY_EFFECT, ParticleEffects.TYPE_MOB_SPELL_AMBIENT);
        register(ParticleType.WITCH, ParticleEffects.TYPE_WITCH_SPELL);
        register(ParticleType.DRIPPING_WATER, ParticleEffects.TYPE_DRIP_WATER);
        register(ParticleType.DRIPPING_LAVA, ParticleEffects.TYPE_DRIP_LAVA);
        register(ParticleType.ANGRY_VILLAGER, ParticleEffects.TYPE_VILLAGER_ANGRY);
        register(ParticleType.HAPPY_VILLAGER, ParticleEffects.TYPE_VILLAGER_HAPPY);
        register(ParticleType.MYCELIUM, ParticleEffects.TYPE_SUSPENDED_TOWN);
        register(ParticleType.NOTE, ParticleEffects.TYPE_NOTE);
        register(ParticleType.PORTAL, ParticleEffects.TYPE_PORTAL);
        register(ParticleType.ENCHANT, ParticleEffects.TYPE_ENCHANTMENT_TABLE);
        register(ParticleType.FLAME, ParticleEffects.TYPE_FLAME);
        register(ParticleType.LAVA, ParticleEffects.TYPE_LAVA);
        register(ParticleType.CLOUD, ParticleEffects.TYPE_EVAPORATION);
        register(ParticleType.DUST, ParticleEffects.TYPE_REDSTONE);
        register(ParticleType.ITEM_SNOWBALL, ParticleEffects.TYPE_SNOWBALL_POOF);
        register(ParticleType.POOF, ParticleEffects.TYPE_EXPLODE);
        register(ParticleType.ITEM_SLIME, ParticleEffects.TYPE_SLIME);
        register(ParticleType.HEART, ParticleEffects.TYPE_HEART);
        register(ParticleType.ITEM, ParticleEffects.TYPE_ITEM_BREAK);
        register(ParticleType.RAIN, ParticleEffects.TYPE_RAIN_SPLASH); 
        register(ParticleType.DRAGON_BREATH, ParticleEffects.TYPE_DRAGONS_BREATH);
        register(ParticleType.END_ROD, ParticleEffects.TYPE_END_ROD);
        register(ParticleType.FALLING_DUST, ParticleEffects.TYPE_FALLING_DUST);
        // TODO add missing
        
    }

    public static int translate(ParticleType particle) {
        if (!pctope.containsKey(particle)) {
            return -1;
        }
        return pctope.get(particle);
    }

    private static void register(ParticleType pc, ParticleEffects pe) {
        pctope.put(pc, pe.id);
    }
}
