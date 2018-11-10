package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.readVector3i;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3i;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class ItemFrameDropItemPacket implements BedrockPacket {
    private Vector3i blockPosition;

    @Override
    public void encode(ByteBuf buffer) {
        writeVector3i(buffer, blockPosition);
    }

    @Override
    public void decode(ByteBuf buffer) {
        blockPosition = readVector3i(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
