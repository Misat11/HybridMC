package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientPrepareCraftingGridPacket;

import java.io.IOException;

@Getter
public class ClientPrepareCraftingGridPacket393 extends MinecraftPacket implements ClientPrepareCraftingGridPacket {
    private int windowId;
    private String recipeId;
    private boolean makeAll;

    @SuppressWarnings("unused")
    private ClientPrepareCraftingGridPacket393() {
    }

    public ClientPrepareCraftingGridPacket393(int windowId, String recipeId, boolean makeAll) {
        this.windowId = windowId;
        this.recipeId = recipeId;
        this.makeAll = makeAll;
    }

    public boolean doesMakeAll() {
        return makeAll;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readByte();
        this.recipeId = in.readString();
        this.makeAll = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeString(this.recipeId);
        out.writeBoolean(this.makeAll);
    }
}
