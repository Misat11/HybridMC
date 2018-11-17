package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientEditBookPacket;

import java.io.IOException;

@Getter
public class ClientEditBookPacket404 extends MinecraftPacket implements ClientEditBookPacket {
    private ItemStack book;
    private boolean isSigning;

    @SuppressWarnings("unused")
    private ClientEditBookPacket404() {
    }

    public ClientEditBookPacket404(ItemStack book, boolean isSigning) {
        this.book = book;
        this.isSigning = isSigning;
    }

    public boolean getIsSigning() {
        return this.isSigning;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.book = NetUtil404.readItem(in);
        this.isSigning = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil404.writeItem(out, this.book);
        out.writeBoolean(this.isSigning);
    }
}
