package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.world.notify.ClientNotification;
import misat11.hybrid.network.java.pabstract.data.game.world.notify.ClientNotificationValue;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerNotifyClientPacket extends IMinecraftPacket {
    public ClientNotification getNotification();

    public ClientNotificationValue getValue();
}
