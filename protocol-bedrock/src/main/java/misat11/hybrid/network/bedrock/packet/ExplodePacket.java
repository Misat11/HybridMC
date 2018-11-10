package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import java.util.ArrayList;
import java.util.List;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeSignedBlockPosition;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3f;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

@Data
public class ExplodePacket implements BedrockPacket {
    private final List<Vector3i> records = new ArrayList<>();
    private Vector3f position;
    private float radius;

    @Override
    public void encode(ByteBuf buffer) {
        writeVector3f(buffer, position);
        buffer.writeFloatLE(radius);
        writeUnsignedInt(buffer, records.size());
        records.forEach(blockPos -> writeSignedBlockPosition(buffer, blockPos));
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
