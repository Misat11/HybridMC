package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.game.PlayerListEntry;
import misat11.hybrid.network.java.pabstract.data.game.PlayerListEntryAction;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayerListEntryPacket extends IMinecraftPacket {

	public PlayerListEntryAction getAction();

	public PlayerListEntry[] getEntries();
}
