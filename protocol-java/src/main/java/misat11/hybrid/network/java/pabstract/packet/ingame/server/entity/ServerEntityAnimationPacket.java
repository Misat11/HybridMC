package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.Animation;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerEntityAnimationPacket extends IMinecraftPacket {

	public int getEntityId();

	public Animation getAnimation();
}
