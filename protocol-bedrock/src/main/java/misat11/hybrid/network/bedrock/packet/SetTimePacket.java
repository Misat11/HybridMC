package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.util.VarInts.writeInt;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class SetTimePacket implements BedrockPacket {
    private int time;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, time);
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
