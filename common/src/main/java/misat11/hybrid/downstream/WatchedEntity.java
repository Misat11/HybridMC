package misat11.hybrid.downstream;

import misat11.hybrid.blockitems.ItemEntry;

public class WatchedEntity {
	
	private long entityID;
	private int type;
	private long vehicleID;
	private float headYaw;
	private float lastRidingYaw;
	
	// Equipment
	private ItemEntry boots;
	private ItemEntry leggins;
	private ItemEntry chestplate;
	private ItemEntry helmet;
	private ItemEntry hand;
	private ItemEntry offHand;
	
	public WatchedEntity(long entityID, int type) {
		this.entityID = entityID;
		this.type = type;
	}

	public long getEntityID() {
		return entityID;
	}

	public int getType() {
		return type;
	}

	public boolean isRiding() {
		return vehicleID != 0;
	}

	public long getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(long vehicleID) {
		this.vehicleID = vehicleID;
	}

	public float getHeadYaw() {
		return headYaw;
	}

	public void setHeadYaw(float headYaw) {
		this.headYaw = headYaw;
	}

	public ItemEntry getBoots() {
		return boots;
	}

	public void setBoots(ItemEntry boots) {
		this.boots = boots;
	}

	public ItemEntry getLeggins() {
		return leggins;
	}

	public void setLeggins(ItemEntry leggins) {
		this.leggins = leggins;
	}

	public ItemEntry getChestplate() {
		return chestplate;
	}

	public void setChestplate(ItemEntry chestplate) {
		this.chestplate = chestplate;
	}

	public ItemEntry getHelmet() {
		return helmet;
	}

	public void setHelmet(ItemEntry helmet) {
		this.helmet = helmet;
	}

	public ItemEntry getHand() {
		return hand;
	}

	public void setHand(ItemEntry hand) {
		this.hand = hand;
	}

	public ItemEntry getOffHand() {
		return offHand;
	}

	public void setOffHand(ItemEntry offHand) {
		this.offHand = offHand;
	}

	public float getLastRidingYaw() {
		return lastRidingYaw;
	}

	public void setLastRidingYaw(float lastRidingYaw) {
		this.lastRidingYaw = lastRidingYaw;
	}
}
