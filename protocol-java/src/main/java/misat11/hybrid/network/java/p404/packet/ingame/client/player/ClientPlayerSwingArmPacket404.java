package misat11.hybrid.network.java.p404.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerSwingArmPacket;

import java.io.IOException;

@Getter
public class ClientPlayerSwingArmPacket404 extends MinecraftPacket implements ClientPlayerSwingArmPacket {
    private Hand hand;

    @SuppressWarnings("unused")
    private ClientPlayerSwingArmPacket404() {
    }

    public ClientPlayerSwingArmPacket404(Hand hand) {
        this.hand = hand;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.hand = MagicValues404.key(Hand.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues404.value(Integer.class, this.hand));
    }
}
