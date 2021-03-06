package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

import misat11.hybrid.network.java.pabstract.data.game.entity.Effect;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerEntityRemoveEffectPacket extends IMinecraftPacket {

	public int getEntityId();

	public Effect getEffect();
}
