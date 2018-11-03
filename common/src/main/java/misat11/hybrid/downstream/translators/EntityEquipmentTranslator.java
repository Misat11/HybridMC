package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.data.game.entity.EquipmentSlot;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityEquipmentPacket;
import com.nukkitx.api.item.ItemTypes;
import com.nukkitx.server.item.NukkitItemInstance;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.MobArmorEquipmentPacket;
import com.nukkitx.server.network.bedrock.packet.MobEquipmentPacket;

import misat11.hybrid.blockitems.ItemEntry;
import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityEquipmentTranslator implements IDownstreamTranslator<ServerEntityEquipmentPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityEquipmentPacket packet) {
		WatchedEntity entity = session.getDownstream().getWatchedEntities().get((long) packet.getEntityId());
		if (entity == null) {
			return null;
		}
		switch (packet.getSlot()) {
		case BOOTS:
			entity.setBoots(session.getServer().getItemTranslator().itemPcToPe(packet.getItem().getId()));
			break;
		case CHESTPLATE:
			entity.setChestplate(session.getServer().getItemTranslator().itemPcToPe(packet.getItem().getId()));
			break;
		case HELMET:
			entity.setHelmet(session.getServer().getItemTranslator().itemPcToPe(packet.getItem().getId()));
			break;
		case LEGGINGS:
			entity.setLeggins(session.getServer().getItemTranslator().itemPcToPe(packet.getItem().getId()));
			break;
		case MAIN_HAND:
			entity.setHand(session.getServer().getItemTranslator().itemPcToPe(packet.getItem().getId()));
			break;
		case OFF_HAND:
			entity.setOffHand(session.getServer().getItemTranslator().itemPcToPe(packet.getItem().getId()));
			break;
		default:
			break;
		}
		if (packet.getSlot() == EquipmentSlot.BOOTS || packet.getSlot() == EquipmentSlot.CHESTPLATE
				|| packet.getSlot() == EquipmentSlot.HELMET || packet.getSlot() == EquipmentSlot.LEGGINGS) {
			return new BedrockPacket[] { create(entity.getEntityID(), entity.getHelmet(), entity.getChestplate(),
					entity.getLeggins(), entity.getBoots()) };
		}
		return new BedrockPacket[] { create(entity.getEntityID(),
				packet.getSlot() == EquipmentSlot.MAIN_HAND ? entity.getHand() : entity.getOffHand(), 0,
				packet.getSlot() == EquipmentSlot.MAIN_HAND) };
	}

	public static MobArmorEquipmentPacket create(long entityID, ItemEntry helmet, ItemEntry chestplate,
			ItemEntry leggins, ItemEntry boots) {
		MobArmorEquipmentPacket maep = new MobArmorEquipmentPacket();
		maep.setRuntimeEntityId(entityID);
		if (helmet != null)
			maep.setHelmet(new NukkitItemInstance(ItemTypes.byId(helmet.getId())));
		if (chestplate != null)
			maep.setChestplate(new NukkitItemInstance(ItemTypes.byId(chestplate.getId())));
		if (leggins != null)
			maep.setLeggings(new NukkitItemInstance(ItemTypes.byId(leggins.getId())));
		if (boots != null)
			maep.setBoots(new NukkitItemInstance(ItemTypes.byId(boots.getId())));
		return maep;
	}

	public static MobEquipmentPacket create(long entityID, ItemEntry hand, int slot, boolean mainHand) {
		MobEquipmentPacket mep = new MobEquipmentPacket();
		mep.setRuntimeEntityId(entityID);
		if (hand != null)
			mep.setItem(new NukkitItemInstance(ItemTypes.byId(hand.getId())));
		mep.setHotbarSlot((byte) slot);
		mep.setInventorySlot((byte) slot);
		mep.setWindowId((byte) (mainHand ? 119 : 0));
		return mep;
	}

}
