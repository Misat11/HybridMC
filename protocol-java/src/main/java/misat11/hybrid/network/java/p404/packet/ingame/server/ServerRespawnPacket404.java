package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.pabstract.data.game.setting.Difficulty;
import misat11.hybrid.network.java.pabstract.data.game.world.WorldType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerRespawnPacket;

import java.io.IOException;

@Getter
public class ServerRespawnPacket404 extends MinecraftPacket implements ServerRespawnPacket {
    private int dimension;
    private Difficulty difficulty;
    private GameMode gameMode;
    private WorldType worldType;

    @SuppressWarnings("unused")
    private ServerRespawnPacket404() {
    }

    public ServerRespawnPacket404(int dimension, Difficulty difficulty, GameMode gameMode, WorldType worldType) {
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.gameMode = gameMode;
        this.worldType = worldType;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.dimension = in.readInt();
        this.difficulty = MagicValues404.key(Difficulty.class, in.readUnsignedByte());
        this.gameMode = MagicValues404.key(GameMode.class, in.readUnsignedByte());
        this.worldType = MagicValues404.key(WorldType.class, in.readString().toLowerCase());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(this.dimension);
        out.writeByte(MagicValues404.value(Integer.class, this.difficulty));
        out.writeByte(MagicValues404.value(Integer.class, this.gameMode));
        out.writeString(MagicValues404.value(String.class, this.worldType));
    }
}