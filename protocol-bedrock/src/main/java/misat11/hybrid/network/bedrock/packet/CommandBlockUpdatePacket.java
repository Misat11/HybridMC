package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;
import static misat11.hybrid.network.util.VarInts.readUnsignedInt;

@Data
public class CommandBlockUpdatePacket implements BedrockPacket {
    private boolean block;
    private Vector3i blockPosition;
    private int commandBlockMode;
    private boolean redstoneMode;
    private boolean conditional;
    private long minecartRuntimeEntityId;
    private String command;
    private String lastOutput;
    private String name;
    private boolean outputTracked;

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        block = buffer.readBoolean();
        if (block) {
            blockPosition = readVector3i(buffer);
            commandBlockMode = readUnsignedInt(buffer);
            redstoneMode = buffer.readBoolean();
            conditional = buffer.readBoolean();
        } else {
            minecartRuntimeEntityId = readRuntimeEntityId(buffer);
        }

        command = readString(buffer);
        lastOutput = readString(buffer);
        name = readString(buffer);
        outputTracked = buffer.readBoolean();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
