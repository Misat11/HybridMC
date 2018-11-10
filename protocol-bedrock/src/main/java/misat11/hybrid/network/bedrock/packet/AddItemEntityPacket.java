package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;

import com.flowpowered.math.vector.Vector3f;
import misat11.hybrid.blockitems.ItemStack;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.bedrock.util.MetadataDictionary;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class AddItemEntityPacket implements BedrockPacket {
    private final MetadataDictionary metadata = new MetadataDictionary();
    private long uniqueEntityId;
    private long runtimeEntityId;
    private ItemStack itemInstance;
    private Vector3f position;
    private Vector3f motion;
    private boolean fishing;

    @Override
    public void encode(ByteBuf buffer) {
        writeUniqueEntityId(buffer, uniqueEntityId);
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeItemInstance(buffer, itemInstance);
        writeVector3f(buffer, position);
        writeVector3f(buffer, motion);
        buffer.writeBoolean(fishing);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // This packet isn't handled
    }
}
