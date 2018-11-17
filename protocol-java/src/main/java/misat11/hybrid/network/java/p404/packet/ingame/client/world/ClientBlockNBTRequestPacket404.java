package misat11.hybrid.network.java.p404.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.world.ClientBlockNBTRequestPacket;

import java.io.IOException;

@Getter
public class ClientBlockNBTRequestPacket404 extends MinecraftPacket implements ClientBlockNBTRequestPacket {
    private int transactionId;
    private Position position;

    @SuppressWarnings("unused")
    private ClientBlockNBTRequestPacket404() {
    }

    public ClientBlockNBTRequestPacket404(int transactionId, Position position) {
        this.transactionId = transactionId;
        this.position = position;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.transactionId = in.readVarInt();
        this.position = NetUtil404.readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.transactionId);
        NetUtil404.writePosition(out, this.position);
    }
}
