package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeUniqueEntityId;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class RemoveEntityPacket implements BedrockPacket {
    private long uniqueEntityId;

    @Override
    public void encode(ByteBuf buffer) {
        writeUniqueEntityId(buffer, uniqueEntityId);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }
}
