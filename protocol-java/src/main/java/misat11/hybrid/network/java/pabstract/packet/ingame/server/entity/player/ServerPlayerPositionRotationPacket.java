package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player;

import java.util.List;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.PositionElement;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerPositionRotationPacket extends IMinecraftPacket {
	public double getX();

	public double getY();

	public double getZ();

	public float getYaw();

	public float getPitch();

	public List<PositionElement> getRelativeElements();

	public int getTeleportId();
}
