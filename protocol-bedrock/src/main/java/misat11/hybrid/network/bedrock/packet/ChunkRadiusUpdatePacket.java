package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.util.VarInts.writeInt;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class ChunkRadiusUpdatePacket implements BedrockPacket {
    private int radius;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, radius);
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
