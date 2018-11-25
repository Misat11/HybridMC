package misat11.hybrid.network.java.pabstract.packet.ingame.server.window;

import misat11.hybrid.network.java.pabstract.data.game.window.property.WindowProperty;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerWindowPropertyPacket extends IMinecraftPacket {
    public int getWindowId();

    public int getRawProperty();

    public <T extends Enum<T> & WindowProperty> T getProperty(Class<T> type);

    public int getValue();
}
