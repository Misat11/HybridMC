package misat11.hybrid.network.java.p401.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientEditBookPacket;

import java.io.IOException;

@Getter
public class ClientEditBookPacket401 extends MinecraftPacket implements ClientEditBookPacket {
    private ItemStack book;
    private boolean isSigning;
    private Hand hand;

    @SuppressWarnings("unused")
    private ClientEditBookPacket401() {
    }

    public ClientEditBookPacket401(ItemStack book, boolean isSigning, Hand hand) {
        this.book = book;
        this.isSigning = isSigning;
        this.hand = hand;
    }

    public boolean getIsSigning() {
        return this.isSigning;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.book = getUtil().readItem(in);
        this.isSigning = in.readBoolean();
        this.hand = getMagic().key(Hand.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writeItem(out, this.book);
        out.writeBoolean(this.isSigning);
        out.writeVarInt(getMagic().value(Integer.class, this.hand));
    }
}
