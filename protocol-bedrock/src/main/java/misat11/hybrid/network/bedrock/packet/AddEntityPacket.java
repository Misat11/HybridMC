package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.entity.Attribute;
import misat11.hybrid.entity.EntityLink;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.bedrock.util.MetadataDictionary;
import misat11.hybrid.util.Rotation;

import java.util.ArrayList;
import java.util.List;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

@Data
public class AddEntityPacket implements BedrockPacket {
    private long uniqueEntityId;
    private long runtimeEntityId;
    private int entityType;
    private Vector3f position;
    private Vector3f motion;
    private Rotation rotation;
    private final List<Attribute> entityAttributes = new ArrayList<>();
    private final MetadataDictionary metadata = new MetadataDictionary();
    private final List<EntityLink> links = new ArrayList<>();

    @Override
    public void encode(ByteBuf buffer) {
        writeUniqueEntityId(buffer, uniqueEntityId);
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeUnsignedInt(buffer, entityType);
        writeVector3f(buffer, position);
        writeVector3f(buffer, motion);
        writeRotation(buffer, rotation);
        writeEntityAttributes(buffer, entityAttributes);
        metadata.writeTo(buffer);
        writeEntityLinks(buffer, links);
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
