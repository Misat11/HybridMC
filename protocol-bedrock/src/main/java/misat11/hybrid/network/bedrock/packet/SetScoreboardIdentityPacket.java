package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.Value;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.BedrockUtil;
import misat11.hybrid.network.util.VarInts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class SetScoreboardIdentityPacket implements BedrockPacket {
    private final List<Entry> entries = new ArrayList<>();
    private Type type;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeByte(type.ordinal());
        VarInts.writeUnsignedInt(buffer, entries.size());
        entries.forEach(entry -> {
            VarInts.writeLong(buffer, entry.scoreboardId);
            if (type == Type.ADD) {
                BedrockUtil.writeUuid(buffer, entry.uuid);
            }
        });
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    public enum Type {
        ADD,
        REMOVE
    }

    @Value
    public static class Entry {
        private final long scoreboardId;
        private final UUID uuid;
    }
}
