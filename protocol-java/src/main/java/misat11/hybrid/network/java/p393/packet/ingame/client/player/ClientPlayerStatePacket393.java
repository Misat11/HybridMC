package misat11.hybrid.network.java.p393.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.PlayerState;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerStatePacket;

import java.io.IOException;

@Getter
public class ClientPlayerStatePacket393 extends MinecraftPacket implements ClientPlayerStatePacket {
    private int entityId;
    private PlayerState state;
    private int jumpBoost;

    @SuppressWarnings("unused")
    private ClientPlayerStatePacket393() {
    }

    public ClientPlayerStatePacket393(int entityId, PlayerState state) {
        this(entityId, state, 0);
    }

    public ClientPlayerStatePacket393(int entityId, PlayerState state, int jumpBoost) {
        this.entityId = entityId;
        this.state = state;
        this.jumpBoost = jumpBoost;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.state = getMagic().key(PlayerState.class, in.readVarInt());
        this.jumpBoost = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeVarInt(getMagic().value(Integer.class, this.state));
        out.writeVarInt(this.jumpBoost);
    }
}
