package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.world.map.MapData;
import misat11.hybrid.network.java.pabstract.data.game.world.map.MapIcon;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerMapDataPacket extends IMinecraftPacket {
	public int getMapId();

	public byte getScale();

	public boolean getTrackingPosition();

	public MapIcon[] getIcons();

	public MapData getData();
}
