package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.Animation;

public interface ServerEntityAnimationPacket {

	public int getEntityId();

	public Animation getAnimation();
}
