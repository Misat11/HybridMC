package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class ContainerClosePacket implements BedrockPacket {
    private byte windowId;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeByte(windowId);
    }

    @Override
    public void decode(ByteBuf buffer) {
        windowId = buffer.readByte();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
