package misat11.hybrid.network.java.p401.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientTabCompletePacket;

import java.io.IOException;

@Getter
public class ClientTabCompletePacket401 extends MinecraftPacket implements ClientTabCompletePacket {
    private int transactionId;
    private String text;

    @SuppressWarnings("unused")
    private ClientTabCompletePacket401() {
    }

    public ClientTabCompletePacket401(int transactionId, String text) {
        this.transactionId = transactionId;
        this.text = text;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.transactionId = in.readVarInt();
        this.text = in.readString();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.transactionId);
        out.writeString(this.text);
    }
}
