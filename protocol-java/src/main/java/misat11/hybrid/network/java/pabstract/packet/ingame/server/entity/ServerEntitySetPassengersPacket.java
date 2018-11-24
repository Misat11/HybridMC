package misat11.hybrid.network.java.pabstract.packet.ingame.server.entity;

public interface ServerEntitySetPassengersPacket {

	public int getEntityId();

	public int[] getPassengerIds();
}
