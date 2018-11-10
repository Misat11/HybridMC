package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class PlayStatusPacket implements BedrockPacket {
    private Status status;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeInt(status.ordinal());
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound
    }

    public enum Status {
        LOGIN_SUCCESS,
        FAILED_CLIENT,
        FAILED_SERVER,
        PLAYER_SPAWN,
        FAILED_INVALID_TENANT,
        FAILED_VANILLA_EDU,
        FAILED_EDU_VANILLA,
        FAILED_SERVER_FULL
    }
}
