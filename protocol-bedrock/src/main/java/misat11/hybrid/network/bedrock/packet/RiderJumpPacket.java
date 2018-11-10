package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.util.VarInts.readInt;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class RiderJumpPacket implements BedrockPacket {
    private int unknown0;
    /*
    Possible the jump boost bar?
    If the value is > 0. Set it to 0
    If the value is =< 90. Set it to 106535321 (wtf?)
     */

    @Override
    public void encode(ByteBuf buffer) {
        writeUnsignedInt(buffer, unknown0);
    }

    @Override
    public void decode(ByteBuf buffer) {
        unknown0 = readInt(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
