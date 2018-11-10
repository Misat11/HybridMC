package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.util.GameMode;

import static misat11.hybrid.network.util.VarInts.readInt;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class SetPlayerGameTypePacket implements BedrockPacket {
    private GameMode gamemode;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, gamemode.ordinal());
    }

    @Override
    public void decode(ByteBuf buffer) {
        gamemode = GameMode.parse(readInt(buffer));
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
