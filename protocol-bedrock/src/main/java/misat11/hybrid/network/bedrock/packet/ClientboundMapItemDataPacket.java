package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class ClientboundMapItemDataPacket implements BedrockPacket {
    @Override
    public void encode(ByteBuf buffer) {
        // TODO: Rewrite
    }

    @Override
    public void decode(ByteBuf buffer) {

    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // This packet isn't handled
    }
}
