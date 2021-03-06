package misat11.hybrid.network.bedrock.packet;

import com.google.common.base.Preconditions;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.Value;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.readString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ResourcePackClientResponsePacket implements BedrockPacket {
    private final List<Entry> entries = new ArrayList<>();
    private Status status;

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        status = Status.values()[buffer.readByte()];

        int packIdsCount = buffer.readShortLE();
        for (int i = 0; i < packIdsCount; i++) {
            String[] split = readString(buffer).split("_");
            Preconditions.checkArgument(split.length == 2);
            entries.add(new Entry(UUID.fromString(split[0]), split[1]));
        }
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }

    public enum Status {
        NONE,
        REFUSED,
        SEND_PACKS,
        HAVE_ALL_PACKS,
        COMPLETED
    }

    @Value
    public static class Entry {
        private final UUID uuid;
        private String version;
    }
}
