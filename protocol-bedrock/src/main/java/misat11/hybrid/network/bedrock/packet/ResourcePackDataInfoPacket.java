package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import java.util.UUID;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

@Data
public class ResourcePackDataInfoPacket implements BedrockPacket {
    private UUID packId;
    private int maxChunkSize;
    private int chunkCount;
    private int compressedPackSize;
    private byte[] hash;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, packId.toString());
        buffer.writeIntLE(maxChunkSize);
        buffer.writeIntLE(chunkCount);
        buffer.writeLongLE(compressedPackSize);
        writeUnsignedInt(buffer, hash.length);
        buffer.writeBytes(hash);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only clientbound
    }
}
