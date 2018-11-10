package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class NetworkStackLatencyPacket implements BedrockPacket {
    long timestamp;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeLongLE(timestamp);
    }

    @Override
    public void decode(ByteBuf buffer) {
        timestamp = buffer.readLongLE();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
