package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3f;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class ChangeDimensionPacket implements BedrockPacket {
    private int dimension;
    private Vector3f position;
    private boolean respawn;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, dimension);
        writeVector3f(buffer, position);
        buffer.writeBoolean(respawn);
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
