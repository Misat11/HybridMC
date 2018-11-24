package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.data.game.entity.Effect;

public interface ServerEntityRemoveEffectPacket {

	public int getEntityId();

	public Effect getEffect();
}
