package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

public interface ClientUpdateCommandBlockMinecartPacket {

	public int getEntityId();

	public String getCommand();

	public boolean isDoesTrackOutput();
}
