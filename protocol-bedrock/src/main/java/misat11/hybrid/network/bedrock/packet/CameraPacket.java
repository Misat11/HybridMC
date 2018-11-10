package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeUniqueEntityId;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class CameraPacket implements BedrockPacket {
    private long cameraUniqueEntityId;
    private long playerUniqueEntityId;

    @Override
    public void encode(ByteBuf buffer) {
        writeUniqueEntityId(buffer, cameraUniqueEntityId);
        writeUniqueEntityId(buffer, playerUniqueEntityId);
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
