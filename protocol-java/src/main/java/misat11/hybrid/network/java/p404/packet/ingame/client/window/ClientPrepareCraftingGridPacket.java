package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ClientPrepareCraftingGridPacket extends MinecraftPacket {
    private int windowId;
    private String recipeId;
    private boolean makeAll;

    @SuppressWarnings("unused")
    private ClientPrepareCraftingGridPacket() {
    }

    public ClientPrepareCraftingGridPacket(int windowId, String recipeId, boolean makeAll) {
        this.windowId = windowId;
        this.recipeId = recipeId;
        this.makeAll = makeAll;
    }

    public int getWindowId() {
        return this.windowId;
    }

    public String getRecipeId() {
        return this.recipeId;
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
