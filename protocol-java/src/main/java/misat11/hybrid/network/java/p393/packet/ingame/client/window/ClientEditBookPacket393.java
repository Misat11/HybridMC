package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientEditBookPacket;

import java.io.IOException;

@Getter
public class ClientEditBookPacket393 extends MinecraftPacket implements ClientEditBookPacket {
    private ItemStack book;
    private boolean isSigning;

    @SuppressWarnings("unused")
    private ClientEditBookPacket393() {
    }

    public ClientEditBookPacket393(ItemStack book, boolean isSigning, Hand hand) {
        this.book = book;
        this.isSigning = isSigning;
    }

    public boolean getIsSigning() {
        return this.isSigning;
    }
    
    public Hand getHand() {
    	return Hand.MAIN_HAND;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.book = getUtil().readItem(in);
        this.isSigning = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writeItem(out, this.book);
        out.writeBoolean(this.isSigning);
    }
}
