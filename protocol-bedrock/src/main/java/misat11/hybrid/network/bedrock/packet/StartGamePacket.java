package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.NukkitServer;
import misat11.hybrid.level.LevelSettings;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.util.GameMode;
import misat11.hybrid.util.Rotation;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class StartGamePacket implements BedrockPacket {
    private long uniqueEntityId;
    private long runtimeEntityId;
    private GameMode gamemode;
    private Vector3f playerPosition;
    private Rotation rotation;
    private LevelSettings levelSettings;
    private String levelId;
    private String worldName;
    private String premiumWorldTemplateId;
    private boolean trial;
    private long currentTick;
    private int enchantmentSeed;
    private String multiplayerCorrelationId;

    @Override
    public void encode(ByteBuf buffer) {
        writeUniqueEntityId(buffer, uniqueEntityId);
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeInt(buffer, gamemode.ordinal());
        writeVector3f(buffer, playerPosition);
        writeVector2f(buffer, rotation.getBodyRotation());
        writeLevelSettings(buffer, levelSettings);
        writeString(buffer, levelId);
        writeString(buffer, worldName);
        writeString(buffer, premiumWorldTemplateId);
        buffer.writeBoolean(trial);
        buffer.writeLongLE(currentTick);
        writeInt(buffer, enchantmentSeed);

        ByteBuf cachedPallete = NukkitServer.getPaletteManager().getCachedPallete();
        buffer.writeBytes(cachedPallete);
        writeString(buffer, multiplayerCorrelationId);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }
}
