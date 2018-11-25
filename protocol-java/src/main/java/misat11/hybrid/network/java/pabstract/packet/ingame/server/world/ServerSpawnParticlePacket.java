package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.world.particle.Particle;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerSpawnParticlePacket extends IMinecraftPacket {
	public Particle getParticle();

	public boolean isLongDistance();

	public float getX();

	public float getY();

	public float getZ();

	public float getOffsetX();

	public float getOffsetY();

	public float getOffsetZ();

	public float getVelocityOffset();

	public int getAmount();
}
