package misat11.hybrid.network.java.pabstract.packet.ingame.server.world;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.WorldEffect;
import misat11.hybrid.network.java.pabstract.data.game.world.effect.WorldEffectData;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ServerPlayEffectPacket extends IMinecraftPacket {
    public WorldEffect getEffect();

    public Position getPosition();

    public WorldEffectData getData();

    public boolean getBroadcast();
}
