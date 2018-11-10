package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.readVector3i;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3i;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class LabTablePacket implements BedrockPacket {
    private byte unknownByte0;
    private Vector3i blockEntityPosition;
    private byte reactionType;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeByte(unknownByte0);
        writeVector3i(buffer, blockEntityPosition);
        buffer.writeByte(reactionType);
    }

    @Override
    public void decode(ByteBuf buffer) {
        unknownByte0 = buffer.readByte();
        blockEntityPosition = readVector3i(buffer);
        reactionType = buffer.readByte();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
