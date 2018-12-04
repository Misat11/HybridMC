package misat11.hybrid.network.java.p393.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.world.ClientUpdateSignPacket;

import java.io.IOException;

@Getter
public class ClientUpdateSignPacket393 extends MinecraftPacket implements ClientUpdateSignPacket {
    private Position position;
    private String lines[];

    @SuppressWarnings("unused")
    private ClientUpdateSignPacket393() {
    }

    public ClientUpdateSignPacket393(Position position, String lines[]) {
        if(lines.length != 4) {
            throw new IllegalArgumentException("Lines must contain exactly 4 strings!");
        }

        this.position = position;
        this.lines = lines;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = getUtil().readPosition(in);
        this.lines = new String[4];
        for(int count = 0; count < this.lines.length; count++) {
            this.lines[count] = in.readString();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writePosition(out, this.position);
        for(String line : this.lines) {
            out.writeString(line);
        }
    }
}
