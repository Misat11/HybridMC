package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerAdvancementTabPacket;

import java.io.IOException;

@Getter
public class ServerAdvancementTabPacket404 extends MinecraftPacket implements ServerAdvancementTabPacket {
    private String tabId;

    @SuppressWarnings("unused")
    private ServerAdvancementTabPacket404() {
    }

    public ServerAdvancementTabPacket404(String tabId) {
        this.tabId = tabId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        if(in.readBoolean()) {
            this.tabId = in.readString();
        } else {
            this.tabId = null;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        if(this.tabId != null) {
            out.writeBoolean(true);
            out.writeString(this.tabId);
        } else {
            out.writeBoolean(false);
        }
    }
}
