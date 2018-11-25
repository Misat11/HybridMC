package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.SoundTranslator;
import misat11.hybrid.level.SoundEvent;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.LevelSoundEventPacket;
import misat11.hybrid.network.bedrock.packet.PlaySoundPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerPlayBuiltinSoundPacket;

public class WorldSoundTranslator implements IDownstreamTranslator<ServerPlayBuiltinSoundPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayBuiltinSoundPacket packet) {
		LevelSoundEventPacket lsep = new LevelSoundEventPacket();
		lsep.setPosition(new Vector3f(packet.getX(), packet.getY(), packet.getZ()));
		lsep.setPitch((int) packet.getPitch());
		switch (packet.getSound()) {

		case BLOCK_CHEST_OPEN:
			lsep.setSoundEvent(SoundEvent.CHEST_OPEN);
			break;
		case BLOCK_CHEST_CLOSE:
			lsep.setSoundEvent(SoundEvent.CHEST_CLOSED);
			break;
		case BLOCK_SHULKER_BOX_OPEN:
			lsep.setSoundEvent(SoundEvent.SHULKER_OPEN);
			break;
		case BLOCK_SHULKER_BOX_CLOSE:
			lsep.setSoundEvent(SoundEvent.SHULKER_CLOSE);
			break;
		case ENCHANT_THORNS_HIT:
			lsep.setSoundEvent(SoundEvent.THORNS);
			break;
		case ENTITY_ARROW_SHOOT:
			lsep.setSoundEvent(SoundEvent.SHOOT);
			break;
		case ENTITY_ARROW_HIT_PLAYER:
			lsep.setSoundEvent(SoundEvent.HIT);
			break;
		case ENTITY_ARROW_HIT:
			lsep.setSoundEvent(SoundEvent.BOW_HIT);
			break;
		case ENTITY_GENERIC_EXTINGUISH_FIRE:
			lsep.setSoundEvent(SoundEvent.FIZZ);
			break;
		case BLOCK_FIRE_EXTINGUISH:
			lsep.setSoundEvent(SoundEvent.EXTINGUISH_FIRE);
			break;
		case ENTITY_CAT_PURR:
			lsep.setSoundEvent(SoundEvent.PURR);
			break;
		case ENTITY_CAT_PURREOW:
			lsep.setSoundEvent(SoundEvent.PURREOW);
			break;
		case ENTITY_HORSE_GALLOP:
			lsep.setSoundEvent(SoundEvent.GALLOP);
			break;
		case ENTITY_LIGHTNING_BOLT_THUNDER:
			lsep.setSoundEvent(SoundEvent.THUNDER);
			break;
		case ENTITY_PLAYER_LEVELUP:
			lsep.setSoundEvent(SoundEvent.LEVELUP);
			break;
		case ENTITY_LEASH_KNOT_PLACE:
			lsep.setSoundEvent(SoundEvent.LEASHKNOT_PLACE);
			break;
		case ENTITY_LEASH_KNOT_BREAK:
			lsep.setSoundEvent(SoundEvent.LEASHKNOT_BREAK);
			break;
		case ENTITY_SHULKER_OPEN:
			lsep.setSoundEvent(SoundEvent.SHULKER_OPEN);
			break;
		case ENTITY_SHULKER_CLOSE:
			lsep.setSoundEvent(SoundEvent.SHULKER_CLOSE);
			break;
		case ITEM_BOTTLE_FILL_DRAGONBREATH:
			lsep.setSoundEvent(SoundEvent.BOTTLE_DRAGONBREATH);
			break;
		case ITEM_BUCKET_FILL_LAVA:
			lsep.setSoundEvent(SoundEvent.BUCKET_FILL_LAVA);
			break;
		case ITEM_BUCKET_EMPTY_LAVA:
			lsep.setSoundEvent(SoundEvent.BUCKET_EMPTY_LAVA);
			break;
		case ITEM_BUCKET_FILL:
			lsep.setSoundEvent(SoundEvent.BUCKET_FILL_WATER);
			break;
		case ITEM_BUCKET_EMPTY:
			lsep.setSoundEvent(SoundEvent.BUCKET_EMPTY_WATER);
			break;
		case MUSIC_DISC_11:
			lsep.setSoundEvent(SoundEvent.RECORD_11);
			break;
		case MUSIC_DISC_13:
			lsep.setSoundEvent(SoundEvent.RECORD_13);
			break;
		case MUSIC_DISC_BLOCKS:
			lsep.setSoundEvent(SoundEvent.RECORD_BLOCKS);
			break;
		case MUSIC_DISC_CAT:
			lsep.setSoundEvent(SoundEvent.RECORD_CAT);
			break;
		case MUSIC_DISC_CHIRP:
			lsep.setSoundEvent(SoundEvent.RECORD_CHIRP);
			break;
		case MUSIC_DISC_FAR:
			lsep.setSoundEvent(SoundEvent.RECORD_FAR);
			break;
		case MUSIC_DISC_MALL:
			lsep.setSoundEvent(SoundEvent.RECORD_MALL);
			break;
		case MUSIC_DISC_MELLOHI:
			lsep.setSoundEvent(SoundEvent.RECORD_MELLOHI);
			break;
		case MUSIC_DISC_STAL:
			lsep.setSoundEvent(SoundEvent.RECORD_STAL);
			break;
		case MUSIC_DISC_STRAD:
			lsep.setSoundEvent(SoundEvent.RECORD_STRAD);
			break;
		case MUSIC_DISC_WAIT:
			lsep.setSoundEvent(SoundEvent.RECORD_WAIT);
			break;
		case MUSIC_DISC_WARD:
			lsep.setSoundEvent(SoundEvent.RECORD_WARD);
			break;
		case ENTITY_ZOMBIE_VILLAGER_CONVERTED:
			lsep.setSoundEvent(SoundEvent.UNFECT);
			break;
		case ENTITY_ZOMBIE_VILLAGER_CURE:
			lsep.setSoundEvent(SoundEvent.REMEDY);
			break;
		case ENTITY_SHEEP_SHEAR:
		case ENTITY_MOOSHROOM_SHEAR:
			lsep.setSoundEvent(SoundEvent.SHEAR);
			break;
		// Break sounds need test
		case BLOCK_GRASS_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(2));
			break;
		case BLOCK_ANVIL_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(145));
			break;
		case BLOCK_GLASS_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(20));
			break;
		case BLOCK_WOOL_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(35));
			break;
		case BLOCK_GRAVEL_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(13));
			break;
		case BLOCK_LADDER_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(65));
			break;
		case BLOCK_METAL_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(1));
			break;
		case BLOCK_SAND_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(12));
			break;
		case BLOCK_SLIME_BLOCK_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(165));
			break;
		case BLOCK_SNOW_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(80));
			break;
		case BLOCK_STONE_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(1));
			break;
		case BLOCK_WOOD_BREAK:
			lsep.setSoundEvent(SoundEvent.BREAK_BLOCK);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(5));
			break;
		// Place sounds need test
		case BLOCK_GRASS_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(2));
			break;
		case BLOCK_ANVIL_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(145));
			break;
		case BLOCK_WOOL_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(35));
			break;
		case BLOCK_GLASS_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(20));
			break;
		case BLOCK_GRAVEL_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(13));
			break;
		case BLOCK_LADDER_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(65));
			break;
		case BLOCK_METAL_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(1));
			break;
		case BLOCK_SAND_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(12));
			break;
		case BLOCK_SLIME_BLOCK_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(165));
			break;
		case BLOCK_SNOW_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(80));
			break;
		case BLOCK_STONE_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(1));
			break;
		case BLOCK_LILY_PAD_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(2));
			break;
		case BLOCK_WOOD_PLACE:
			lsep.setSoundEvent(SoundEvent.PLACE);
			lsep.setExtraData(session.getServer().getPaletteManager().fromLegacy(5));
			break;
		case BLOCK_LAVA_POP:
			lsep.setSoundEvent(SoundEvent.POP);
			break;
		case BLOCK_PORTAL_TRAVEL:
			lsep.setSoundEvent(SoundEvent.PORTAL);
			break;
		case BLOCK_LEVER_CLICK:
		case BLOCK_COMPARATOR_CLICK:
		case BLOCK_STONE_BUTTON_CLICK_ON:
		case BLOCK_METAL_PRESSURE_PLATE_CLICK_ON:
		case BLOCK_STONE_PRESSURE_PLATE_CLICK_ON:
		case BLOCK_TRIPWIRE_CLICK_ON:
		case BLOCK_WOODEN_BUTTON_CLICK_ON:
		case BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON:
			lsep.setSoundEvent(SoundEvent.POWER_ON);
			break;
		case BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF:
		case BLOCK_STONE_BUTTON_CLICK_OFF:
		case BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF:
		case BLOCK_TRIPWIRE_CLICK_OFF:
		case BLOCK_WOODEN_BUTTON_CLICK_OFF:
		case BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF:
			lsep.setSoundEvent(SoundEvent.POWER_OFF);
			break;
		// Notes need test and extradata or send with else packet
		case BLOCK_NOTE_BLOCK_BASEDRUM:
		case BLOCK_NOTE_BLOCK_BASS:
		case BLOCK_NOTE_BLOCK_BELL:
		case BLOCK_NOTE_BLOCK_CHIME:
		case BLOCK_NOTE_BLOCK_FLUTE:
		case BLOCK_NOTE_BLOCK_GUITAR:
		case BLOCK_NOTE_BLOCK_HARP:
		case BLOCK_NOTE_BLOCK_HAT:
		case BLOCK_NOTE_BLOCK_PLING:
		case BLOCK_NOTE_BLOCK_SNARE:
		case BLOCK_NOTE_BLOCK_XYLOPHONE:
			lsep.setSoundEvent(SoundEvent.NOTE);
			break;
		case BLOCK_PISTON_EXTEND:
			lsep.setSoundEvent(SoundEvent.PISTON_OUT);
			break;
		case BLOCK_PISTON_CONTRACT:
			lsep.setSoundEvent(SoundEvent.PISTON_IN);
			break;
		case ENTITY_FISHING_BOBBER_THROW:
			lsep.setSoundEvent(SoundEvent.SPLASH);
			break;
		case ENTITY_EGG_THROW:
			// Throw need test or else translates
		case ENTITY_ENDER_PEARL_THROW:
		case ENTITY_EXPERIENCE_BOTTLE_THROW:
		case ENTITY_LINGERING_POTION_THROW:
		case ENTITY_SNOWBALL_THROW:
		case ENTITY_SPLASH_POTION_THROW:
		case ENTITY_WITCH_THROW:
			lsep.setSoundEvent(SoundEvent.THROW);
			break;
		case ITEM_FLINTANDSTEEL_USE:
			lsep.setSoundEvent(SoundEvent.IGNITE);
			break;
		case ENTITY_TNT_PRIMED:
			lsep.setSoundEvent(SoundEvent.EVENT_SOUND_TNT);
			break;
		case ENTITY_GENERIC_EXPLODE:
			lsep.setSoundEvent(SoundEvent.EXPLODE);
			break;
		default:
			break;
		}

		if (lsep.getSoundEvent() != null) {
			return new BedrockPacket[] {lsep};
		}
		
		PlaySoundPacket psp = new PlaySoundPacket();
		psp.setBlockPosition(new Vector3i(packet.getX(), packet.getY(), packet.getZ()));
		psp.setPitch(packet.getPitch());
		psp.setVolume(packet.getVolume());
		if (!SoundTranslator.isIgnored(packet.getSound()) && SoundTranslator.isTranslatable(packet.getSound())) {
			psp.setSound(SoundTranslator.translate(packet.getSound()));
			return new BedrockPacket[] { psp };
		}
		return null;
	}

}
