package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class SetTitlePacket implements BedrockPacket {
    private Type type;
    private String text;
    private int fadeInTime;
    private int stayTime;
    private int fadeOutTime;

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, type.ordinal());
        writeString(buffer, text);
        writeInt(buffer, fadeInTime);
        writeInt(buffer, stayTime);
        writeInt(buffer, fadeOutTime);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }

    public enum Type {
        CLEAR_TITLE,
        RESET_TITLE,
        SET_TITLE,
        SET_SUBTITLE,
        SET_ACTIONBAR_MESSAGE,
        SET_ANIMATION_TIMES
    }
}
