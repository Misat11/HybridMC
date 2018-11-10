package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3i;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class PlaySoundPacket implements BedrockPacket {
    private String sound;
    private Vector3i blockPosition;
    private float volume;
    private float pitch;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, sound);
        writeVector3i(buffer, blockPosition);
        buffer.writeFloatLE(volume);
        buffer.writeFloatLE(pitch);
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
