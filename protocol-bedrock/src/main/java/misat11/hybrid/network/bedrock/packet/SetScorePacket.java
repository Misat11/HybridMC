package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

// TODO add support
public class SetScorePacket implements BedrockPacket {

    @Override
    public void encode(ByteBuf buffer) {
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Client bound only
    }

    public enum Action {
        SET,
        REMOVE
    }
}
