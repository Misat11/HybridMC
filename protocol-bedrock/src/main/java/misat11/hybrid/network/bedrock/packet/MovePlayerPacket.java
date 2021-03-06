package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.util.Rotation;

@Data
public class MovePlayerPacket implements BedrockPacket {
    private long runtimeEntityId;
    private Vector3f position;
    private Rotation rotation;
    private Mode mode;
    private boolean onGround;
    private long ridingRuntimeEntityId;
    private TeleportationCause teleportationCause;
    private int unknown0;

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeVector3f(buffer, position);
        writeRotation(buffer, rotation);
        buffer.writeByte(mode.ordinal());
        buffer.writeBoolean(onGround);
        writeRuntimeEntityId(buffer, ridingRuntimeEntityId);
        if (mode == Mode.TELEPORT) {
            buffer.writeIntLE(teleportationCause.ordinal());
            buffer.writeIntLE(unknown0);
        }
    }

    @Override
    public void decode(ByteBuf buffer) {
        runtimeEntityId = readRuntimeEntityId(buffer);
        position = readVector3f(buffer);
        rotation = readRotation(buffer);
        mode = Mode.values()[buffer.readByte()];
        onGround = buffer.readBoolean();
        ridingRuntimeEntityId = readRuntimeEntityId(buffer);
        if (mode == Mode.TELEPORT) {
            teleportationCause = TeleportationCause.values()[buffer.readIntLE()];
            unknown0 = buffer.readIntLE();
        }
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }

    public enum Mode {
        NORMAL,
        RESET,
        TELEPORT,
        ROTATION
    }

    public enum TeleportationCause {
        UNKNOWN,
        PROJECTILE,
        CHORUS_FRUIT,
        COMMAND,
        BEHAVIOR,
        COUNT
    }
}
