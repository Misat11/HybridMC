package misat11.hybrid.network.java.p401.packet.ingame.server.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.window.ServerWindowItemsPacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerWindowItemsPacket401 extends MinecraftPacket implements ServerWindowItemsPacket {
    private int windowId;
    private ItemStack items[];

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readUnsignedByte();
        this.items = new ItemStack[in.readShort()];
        for(int index = 0; index < this.items.length; index++) {
            this.items[index] = getUtil().readItem(in);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeShort(this.items.length);
        for(ItemStack item : this.items) {
        	getUtil().writeItem(out, item);
        }
    }
}
