package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3f;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class RespawnPacket implements BedrockPacket {
    private Vector3f position;

    @Override
    public void encode(ByteBuf buffer) {
        writeVector3f(buffer, position);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Client bound only.
    }
}
