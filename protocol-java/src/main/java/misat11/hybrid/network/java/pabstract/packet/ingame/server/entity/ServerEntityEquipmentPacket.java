package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.data.game.entity.EquipmentSlot;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;

public interface ServerEntityEquipmentPacket {

	public int getEntityId();

	public EquipmentSlot getSlot();

	public ItemStack getItem();
}
