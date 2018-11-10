package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

@Data
public class ServerSettingsResponsePacket implements BedrockPacket {
    private int formId;
    private String formData;

    @Override
    public void encode(ByteBuf buffer) {
        writeUnsignedInt(buffer, formId);
        writeString(buffer, formData);
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
