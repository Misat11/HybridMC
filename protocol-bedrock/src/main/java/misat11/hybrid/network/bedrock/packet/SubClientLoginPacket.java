package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import io.netty.util.AsciiString;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.readLEAsciiString;
import static misat11.hybrid.network.util.VarInts.readUnsignedInt;

@Data
public class SubClientLoginPacket implements BedrockPacket {
    private AsciiString chainData;
    private AsciiString skinData;

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        ByteBuf jwt = buffer.readSlice(readUnsignedInt(buffer));
        chainData = readLEAsciiString(jwt);
        skinData = readLEAsciiString(jwt);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
