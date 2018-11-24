package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

public interface ServerEntityCollectItemPacket {

	public int getCollectedEntityId();

	public int getCollectorEntityId();

	public int getItemCount();
}
