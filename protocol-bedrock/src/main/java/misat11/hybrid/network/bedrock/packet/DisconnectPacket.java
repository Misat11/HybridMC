package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class DisconnectPacket implements BedrockPacket {
    private boolean disconnectScreenHidden;
    private String kickMessage;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeBoolean(disconnectScreenHidden);
        if (!disconnectScreenHidden) {
            writeString(buffer, kickMessage);
        }
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
