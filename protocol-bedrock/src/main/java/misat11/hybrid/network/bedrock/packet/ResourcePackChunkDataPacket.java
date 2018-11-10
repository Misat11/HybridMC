package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;

import java.util.UUID;

@Data
public class ResourcePackChunkDataPacket implements BedrockPacket {
    private UUID packId;
    private int chunkIndex;
    private long progress;
    private byte[] data;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, packId.toString());
        buffer.writeIntLE(chunkIndex);
        buffer.writeLongLE(progress);
        buffer.writeIntLE(data.length);
        buffer.writeBytes(data);
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
