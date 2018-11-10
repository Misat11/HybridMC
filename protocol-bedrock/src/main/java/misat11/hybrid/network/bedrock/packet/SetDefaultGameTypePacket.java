package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.util.GameMode;

import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class SetDefaultGameTypePacket implements BedrockPacket {
    private GameMode gamemode;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, gamemode.ordinal());
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Client bound only.
    }
}
