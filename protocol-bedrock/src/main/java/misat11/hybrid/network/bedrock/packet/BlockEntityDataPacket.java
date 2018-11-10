package misat11.hybrid.network.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;
import com.nukkitx.nbt.NBTEncodingType;
import com.nukkitx.nbt.stream.NBTInputStream;
import com.nukkitx.nbt.stream.NBTOutputStream;
import com.nukkitx.nbt.tag.Tag;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.util.LittleEndianByteBufInputStream;
import misat11.hybrid.network.util.LittleEndianByteBufOutputStream;

import static misat11.hybrid.network.bedrock.BedrockUtil.readVector3i;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeVector3i;

import java.io.IOException;

@Data
public class BlockEntityDataPacket implements BedrockPacket {
    private Vector3i blockPostion;
    private Tag<?> data;

    @Override
    public void encode(ByteBuf buffer) {
        writeVector3i(buffer, blockPostion);
        try (NBTOutputStream writer = new NBTOutputStream(new LittleEndianByteBufOutputStream(buffer), NBTEncodingType.BEDROCK)) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void decode(ByteBuf buffer) {
        blockPostion = readVector3i(buffer);
        try (NBTInputStream reader = new NBTInputStream(new LittleEndianByteBufInputStream(buffer), NBTEncodingType.BEDROCK)) {
            data = reader.readTag();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
