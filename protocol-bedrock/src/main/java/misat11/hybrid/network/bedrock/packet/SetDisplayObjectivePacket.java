package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class SetDisplayObjectivePacket implements BedrockPacket {
    private String slotName;
    private String name;
    private String displayName;
    private String criteriaName;
    private int order;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, slotName);
        writeString(buffer, name);
        writeString(buffer, displayName);
        writeString(buffer, criteriaName);
        writeInt(buffer, order);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Client bound only
    }
}
