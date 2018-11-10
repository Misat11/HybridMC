package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.bedrock.annotations.NoEncryption;

@Data
@NoEncryption // This is sent in plain text to complete the Diffie Hellman key exchange.
public class ServerToClientHandshakePacket implements BedrockPacket {
    private String jwt;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, jwt);
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
