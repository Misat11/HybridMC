package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

public interface ClientConfirmTransactionPacket {

	public int getWindowId();

	public int getActionId();

	public boolean getAccepted();
}
