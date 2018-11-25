package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerAbilitiesPacket extends IMinecraftPacket {

	public boolean getInvincible();

	public boolean getCanFly();

	public boolean getFlying();

	public boolean getCreative();

	public float getFlySpeed();

	public float getWalkSpeed();
}
