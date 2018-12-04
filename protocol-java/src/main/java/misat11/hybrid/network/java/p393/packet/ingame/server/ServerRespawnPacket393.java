package misat11.hybrid.network.java.p393.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.pabstract.data.game.setting.Difficulty;
import misat11.hybrid.network.java.pabstract.data.game.world.WorldType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerRespawnPacket;

import java.io.IOException;

@Getter
public class ServerRespawnPacket393 extends MinecraftPacket implements ServerRespawnPacket {
    private int dimension;
    private Difficulty difficulty;
    private GameMode gameMode;
    private WorldType worldType;

    @SuppressWarnings("unused")
    private ServerRespawnPacket393() {
    }

    public ServerRespawnPacket393(int dimension, Difficulty difficulty, GameMode gameMode, WorldType worldType) {
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.gameMode = gameMode;
        this.worldType = worldType;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.dimension = in.readInt();
        this.difficulty = getMagic().key(Difficulty.class, in.readUnsignedByte());
        this.gameMode = getMagic().key(GameMode.class, in.readUnsignedByte());
        this.worldType = getMagic().key(WorldType.class, in.readString().toLowerCase());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(this.dimension);
        out.writeByte(getMagic().value(Integer.class, this.difficulty));
        out.writeByte(getMagic().value(Integer.class, this.gameMode));
        out.writeString(getMagic().value(String.class, this.worldType));
    }
}
