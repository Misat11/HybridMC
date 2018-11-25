package misat11.hybrid.network.java.pabstract.packet.ingame.client.player;

import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.InteractAction;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientPlayerInteractEntityPacket extends IMinecraftPacket {

	public int getEntityId();

	public InteractAction getAction();

	public float getTargetX();

	public float getTargetY();

	public float getTargetZ();

	public Hand getHand();

}
