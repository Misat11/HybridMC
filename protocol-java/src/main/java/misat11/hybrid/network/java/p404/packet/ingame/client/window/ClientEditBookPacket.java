package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.p404.packet.MinecraftPacket;
import misat11.hybrid.network.java.p404.util.NetUtil;

import java.io.IOException;

public class ClientEditBookPacket extends MinecraftPacket {
    private ItemStack book;
    private boolean isSigning;

    @SuppressWarnings("unused")
    private ClientEditBookPacket() {
    }

    public ClientEditBookPacket(ItemStack book, boolean isSigning) {
        this.book = book;
        this.isSigning = isSigning;
    }

    public ItemStack getBook() {
        return this.book;
    }

    public boolean getIsSigning() {
        return this.isSigning;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.book = NetUtil.readItem(in);
        this.isSigning = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writeItem(out, this.book);
        out.writeBoolean(this.isSigning);
    }
}
