package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeRuntimeEntityId;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class MobEffectPacket implements BedrockPacket {
    private long runtimeEntityId;
    private Event event;
    private int effectId;
    private int amplifier;
    private boolean particles;
    private int duration;

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, runtimeEntityId);
        buffer.writeByte(event.ordinal());
        writeInt(buffer, effectId);
        writeInt(buffer, amplifier);
        buffer.writeBoolean(particles);
        writeInt(buffer, duration);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }

    public enum Event {
        NONE,
        ADD,
        MODIFY,
        REMOVE,
    }
}
