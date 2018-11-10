package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.level.GameRules;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeGameRules;

@Data
public class GameRulesChangedPacket implements BedrockPacket {
    private GameRules gameRules;

    @Override
    public void encode(ByteBuf buffer) {
        writeGameRules(buffer, gameRules);
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
