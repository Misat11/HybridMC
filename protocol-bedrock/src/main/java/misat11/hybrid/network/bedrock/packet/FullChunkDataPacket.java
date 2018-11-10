package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.util.VarInts.writeInt;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
@ToString(exclude = {"data"})
@EqualsAndHashCode(exclude = {"data"})
public class FullChunkDataPacket implements BedrockPacket {
    private int chunkX;
    private int chunkZ;
    private byte[] data;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, chunkX);
        writeInt(buffer, chunkZ);
        writeUnsignedInt(buffer, data.length);
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
