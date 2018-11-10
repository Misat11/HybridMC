package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.util.data.BlockFace;

import static misat11.hybrid.network.bedrock.BedrockUtil.readRuntimeEntityId;
import static misat11.hybrid.network.bedrock.BedrockUtil.readVector3i;
import static misat11.hybrid.network.util.VarInts.readInt;

@Data
public class PlayerActionPacket implements BedrockPacket {
    private long runtimeEntityId;
    private Action action;
    private Vector3i blockPosition;
    private BlockFace face;

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        runtimeEntityId = readRuntimeEntityId(buffer);
        action = Action.values()[readInt(buffer)];
        blockPosition = readVector3i(buffer);
        face = BlockFace.values()[readInt(buffer)];
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }

    public enum Action {
        START_BREAK,
        ABORT_BREAK,
        STOP_BREAK,
        GET_UPDATED_BLOCK,
        DROP_ITEM,
        START_SLEEP,
        STOP_SLEEP,
        RESPAWN,
        JUMP,
        START_SPRINT,
        STOP_SPRINT,
        START_SNEAK,
        STOP_SNEAK,
        DIMENSION_CHANGE_REQUEST,
        DIMENSION_CHANGE_SUCCESS,
        START_GLIDE,
        STOP_GLIDE,
        BUILD_DENIED,
        CONTINUE_BREAK,
        CHANGE_SKIN,
        SET_ENCHANTMENT_SEED,
        START_SWIMMING,
        STOP_SWIMMING,
        START_SPIN_ATTACK,
        STOP_SPIN_ATTACK
    }
}
