package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class AddBehaviorTreePacket implements BedrockPacket {
    private String behaviorTreeJson;

    @Override
    public void encode(ByteBuf byteBuf) {

    }

    @Override
    public void decode(ByteBuf byteBuf) {

    }

    @Override
    public void handle(NetworkPacketHandler handler) {

    }
}
