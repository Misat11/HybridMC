package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeUniqueEntityId;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3i;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class ContainerOpenPacket implements BedrockPacket {
    private byte windowId;
    private byte type;
    private Vector3i blockPosition;
    private long uniqueEntityId;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeByte(windowId);
        buffer.writeByte(type);
        writeVector3i(buffer, blockPosition);
        writeUniqueEntityId(buffer, uniqueEntityId);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // This packet isn't handled
    }
}
