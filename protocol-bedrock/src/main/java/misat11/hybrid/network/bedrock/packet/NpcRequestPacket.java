package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeRuntimeEntityId;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class NpcRequestPacket implements BedrockPacket {
    private long runtimeEntityId;
    private Type requestType;
    private String command;
    private byte actionType;

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, runtimeEntityId);
        buffer.writeByte(requestType.ordinal());
        writeString(buffer, command);
        buffer.writeByte(actionType);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }

    public enum Type {
        // TODO: Didn't really look too far into this.
        SET_ACTION,
        EXECUTE_COMMAND_ACTION,
        EXECUTE_CLOSING_COMMANDS
    }
}
