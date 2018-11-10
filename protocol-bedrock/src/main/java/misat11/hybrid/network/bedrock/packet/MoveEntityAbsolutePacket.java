package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.util.Rotation;
import misat11.hybrid.util.bitset.IntBitSet;

@Data
public class MoveEntityAbsolutePacket implements BedrockPacket {
    private static final int FLAG_TELEPORTED = 0x01;
    private static final int FLAG_ON_GROUND = 0x02;
    private long runtimeEntityId;
    private final IntBitSet flags = new IntBitSet();
    private Vector3f position;
    private Rotation rotation;

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, runtimeEntityId);
        buffer.writeByte(flags.get());
        writeVector3f(buffer, position);
        writeByteRotation(buffer, rotation);
    }

    @Override
    public void decode(ByteBuf buffer) {
        runtimeEntityId = readRuntimeEntityId(buffer);
        flags.set(buffer.readByte());
        position = readVector3f(buffer);
        rotation = readByteRotation(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }

    public void setOnGround(boolean onGround) {
        flags.set(FLAG_ON_GROUND, onGround);
    }

    public boolean isOnGround() {
        return flags.get(FLAG_ON_GROUND);
    }

    public void setTeleported(boolean teleported) {
        flags.set(FLAG_TELEPORTED, teleported);
    }

    public boolean isTeleported() {
        return flags.get(FLAG_TELEPORTED);
    }
}
