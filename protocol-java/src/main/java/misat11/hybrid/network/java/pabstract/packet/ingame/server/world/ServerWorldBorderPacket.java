package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.world.WorldBorderAction;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerWorldBorderPacket extends IMinecraftPacket {
	public WorldBorderAction getAction();

	public double getRadius();

	public double getOldRadius();

	public double getNewRadius();

	public long getSpeed();

	public double getCenterX();

	public double getCenterY();

	public int getPortalTeleportBoundary();

	public int getWarningTime();

	public int getWarningBlocks();
}
