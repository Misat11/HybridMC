package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class SetEntityLinkPacket implements BedrockPacket {
    //private EntityLink entityLink TODO: Implement

    @Override
    public void encode(ByteBuf buffer) {

    }

    @Override
    public void decode(ByteBuf buffer) {

    }

    @Override
    public void handle(NetworkPacketHandler handler) {

    }
}
