package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

public class ResourcePackStackPacket implements BedrockPacket {

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeBoolean(false);
        buffer.writeShortLE(0);
        buffer.writeShortLE(0);
        // TODO add resourcepack support
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound
    }
}
