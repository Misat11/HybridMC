package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.readString;
import static misat11.hybrid.network.util.VarInts.readUnsignedInt;

@Data
public class ModalFormResponsePacket implements BedrockPacket {
    private int formId;
    private String formData;

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        formId = readUnsignedInt(buffer);
        formData = readString(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
