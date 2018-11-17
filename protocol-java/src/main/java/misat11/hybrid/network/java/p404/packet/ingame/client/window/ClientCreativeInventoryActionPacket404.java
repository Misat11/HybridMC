package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientCreativeInventoryActionPacket;

import java.io.IOException;

@Getter
public class ClientCreativeInventoryActionPacket404 extends MinecraftPacket implements ClientCreativeInventoryActionPacket {
    private int slot;
    private ItemStack clickedItem;

    @SuppressWarnings("unused")
    private ClientCreativeInventoryActionPacket404() {
    }

    public ClientCreativeInventoryActionPacket404(int slot, ItemStack clickedItem) {
        this.slot = slot;
        this.clickedItem = clickedItem;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.slot = in.readShort();
        this.clickedItem = NetUtil404.readItem(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeShort(this.slot);
        NetUtil404.writeItem(out, this.clickedItem);
    }
}
