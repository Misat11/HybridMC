package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.entity.Attribute;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writePlayerAttributes;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeRuntimeEntityId;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateAttributesPacket implements BedrockPacket {
    private long runtimeEntityId;
    private List<Attribute> attributes = new ArrayList<>();

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writePlayerAttributes(buffer, attributes);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }
}
