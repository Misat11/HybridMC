package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.BedrockUtil;
import misat11.hybrid.network.util.VarInts;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateSoftEnumPacket implements BedrockPacket {
    private final List<String> values = new ArrayList<>();
    private String enumName;
    private Type type;

    @Override
    public void encode(ByteBuf buffer) {
        BedrockUtil.writeString(buffer, enumName);
        VarInts.writeUnsignedInt(buffer, values.size());
        values.forEach(s -> BedrockUtil.writeString(buffer, s));
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    public enum Type {
        ADD,
        REMOVE,
        UPDATE
    }
}
