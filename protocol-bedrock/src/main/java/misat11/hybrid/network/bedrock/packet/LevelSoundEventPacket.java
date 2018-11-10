package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.level.SoundEvent;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.readVector3f;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3f;
import static misat11.hybrid.network.util.VarInts.readInt;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class LevelSoundEventPacket implements BedrockPacket {
    private SoundEvent soundEvent;
    private Vector3f position;
    private int extraData;
    private int pitch;
    private boolean unknown0;
    private boolean relativeVolumeDisabled;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeByte(soundEvent.ordinal());
        writeVector3f(buffer, position);
        writeInt(buffer, extraData);
        writeInt(buffer, pitch);
        buffer.writeBoolean(unknown0);
        buffer.writeBoolean(relativeVolumeDisabled);
    }

    @Override
    public void decode(ByteBuf buffer) {
        soundEvent = SoundEvent.values()[buffer.readByte()];
        position = readVector3f(buffer);
        extraData = readInt(buffer);
        pitch = readInt(buffer);
        unknown0 = buffer.readBoolean();
        relativeVolumeDisabled = buffer.readBoolean();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
