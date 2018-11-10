package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.util.VarInts.writeUnsignedLong;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateBlockSyncedPacket extends UpdateBlockPacket {
    private long runtimeEntityId;
    private long unknownLong1;

    @Override
    public void encode(ByteBuf buffer) {
        super.encode(buffer);
        writeUnsignedLong(buffer, runtimeEntityId);
        writeUnsignedLong(buffer, unknownLong1);
    }

    @Override
    public void decode(ByteBuf buffer) {
        super.decode(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Client bound only
    }
}
