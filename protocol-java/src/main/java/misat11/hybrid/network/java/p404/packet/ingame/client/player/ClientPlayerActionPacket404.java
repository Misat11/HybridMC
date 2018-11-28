package misat11.hybrid.network.java.p404.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.PlayerAction;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockFace;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerActionPacket;

import java.io.IOException;

@Getter
public class ClientPlayerActionPacket404 extends MinecraftPacket implements ClientPlayerActionPacket {
    private PlayerAction action;
    private Position position;
    private BlockFace face;

    @SuppressWarnings("unused")
    private ClientPlayerActionPacket404() {
    }

    public ClientPlayerActionPacket404(PlayerAction action, Position position, BlockFace face) {
        this.action = action;
        this.position = position;
        this.face = face;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.action = getMagic().key(PlayerAction.class, in.readVarInt());
        this.position = getUtil().readPosition(in);
        this.face = getMagic().key(BlockFace.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.action));
        getUtil().writePosition(out, this.position);
        out.writeByte(getMagic().value(Integer.class, this.face));
    }
}
