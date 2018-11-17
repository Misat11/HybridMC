package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.CommandBlockMode;

public interface ClientUpdateCommandBlockPacket {

	public Position getPosition();

	public String getCommand();

	public CommandBlockMode getMode();

	public boolean isDoesTrackOutput();

	public boolean isConditional();

	public boolean isAutomatic();
}
