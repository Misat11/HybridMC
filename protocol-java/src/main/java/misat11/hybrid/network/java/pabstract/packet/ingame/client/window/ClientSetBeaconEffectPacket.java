package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientSetBeaconEffectPacket extends IMinecraftPacket {

	public int getPrimaryEffect();

	public int getSecondaryEffect();
}
