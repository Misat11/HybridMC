package misat11.hybrid.network.java.p401.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.world.ClientBlockNBTRequestPacket;

import java.io.IOException;

@Getter
public class ClientBlockNBTRequestPacket401 extends MinecraftPacket implements ClientBlockNBTRequestPacket {
    private int transactionId;
    private Position position;

    @SuppressWarnings("unused")
    private ClientBlockNBTRequestPacket401() {
    }

    public ClientBlockNBTRequestPacket401(int transactionId, Position position) {
        this.transactionId = transactionId;
        this.position = position;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.transactionId = in.readVarInt();
        this.position = getUtil().readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.transactionId);
        getUtil().writePosition(out, this.position);
    }
}
