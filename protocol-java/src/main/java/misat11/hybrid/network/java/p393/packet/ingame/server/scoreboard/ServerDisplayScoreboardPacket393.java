package misat11.hybrid.network.java.p393.packet.ingame.server.scoreboard;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.ScoreboardPosition;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.scoreboard.ServerDisplayScoreboardPacket;

import java.io.IOException;

@Getter
public class ServerDisplayScoreboardPacket393 extends MinecraftPacket implements ServerDisplayScoreboardPacket {
    private ScoreboardPosition position;
    private String scoreboardName;

    @SuppressWarnings("unused")
    private ServerDisplayScoreboardPacket393() {
    }

    public ServerDisplayScoreboardPacket393(ScoreboardPosition position, String name) {
        this.position = position;
        this.scoreboardName = name;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = getMagic().key(ScoreboardPosition.class, in.readByte());
        this.scoreboardName = in.readString();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(getMagic().value(Integer.class, this.position));
        out.writeString(this.scoreboardName);
    }
}
