package misat11.hybrid.network.java.p401.packet.ingame.server.scoreboard;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.ScoreboardAction;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.scoreboard.ServerUpdateScorePacket;

import java.io.IOException;

@Getter
public class ServerUpdateScorePacket401 extends MinecraftPacket implements ServerUpdateScorePacket {
    private String entry;
    private ScoreboardAction action;
    private String objective;
    private int value;

    @SuppressWarnings("unused")
    private ServerUpdateScorePacket401() {
    }

    public ServerUpdateScorePacket401(String entry, String objective) {
        this.entry = entry;
        this.objective = objective;
        this.action = ScoreboardAction.REMOVE;
    }

    public ServerUpdateScorePacket401(String entry, String objective, int value) {
        this.entry = entry;
        this.objective = objective;
        this.value = value;
        this.action = ScoreboardAction.ADD_OR_UPDATE;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entry = in.readString();
        this.action = getMagic().key(ScoreboardAction.class, in.readVarInt());
        this.objective = in.readString();
        if(this.action == ScoreboardAction.ADD_OR_UPDATE) {
            this.value = in.readVarInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.entry);
        out.writeVarInt(getMagic().value(Integer.class, this.action));
        out.writeString(this.objective);
        if(this.action == ScoreboardAction.ADD_OR_UPDATE) {
            out.writeVarInt(this.value);
        }
    }
}
