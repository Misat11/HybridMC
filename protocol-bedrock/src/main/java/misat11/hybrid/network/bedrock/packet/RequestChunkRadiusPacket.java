package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.util.VarInts.readInt;
import static misat11.hybrid.network.util.VarInts.writeInt;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class RequestChunkRadiusPacket implements BedrockPacket {
    private int radius;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, radius);
    }

    @Override
    public void decode(ByteBuf buffer) {
        radius = readInt(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
