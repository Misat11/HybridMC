package misat11.hybrid.network.java.pabstract.data.game.entity.player;

import misat11.hybrid.network.java.pabstract.data.game.world.notify.ClientNotificationValue;

public enum GameMode implements ClientNotificationValue {
    SURVIVAL,
    CREATIVE,
    ADVENTURE,
    SPECTATOR;
}
