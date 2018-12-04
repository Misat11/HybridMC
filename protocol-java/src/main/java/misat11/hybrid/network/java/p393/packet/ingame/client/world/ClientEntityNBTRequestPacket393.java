package misat11.hybrid.network.java.p393.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.world.ClientEntityNBTRequestPacket;

import java.io.IOException;

@Getter
public class ClientEntityNBTRequestPacket393 extends MinecraftPacket implements ClientEntityNBTRequestPacket{
    private int transactionId;
    private int entityId;

    @SuppressWarnings("unused")
    private ClientEntityNBTRequestPacket393() {
    }

    public ClientEntityNBTRequestPacket393(int transactionId, int entityId) {
        this.transactionId = transactionId;
        this.entityId = entityId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.transactionId = in.readVarInt();
        this.entityId = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.transactionId);
        out.writeVarInt(this.entityId);
    }
}
