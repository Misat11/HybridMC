package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.BedrockUtil;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class ScriptCustomEventPacket implements BedrockPacket {
    private String eventName;
    private String data;

    @Override
    public void encode(ByteBuf buffer) {
        BedrockUtil.writeString(buffer, eventName);
        BedrockUtil.writeString(buffer, data);
    }

    @Override
    public void decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Client bound only
    }
}

