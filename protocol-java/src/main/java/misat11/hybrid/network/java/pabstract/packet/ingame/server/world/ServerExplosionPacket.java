package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import java.util.List;

import misat11.hybrid.network.java.pabstract.data.game.world.block.ExplodedBlockRecord;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerExplosionPacket extends IMinecraftPacket {
	public float getX();

	public float getY();

	public float getZ();

	public float getRadius();

	public List<ExplodedBlockRecord> getExploded();

	public float getPushX();

	public float getPushY();

	public float getPushZ();
}
