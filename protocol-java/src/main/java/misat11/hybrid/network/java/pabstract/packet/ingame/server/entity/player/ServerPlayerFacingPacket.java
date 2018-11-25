package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player;

import misat11.hybrid.network.java.pabstract.data.game.entity.FeetOrEyes;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerFacingPacket extends IMinecraftPacket {

	public FeetOrEyes getOrigin();

	public double getX();

	public double getY();

	public double getZ();

	public Integer getTargetEntityId();

	public FeetOrEyes getTargetEntityFeetOrEyes();
}
