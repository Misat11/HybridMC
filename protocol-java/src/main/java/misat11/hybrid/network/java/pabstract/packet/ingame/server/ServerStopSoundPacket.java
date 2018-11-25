package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.world.sound.Sound;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.SoundCategory;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerStopSoundPacket extends IMinecraftPacket {

	public SoundCategory getCategory();

	public Sound getSound();
}
