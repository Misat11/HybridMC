package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeRuntimeEntityId;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class TakeItemEntityPacket implements BedrockPacket {
    private long itemRuntimeEntityId;
    private long runtimeEntityId;

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, itemRuntimeEntityId);
        writeRuntimeEntityId(buffer, runtimeEntityId);
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
