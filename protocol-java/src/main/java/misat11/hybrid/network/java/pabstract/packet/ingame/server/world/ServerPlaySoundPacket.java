package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.world.sound.Sound;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.SoundCategory;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlaySoundPacket extends IMinecraftPacket {
	public Sound getSound();

	public SoundCategory getCategory();

	public double getX();

	public double getY();

	public double getZ();

	public float getVolume();

	public float getPitch();
}
