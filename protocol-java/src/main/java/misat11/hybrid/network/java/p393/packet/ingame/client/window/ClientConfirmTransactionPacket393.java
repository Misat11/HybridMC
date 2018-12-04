package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientConfirmTransactionPacket;

import java.io.IOException;

@Getter
public class ClientConfirmTransactionPacket393 extends MinecraftPacket implements ClientConfirmTransactionPacket {
    private int windowId;
    private int actionId;
    private boolean accepted;

    @SuppressWarnings("unused")
    private ClientConfirmTransactionPacket393() {
    }

    public ClientConfirmTransactionPacket393(int windowId, int actionId, boolean accepted) {
        this.windowId = windowId;
        this.actionId = actionId;
        this.accepted = accepted;
    }

    public boolean getAccepted() {
        return this.accepted;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readByte();
        this.actionId = in.readShort();
        this.accepted = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeShort(this.actionId);
        out.writeBoolean(this.accepted);
    }
}
