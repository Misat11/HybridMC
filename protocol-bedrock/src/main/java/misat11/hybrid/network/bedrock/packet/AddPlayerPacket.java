package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import misat11.hybrid.blockitems.ItemStack;
import misat11.hybrid.entity.EntityLink;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.bedrock.util.MetadataDictionary;
import misat11.hybrid.permission.CommandPermission;
import misat11.hybrid.permission.PlayerPermission;
import misat11.hybrid.util.Rotation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

@Data
public class AddPlayerPacket implements BedrockPacket {
    private UUID uuid;
    private String username;
    private long uniqueEntityId;
    private long runtimeEntityId;
    private String platformChatId;
    private Vector3f position;
    private Vector3f motion;
    private Rotation rotation;
    private ItemStack hand;
    private final MetadataDictionary metadata = new MetadataDictionary();
    private int flags;
    private CommandPermission commandPermission;
    private int flags2;
    private PlayerPermission playerPermission;
    private int customFlags;
    //private final AdventureSettings adventureSettings = new AdventureSettings;
    private final List<EntityLink> entityLinks = new ArrayList<>();
    private String deviceId;

    @Override
    public void encode(ByteBuf buffer) {
        writeUuid(buffer, uuid);
        writeString(buffer, username);
        writeUniqueEntityId(buffer, uniqueEntityId);
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeString(buffer, platformChatId);
        writeVector3f(buffer, position);
        writeVector3f(buffer, motion);
        writeRotation(buffer, rotation);
        writeItemInstance(buffer, hand);
        metadata.writeTo(buffer);
        writeUnsignedInt(buffer, flags);
        writeUnsignedInt(buffer, commandPermission.ordinal());
        writeUnsignedInt(buffer, flags2);
        writeUnsignedInt(buffer, playerPermission.ordinal());
        writeUnsignedInt(buffer, customFlags);
        buffer.writeLongLE(0);
        writeEntityLinks(buffer, entityLinks);
        writeString(buffer, deviceId);
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
