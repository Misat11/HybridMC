package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import io.netty.util.AsciiString;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.readLEAsciiString;
import static misat11.hybrid.network.util.VarInts.readUnsignedInt;

@Data
@EqualsAndHashCode(exclude = {"skinData"})
@ToString(exclude = {"chainData", "skinData"})
public class LoginPacket implements BedrockPacket {
    private int protocolVersion;
    // Base64 strings so we only need Ascii characters.
    private AsciiString chainData;
    private AsciiString skinData;

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        protocolVersion = buffer.readInt();

        ByteBuf jwt = buffer.readSlice(readUnsignedInt(buffer)); // Get the JWT.
        chainData = readLEAsciiString(jwt);
        skinData = readLEAsciiString(jwt);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
