package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3f;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class SpawnExperienceOrbPacket implements BedrockPacket {
    private Vector3f position;
    private int amount;

    @Override
    public void encode(ByteBuf buffer) {
        writeVector3f(buffer, position);
        writeInt(buffer, amount);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound
    }
}
