package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3i;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class BlockEventPacket implements BedrockPacket {
    private Vector3i blockPosition;
    private Type eventType;
    private int eventData;

    @Override
    public void encode(ByteBuf buffer) {
        writeVector3i(buffer, blockPosition);
        writeInt(buffer, eventType.ordinal());
        writeInt(buffer, eventData);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // This packet isn't handled
    }

    public enum Type {
        NONE,
        CHEST
    }
}
