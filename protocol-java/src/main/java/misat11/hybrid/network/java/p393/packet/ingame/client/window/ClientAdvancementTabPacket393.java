package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.data.game.window.AdvancementTabAction;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientAdvancementTabPacket;

import java.io.IOException;

public class ClientAdvancementTabPacket393 extends MinecraftPacket implements ClientAdvancementTabPacket {
    private AdvancementTabAction action;
    private String tabId;

    public ClientAdvancementTabPacket393() {
        this.action = AdvancementTabAction.CLOSED_SCREEN;
    }

    public ClientAdvancementTabPacket393(String tabId) {
        this.action = AdvancementTabAction.OPENED_TAB;
        this.tabId = tabId;
    }

    public String getTabId() {
        if(this.action != AdvancementTabAction.OPENED_TAB) {
            throw new IllegalStateException("tabId is only set if action is " + AdvancementTabAction.OPENED_TAB
                    + " but it was " + this.action);
        }
        return tabId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        switch(this.action = getMagic().key(AdvancementTabAction.class, in.readVarInt())) {
            case CLOSED_SCREEN:
                break;
            case OPENED_TAB:
                this.tabId = in.readString();
                break;
            default:
                throw new IOException("Unknown advancement tab action: " + this.action);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.action));
        switch(this.action) {
            case CLOSED_SCREEN:
                break;
            case OPENED_TAB:
                out.writeString(this.tabId);
                break;
            default:
                throw new IOException("Unknown advancement tab action: " + this.action);
        }
    }
}
