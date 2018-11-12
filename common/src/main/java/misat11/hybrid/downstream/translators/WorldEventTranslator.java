package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.data.game.world.effect.BreakBlockEffectData;
import com.github.steveice10.mc.protocol.data.game.world.effect.ParticleEffect;
import com.github.steveice10.mc.protocol.data.game.world.effect.RecordEffectData;
import com.github.steveice10.mc.protocol.data.game.world.effect.SoundEffect;
import com.github.steveice10.mc.protocol.data.game.world.effect.WorldEffect;
import com.github.steveice10.mc.protocol.data.game.world.effect.WorldEffectData;
import com.github.steveice10.mc.protocol.data.game.world.sound.BuiltinSound;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlayEffectPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.SoundTranslator;
import misat11.hybrid.level.ParticleEffects;
import misat11.hybrid.message.JukeboxPopupMessage;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.LevelEventPacket;
import misat11.hybrid.network.bedrock.packet.PlaySoundPacket;
import misat11.hybrid.network.bedrock.packet.StopSoundPacket;
import misat11.hybrid.network.bedrock.packet.TextPacket;
import misat11.hybrid.network.bedrock.packet.LevelEventPacket.Event;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class WorldEventTranslator implements IDownstreamTranslator<ServerPlayEffectPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayEffectPacket packet) {
		WorldEffect effect = packet.getEffect();
		WorldEffectData data = packet.getData();
		List<BedrockPacket> packets = new ArrayList<>();
		if (effect == SoundEffect.RECORD && data instanceof RecordEffectData) {
			if (records.containsKey(((RecordEffectData) data).getRecordId())) {
				PlaySoundPacket psp = new PlaySoundPacket();
				psp.setSound(SoundTranslator.translate(records.get(((RecordEffectData) data).getRecordId())));
				psp.setBlockPosition(convertPosition(packet.getPosition()));
				psp.setPitch(1);
				psp.setVolume(10);
				packets.add(psp);
				session.getDownstream().getJukeboxCache().registerJukebox(convertPosition(packet.getPosition()),
						records.get(((RecordEffectData) data).getRecordId()));
				if (record_names.containsKey(((RecordEffectData) data).getRecordId())) {
					TextPacket text = new TextPacket();
					text.setMessage(new JukeboxPopupMessage(
							"Now playing: C418 - " + record_names.get(((RecordEffectData) data).getRecordId()),
							new String[0]));
					packets.add(text);
				}
			} else if (((RecordEffectData) data).getRecordId() == 0) {
				BuiltinSound sound = session.getDownstream().getJukeboxCache()
						.unregisterJukebox(convertPosition(packet.getPosition()));
				if (sound != null) {
					StopSoundPacket ssp = new StopSoundPacket();
					ssp.setSoundName(SoundTranslator.translate(sound));
					packets.add(ssp);
				}
			} else {
				// Something is wrong
			}
		} else if (effect == ParticleEffect.BREAK_BLOCK && data instanceof BreakBlockEffectData) {
			// I don't know where is this packet used
			PlaySoundPacket psp = new PlaySoundPacket();
			if (breakBlock.containsKey(((BreakBlockEffectData) data).getBlockState().getId())) {
				psp.setSound(SoundTranslator
						.translate(breakBlock.get(((BreakBlockEffectData) data).getBlockState().getId())));
				// TODO this not working correct on flattening servers
			} else {
				psp.setSound(SoundTranslator.translate(breakBlock.get(1)));
			}
			psp.setBlockPosition(convertPosition(packet.getPosition()));
			psp.setPitch(1);
			psp.setVolume(10);
			packets.add(psp);
		} else if (sounds.containsKey(effect)) {
			// This packet is used only when effect is from block not entity
			// ex. pressure plate -> door, but when player open door I don't hear anything
			if (!SoundTranslator.isIgnored(sounds.get(effect)) && SoundTranslator.isTranslatable(sounds.get(effect))) {
				PlaySoundPacket psp = new PlaySoundPacket();
				psp.setSound(SoundTranslator.translate(sounds.get(effect)));
				psp.setBlockPosition(convertPosition(packet.getPosition()));
				psp.setPitch(1);
				psp.setVolume(1);
				packets.add(psp);
			}
		}
		if (effect == ParticleEffect.SMOKE) {
			LevelEventPacket pk = new LevelEventPacket();
			pk.setEvent(Event.SET_DATA);
			pk.setPosition(new Vector3f(packet.getPosition().getX(), packet.getPosition().getY(),
					packet.getPosition().getZ()));
			pk.setData(ParticleEffects.TYPE_SMOKE.id);
			packets.add(pk);
		}
		if (effect == ParticleEffect.BONEMEAL_GROW) {
			LevelEventPacket pk = new LevelEventPacket();
			pk.setEvent(Event.PARTICLE_BONEMEAL);
			pk.setPosition(new Vector3f(packet.getPosition().getX(), packet.getPosition().getY(),
					packet.getPosition().getZ()));
			pk.setData(0);
			packets.add(pk);
		}
		if (effect == ParticleEffect.BREAK_EYE_OF_ENDER) {
			LevelEventPacket pk = new LevelEventPacket();
			pk.setEvent(Event.PARTICLE_EYE_DESPAWN);
			pk.setPosition(new Vector3f(packet.getPosition().getX(), packet.getPosition().getY(),
					packet.getPosition().getZ()));
			pk.setData(0);
			packets.add(pk);
		}
		if (effect == ParticleEffect.BREAK_SPLASH_POTION) {
			LevelEventPacket pk = new LevelEventPacket();
			pk.setEvent(Event.PARTICLE_SPLASH);
			pk.setPosition(new Vector3f(packet.getPosition().getX(), packet.getPosition().getY(),
					packet.getPosition().getZ()));
			pk.setData(0); // TODO
			packets.add(pk);
		}
		if (effect == ParticleEffect.END_GATEWAY_SPAWN) {
			// TODO
		}
		if (effect == ParticleEffect.ENDERDRAGON_FIREBALL_EXPLODE) {
			// TODO
		}
		if (effect == ParticleEffect.MOB_SPAWN) {
			LevelEventPacket pk = new LevelEventPacket();
			pk.setEvent(Event.PARTICLE_SPAWN);
			pk.setPosition(new Vector3f(packet.getPosition().getX(), packet.getPosition().getY(),
					packet.getPosition().getZ()));
			pk.setData(2);
			packets.add(pk);
		}
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

	private static HashMap<WorldEffect, BuiltinSound> sounds = new HashMap<WorldEffect, BuiltinSound>();
	private static HashMap<Integer, BuiltinSound> breakBlock = new HashMap<Integer, BuiltinSound>();
	private static HashMap<Integer, BuiltinSound> records = new HashMap<Integer, BuiltinSound>();
	private static HashMap<Integer, String> record_names = new HashMap<Integer, String>();

	static {
		sounds.put(SoundEffect.BLOCK_DISPENSER_DISPENSE, BuiltinSound.BLOCK_DISPENSER_DISPENSE);
		sounds.put(SoundEffect.BLOCK_DISPENSER_FAIL, BuiltinSound.BLOCK_DISPENSER_FAIL);
		sounds.put(SoundEffect.BLOCK_DISPENSER_LAUNCH, BuiltinSound.BLOCK_DISPENSER_LAUNCH);
		sounds.put(SoundEffect.ENTITY_ENDEREYE_LAUNCH, BuiltinSound.ENTITY_ENDER_EYE_LAUNCH);
		sounds.put(SoundEffect.ENTITY_FIREWORK_SHOOT, BuiltinSound.ENTITY_FIREWORK_ROCKET_SHOOT);
		sounds.put(SoundEffect.BLOCK_IRON_DOOR_OPEN, BuiltinSound.BLOCK_IRON_DOOR_OPEN);
		sounds.put(SoundEffect.BLOCK_WOODEN_DOOR_OPEN, BuiltinSound.BLOCK_WOODEN_DOOR_OPEN);
		sounds.put(SoundEffect.BLOCK_WOODEN_TRAPDOOR_OPEN, BuiltinSound.BLOCK_WOODEN_TRAPDOOR_OPEN);
		sounds.put(SoundEffect.BLOCK_FENCE_GATE_OPEN, BuiltinSound.BLOCK_FENCE_GATE_OPEN);
		sounds.put(SoundEffect.BLOCK_FIRE_EXTINGUISH, BuiltinSound.BLOCK_FIRE_EXTINGUISH);
		sounds.put(SoundEffect.BLOCK_IRON_DOOR_CLOSE, BuiltinSound.BLOCK_IRON_DOOR_CLOSE);
		sounds.put(SoundEffect.BLOCK_WOODEN_DOOR_CLOSE, BuiltinSound.BLOCK_WOODEN_DOOR_CLOSE);
		sounds.put(SoundEffect.BLOCK_WOODEN_TRAPDOOR_CLOSE, BuiltinSound.BLOCK_WOODEN_TRAPDOOR_CLOSE);
		sounds.put(SoundEffect.BLOCK_FENCE_GATE_CLOSE, BuiltinSound.BLOCK_FENCE_GATE_CLOSE);
		sounds.put(SoundEffect.ENTITY_GHAST_WARN, BuiltinSound.ENTITY_GHAST_WARN);
		sounds.put(SoundEffect.ENTITY_GHAST_SHOOT, BuiltinSound.ENTITY_GHAST_SHOOT);
		sounds.put(SoundEffect.ENTITY_ENDERDRAGON_SHOOT, BuiltinSound.ENTITY_ENDER_DRAGON_SHOOT);
		sounds.put(SoundEffect.ENTITY_BLAZE_SHOOT, BuiltinSound.ENTITY_BLAZE_SHOOT);
		sounds.put(SoundEffect.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, BuiltinSound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR);
		sounds.put(SoundEffect.ENTITY_ZOMBIE_ATTACK_DOOR_IRON, BuiltinSound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR);
		sounds.put(SoundEffect.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, BuiltinSound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR);
		sounds.put(SoundEffect.ENTITY_WITHER_BREAK_BLOCK, BuiltinSound.ENTITY_WITHER_BREAK_BLOCK);
		sounds.put(SoundEffect.ENTITY_WITHER_SPAWN, BuiltinSound.ENTITY_WITHER_SPAWN);
		sounds.put(SoundEffect.ENTITY_WITHER_SHOOT, BuiltinSound.ENTITY_WITHER_SHOOT);
		sounds.put(SoundEffect.ENTITY_BAT_TAKEOFF, BuiltinSound.ENTITY_BAT_TAKEOFF);
		sounds.put(SoundEffect.ENTITY_ZOMBIE_INFECT, BuiltinSound.ENTITY_ZOMBIE_INFECT);
		sounds.put(SoundEffect.ENTITY_ZOMBIE_VILLAGER_CONVERTED, BuiltinSound.ENTITY_ZOMBIE_VILLAGER_CONVERTED);
		sounds.put(SoundEffect.ENTITY_ENDERDRAGON_DEATH, BuiltinSound.ENTITY_ENDER_DRAGON_DEATH);
		sounds.put(SoundEffect.BLOCK_ANVIL_DESTROY, BuiltinSound.BLOCK_ANVIL_DESTROY);
		sounds.put(SoundEffect.BLOCK_ANVIL_USE, BuiltinSound.BLOCK_ANVIL_USE);
		sounds.put(SoundEffect.BLOCK_ANVIL_LAND, BuiltinSound.BLOCK_ANVIL_LAND);
		sounds.put(SoundEffect.BLOCK_PORTAL_TRAVEL, BuiltinSound.BLOCK_PORTAL_TRAVEL);
		sounds.put(SoundEffect.BLOCK_CHORUS_FLOWER_GROW, BuiltinSound.BLOCK_CHORUS_FLOWER_GROW);
		sounds.put(SoundEffect.BLOCK_CHORUS_FLOWER_DEATH, BuiltinSound.BLOCK_CHORUS_FLOWER_DEATH);
		sounds.put(SoundEffect.BLOCK_BREWING_STAND_BREW, BuiltinSound.BLOCK_BREWING_STAND_BREW);
		sounds.put(SoundEffect.BLOCK_IRON_TRAPDOOR_OPEN, BuiltinSound.BLOCK_IRON_TRAPDOOR_OPEN);
		sounds.put(SoundEffect.BLOCK_IRON_TRAPDOOR_CLOSE, BuiltinSound.BLOCK_IRON_TRAPDOOR_CLOSE);
		sounds.put(ParticleEffect.BREAK_EYE_OF_ENDER, BuiltinSound.ENTITY_ENDER_EYE_DEATH);
		sounds.put(SoundEffect.ENTITY_ENDERDRAGON_GROWL, BuiltinSound.ENTITY_ENDER_DRAGON_GROWL);
		sounds.put(ParticleEffect.BREAK_SPLASH_POTION, BuiltinSound.BLOCK_GLASS_BREAK);

		records.put(2256, BuiltinSound.MUSIC_DISC_13); // record_13
		records.put(2257, BuiltinSound.MUSIC_DISC_CAT); // record_cat
		records.put(2258, BuiltinSound.MUSIC_DISC_BLOCKS); // record_blocks
		records.put(2259, BuiltinSound.MUSIC_DISC_CHIRP); // record_chirp
		records.put(2260, BuiltinSound.MUSIC_DISC_FAR); // record_far
		records.put(2261, BuiltinSound.MUSIC_DISC_MALL); // record_mall
		records.put(2262, BuiltinSound.MUSIC_DISC_MELLOHI); // record_mellohi
		records.put(2263, BuiltinSound.MUSIC_DISC_STAL); // record_stal
		records.put(2264, BuiltinSound.MUSIC_DISC_STRAD); // record_strad
		records.put(2265, BuiltinSound.MUSIC_DISC_WARD); // record_ward
		records.put(2266, BuiltinSound.MUSIC_DISC_11); // record_11
		records.put(2267, BuiltinSound.MUSIC_DISC_WAIT); // record_wait

		record_names.put(2256, "13"); // record_13
		record_names.put(2257, "Cat"); // record_cat
		record_names.put(2258, "Blocks"); // record_blocks
		record_names.put(2259, "Chirp"); // record_chirp
		record_names.put(2260, "Far"); // record_far
		record_names.put(2261, "Mall"); // record_mall
		record_names.put(2262, "Mellohi"); // record_mellohi
		record_names.put(2263, "Stal"); // record_stal
		record_names.put(2264, "Strad"); // record_strad
		record_names.put(2265, "Ward"); // record_ward
		record_names.put(2266, "11"); // record_11
		record_names.put(2267, "Wait"); // record_wait

		breakBlock.put(1, BuiltinSound.BLOCK_STONE_BREAK);
		breakBlock.put(2, BuiltinSound.BLOCK_GRASS_BREAK);
		breakBlock.put(3, BuiltinSound.BLOCK_GRAVEL_BREAK);
		breakBlock.put(4, BuiltinSound.BLOCK_STONE_BREAK);
		breakBlock.put(5, BuiltinSound.BLOCK_WOOD_BREAK);
		breakBlock.put(6, BuiltinSound.BLOCK_GRASS_BREAK);
		breakBlock.put(12, BuiltinSound.BLOCK_SAND_BREAK);
		breakBlock.put(13, BuiltinSound.BLOCK_GRAVEL_BREAK);
	}

}
