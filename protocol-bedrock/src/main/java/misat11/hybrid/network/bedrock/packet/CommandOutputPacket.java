package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.bedrock.data.CommandOriginData;
import misat11.hybrid.network.bedrock.data.CommandOutputMessage;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeCommandOriginData;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommandOutputPacket implements BedrockPacket {
    private final List<CommandOutputMessage> outputMessages = new ArrayList<>();
    private CommandOriginData commandOriginData;
    private byte outputType;
    private int successCount;

    @Override
    public void encode(ByteBuf buffer) {
        writeCommandOriginData(buffer, commandOriginData);
        buffer.writeByte(outputType);

    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // This packet isn't handled
    }
}
