package misat11.hybrid.network.java.p404.packet.ingame.server.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.window.WindowType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.window.ServerOpenWindowPacket;

import java.io.IOException;

@Getter
public class ServerOpenWindowPacket404 extends MinecraftPacket implements ServerOpenWindowPacket {
    private int windowId;
    private WindowType type;
    private String name;
    private int slots;
    private int ownerEntityId;

    @SuppressWarnings("unused")
    private ServerOpenWindowPacket404() {
    }

    public ServerOpenWindowPacket404(int windowId, WindowType type, String name, int slots) {
        this(windowId, type, name, slots, 0);
    }

    public ServerOpenWindowPacket404(int windowId, WindowType type, String name, int slots, int ownerEntityId) {
        this.windowId = windowId;
        this.type = type;
        this.name = name;
        this.slots = slots;
        this.ownerEntityId = ownerEntityId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readUnsignedByte();
        this.type = getMagic().key(WindowType.class, in.readString());
        this.name = in.readString();
        this.slots = in.readUnsignedByte();
        if(this.type == WindowType.HORSE) {
            this.ownerEntityId = in.readInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeString(getMagic().value(String.class, this.type));
        out.writeString(this.name);
        out.writeByte(this.slots);
        if(this.type == WindowType.HORSE) {
            out.writeInt(this.ownerEntityId);
        }
    }
}
