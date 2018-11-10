package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.bedrock.data.CommandOriginData;

@Data
public class CommandRequestPacket implements BedrockPacket {
    private String command;
    private CommandOriginData commandOriginData;
    private boolean internal;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, command);
        writeCommandOriginData(buffer, commandOriginData);
        buffer.writeBoolean(internal);
    }

    @Override
    public void decode(ByteBuf buffer) {
        command = readString(buffer);
        commandOriginData = readCommandOriginData(buffer);
        internal = buffer.readBoolean();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
