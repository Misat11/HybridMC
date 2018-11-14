package misat11.hybrid.network.java.p404.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues;
import misat11.hybrid.network.java.p404.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.p404.data.game.entity.player.PlayerAction;
import misat11.hybrid.network.java.p404.data.game.world.block.BlockFace;
import misat11.hybrid.network.java.p404.packet.MinecraftPacket;
import misat11.hybrid.network.java.p404.util.NetUtil;

import java.io.IOException;

public class ClientPlayerActionPacket extends MinecraftPacket {
    private PlayerAction action;
    private Position position;
    private BlockFace face;

    @SuppressWarnings("unused")
    private ClientPlayerActionPacket() {
    }

    public ClientPlayerActionPacket(PlayerAction action, Position position, BlockFace face) {
        this.action = action;
        this.position = position;
        this.face = face;
    }

    public PlayerAction getAction() {
        return this.action;
    }

    public Position getPosition() {
        return this.position;
    }

    public BlockFace getFace() {
        return this.face;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.action = MagicValues.key(PlayerAction.class, in.readVarInt());
        this.position = NetUtil.readPosition(in);
        this.face = MagicValues.key(BlockFace.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, this.action));
        NetUtil.writePosition(out, this.position);
        out.writeByte(MagicValues.value(Integer.class, this.face));
    }
}
