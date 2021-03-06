package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientConfirmTransactionPacket extends IMinecraftPacket {

	public int getWindowId();

	public int getActionId();

	public boolean getAccepted();
}
