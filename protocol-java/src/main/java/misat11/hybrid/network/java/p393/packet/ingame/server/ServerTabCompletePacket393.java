package misat11.hybrid.network.java.p393.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.data.message.TextMessage;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerTabCompletePacket;

import java.io.IOException;

@Getter
public class ServerTabCompletePacket393 extends MinecraftPacket implements ServerTabCompletePacket {
    private int transactionId;
    private int start;
    private int length;
    private String matches[];
    private Message tooltips[];

    @SuppressWarnings("unused")
    private ServerTabCompletePacket393() {
    }

    public ServerTabCompletePacket393(int transactionId, int start, int length, String matches[], Message tooltips[]) {
        if (tooltips.length != matches.length) {
            throw new IllegalArgumentException("Length of matches and tooltips must be equal.");
        }
        this.transactionId = transactionId;
        this.start = start;
        this.length = length;
        this.matches = matches;
        this.tooltips = tooltips;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.matches = new String[in.readVarInt()];
        this.tooltips = new TextMessage[this.matches.length];
        for(int index = 0; index < this.matches.length; index++) {
            this.matches[index] = in.readString();
            if (in.readBoolean()) {
                this.tooltips[index] = Message.fromString(in.readString());
            }
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.matches.length);
        for(int index = 0; index < this.matches.length; index++) {
            out.writeString(this.matches[index]);
            Message tooltip = this.tooltips[index];
            if (tooltip != null) {
                out.writeBoolean(true);
                out.writeString(tooltip.toJsonString());
            } else {
                out.writeBoolean(false);
            }
        }
    }
}
