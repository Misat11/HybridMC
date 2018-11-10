package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeRuntimeEntityId;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class ShowCreditsPacket implements BedrockPacket {
    private long runtimeEntityId;
    private Status status;

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeInt(buffer, status.ordinal());
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }

    public enum Status {
        START_CREDITS,
        END_CREDITS
    }
}
