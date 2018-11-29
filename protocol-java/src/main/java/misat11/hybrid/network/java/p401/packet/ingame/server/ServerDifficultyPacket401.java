package misat11.hybrid.network.java.p401.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.setting.Difficulty;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerDifficultyPacket;

import java.io.IOException;

@Getter
public class ServerDifficultyPacket401 extends MinecraftPacket implements ServerDifficultyPacket {
    private Difficulty difficulty;

    @SuppressWarnings("unused")
    private ServerDifficultyPacket401() {
    }

    public ServerDifficultyPacket401(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.difficulty = getMagic().key(Difficulty.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(getMagic().value(Integer.class, this.difficulty));
    }
}
