package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import java.util.UUID;

import misat11.hybrid.network.java.pabstract.data.game.BossBarAction;
import misat11.hybrid.network.java.pabstract.data.game.BossBarColor;
import misat11.hybrid.network.java.pabstract.data.game.BossBarDivision;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerBossBarPacket extends IMinecraftPacket {
	public UUID getUUID();

	public BossBarAction getAction();

	public Message getTitle();

	public float getHealth();

	public BossBarColor getColor();

	public BossBarDivision getDivision();

	public boolean getDarkenSky();

	public boolean shouldPlayEndMusic();

	public boolean shouldShowFog();
}
