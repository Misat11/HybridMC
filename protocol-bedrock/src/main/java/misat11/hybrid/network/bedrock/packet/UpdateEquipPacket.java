package misat11.hybrid.network.bedrock.packet;

import com.nukkitx.nbt.NBTEncodingType;
import com.nukkitx.nbt.stream.NBTOutputStream;
import com.nukkitx.nbt.tag.Tag;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.util.LittleEndianByteBufOutputStream;

import java.io.IOException;

import static misat11.hybrid.network.bedrock.BedrockUtil.writeUniqueEntityId;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
public class UpdateEquipPacket implements BedrockPacket {
    private byte windowId;
    private byte windowType;
    private int unknown0; // Couldn't find anything on this one. Looks like it isn't used?
    private long uniqueEntityId;
    private Tag<?> tag;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeByte(windowId);
        buffer.writeByte(windowType);
        writeInt(buffer, unknown0);
        writeUniqueEntityId(buffer, uniqueEntityId);
        try (NBTOutputStream writer = new NBTOutputStream(new LittleEndianByteBufOutputStream(buffer), NBTEncodingType.BEDROCK)) {
            writer.write(tag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }
}
