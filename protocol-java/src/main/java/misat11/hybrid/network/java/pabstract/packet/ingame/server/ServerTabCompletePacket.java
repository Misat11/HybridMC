package misat11.hybrid.network.java.pabstract.packet.ingame.server;

import misat11.hybrid.network.java.pabstract.data.message.Message;

public interface ServerTabCompletePacket {
	public int getTransactionId();

	public int getStart();

	public int getLength();

	public String[] getMatches();

	public Message[] getTooltips();
}
