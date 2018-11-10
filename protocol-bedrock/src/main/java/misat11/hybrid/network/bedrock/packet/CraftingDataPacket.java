package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

public class CraftingDataPacket implements BedrockPacket {
    @Override
    public void encode(ByteBuf buffer) {
        //TODO: Rewrite this.
    }

    @Override
    public void decode(ByteBuf buffer) {

    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // This packet isn't handled
    }
}
