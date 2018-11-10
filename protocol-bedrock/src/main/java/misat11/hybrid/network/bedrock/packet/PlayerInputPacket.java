package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.readVector2f;

import com.flowpowered.math.vector.Vector2f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class PlayerInputPacket implements BedrockPacket {
    private Vector2f inputMotion;
    private boolean unknown0;
    private boolean unknown1;

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        inputMotion = readVector2f(buffer);
        unknown0 = buffer.readBoolean();
        unknown1 = buffer.readBoolean();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
