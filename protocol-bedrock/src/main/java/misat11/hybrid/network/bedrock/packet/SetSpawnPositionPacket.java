package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3i;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class SetSpawnPositionPacket implements BedrockPacket {
    private Type spawnType;
    private Vector3i blockPosition;
    private boolean spawnForced;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, spawnType.ordinal());
        writeVector3i(buffer, blockPosition);
        buffer.writeBoolean(spawnForced);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }

    public enum Type {
        PLAYER_SPAWN,
        WORLD_SPAWN
    }
}
