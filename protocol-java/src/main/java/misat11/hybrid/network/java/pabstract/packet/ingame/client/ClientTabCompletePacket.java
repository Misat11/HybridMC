package misat11.hybrid.network.java.pabstract.packet.ingame.client;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientTabCompletePacket extends IMinecraftPacket {
	public int getTransactionId();
	
	public String getText();
}
