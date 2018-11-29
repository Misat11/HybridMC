package misat11.hybrid.network.java.p401.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.pabstract.data.game.setting.Difficulty;
import misat11.hybrid.network.java.pabstract.data.game.world.WorldType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerJoinGamePacket;

import java.io.IOException;

@Getter
public class ServerJoinGamePacket401 extends MinecraftPacket implements ServerJoinGamePacket {
    private int entityId;
    private boolean hardcore;
    private GameMode gameMode;
    private int dimension;
    private Difficulty difficulty;
    private int maxPlayers;
    private WorldType worldType;
    private boolean reducedDebugInfo;

    @SuppressWarnings("unused")
    private ServerJoinGamePacket401() {
    }

    public ServerJoinGamePacket401(int entityId, boolean hardcore, GameMode gameMode, int dimension, Difficulty difficulty, int maxPlayers, WorldType worldType, boolean reducedDebugInfo) {
        this.entityId = entityId;
        this.hardcore = hardcore;
        this.gameMode = gameMode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.worldType = worldType;
        this.reducedDebugInfo = reducedDebugInfo;
    }
    
    @Override
    public boolean getReducedDebugInfo() {
    	return this.reducedDebugInfo;
    }
    
    @Override
    public boolean getHardcore() {
    	return this.hardcore;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readInt();
        int gamemode = in.readUnsignedByte();
        this.hardcore = (gamemode & 8) == 8;
        gamemode &= -9;
        this.gameMode = getMagic().key(GameMode.class, gamemode);
        this.dimension = in.readInt();
        this.difficulty = getMagic().key(Difficulty.class, in.readUnsignedByte());
        this.maxPlayers = in.readUnsignedByte();
        this.worldType = getMagic().key(WorldType.class, in.readString().toLowerCase());
        this.reducedDebugInfo = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(this.entityId);
        int gamemode = getMagic().value(Integer.class, this.gameMode);
        if(this.hardcore) {
            gamemode |= 8;
        }

        out.writeByte(gamemode);
        out.writeInt(this.dimension);
        out.writeByte(getMagic().value(Integer.class, this.difficulty));
        out.writeByte(this.maxPlayers);
        out.writeString(getMagic().value(String.class, this.worldType));
        out.writeBoolean(this.reducedDebugInfo);
    }
}
