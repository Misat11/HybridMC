package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityStatusPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.entity.EntityEvent;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.EntityEventPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityStatusTranslator implements IDownstreamTranslator<ServerEntityStatusPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityStatusPacket packet) {
		// TODO translate other cases
		List<BedrockPacket> packets = new ArrayList<>();
		switch (packet.getStatus()) {
		case ANIMAL_EMIT_HEARTS:
			break;
		case ARMOR_STAND_HIT:
			break;
		case FIREWORK_EXPLODE:
			packets.add(create(packet.getEntityId(), EntityEvent.FIREWORK_PARTICLES, 0));
			break;
		case FISHING_HOOK_PULL_PLAYER:
			packets.add(create(packet.getEntityId(), EntityEvent.FISH_HOOK_HOOK, 0));
			break;
		case GUARDIAN_MAKE_SOUND:
			break;
		case IRON_GOLEM_ATTACK:
			break;
		case IRON_GOLEM_EMPTY_HAND:
			break;
		case IRON_GOLEM_HOLD_POPPY:
			break;
		case LIVING_BURN:
			break;
		case LIVING_DEATH:
			break;
		case LIVING_DROWN:
			break;
		case LIVING_HURT:
			break;
		case LIVING_HURT_THORNS:
			break;
		case LIVING_SHIELD_BLOCK:
			break;
		case LIVING_SHIELD_BREAK:
			break;
		case MOB_EMIT_SMOKE:
			break;
		case PLAYER_DISABLE_REDUCED_DEBUG:
			break;
		case PLAYER_ENABLE_REDUCED_DEBUG:
			break;
		case PLAYER_FINISH_USING_ITEM:
			break;
		case PLAYER_OP_PERMISSION_LEVEL_0:
			break;
		case PLAYER_OP_PERMISSION_LEVEL_1:
			break;
		case PLAYER_OP_PERMISSION_LEVEL_2:
			break;
		case PLAYER_OP_PERMISSION_LEVEL_3:
			break;
		case PLAYER_OP_PERMISSION_LEVEL_4:
			break;
		case RABBIT_JUMP_OR_MINECART_SPAWNER_DELAY_RESET:
			break;
		case SHEEP_GRAZE_OR_TNT_CART_EXPLODE:
			packets.add(create(packet.getEntityId(), EntityEvent.MINECART_TNT_PRIME_FUSE, 0));
			break;
		case SQUID_RESET_ROTATION:
			break;
		case TAMEABLE_TAMING_FAILED:
			break;
		case TAMEABLE_TAMING_SUCCEEDED:
			break;
		case TIPPED_ARROW_EMIT_PARTICLES:
			break;
		case TOTEM_OF_UNDYING_MAKE_SOUND:
			break;
		case VILLAGER_ANGRY:
			break;
		case VILLAGER_HAPPY:
			break;
		case VILLAGER_MATE:
			break;
		case WITCH_EMIT_PARTICLES:
			break;
		case WOLF_SHAKE_WATER:
			break;
		case ZOMBIE_VILLAGER_CURE:
			break;
		default:
			break;
		}
		return packets.toArray(new BedrockPacket[packets.size()]);
	}
	
	public static EntityEventPacket create(long entityID, EntityEvent event, int data) {
		EntityEventPacket eep = new EntityEventPacket();
		eep.setRuntimeEntityId(entityID);
		eep.setEvent(event);
		eep.setData(data);
		return eep;
	}

}
