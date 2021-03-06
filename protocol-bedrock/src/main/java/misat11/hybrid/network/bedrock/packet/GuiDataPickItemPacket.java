package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class GuiDataPickItemPacket implements BedrockPacket {
    private String locale;
    private String popupMessage;
    private int hotbarSlot;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, locale);
        writeString(buffer, popupMessage);
        buffer.writeIntLE(hotbarSlot);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {

    }
}
