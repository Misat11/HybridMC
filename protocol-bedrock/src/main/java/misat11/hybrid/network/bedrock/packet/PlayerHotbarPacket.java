package misat11.hybrid.network.bedrock.packet;

import misat11.hybrid.blockitems.ItemStack;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import static misat11.hybrid.network.util.VarInts.readUnsignedInt;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlayerHotbarPacket implements BedrockPacket {
    private final List<ItemStack> items = new ArrayList<>();
    private int selectedHotbarSlot;
    private byte windowId;
    private boolean selectHotbarSlot;

    @Override
    public void encode(ByteBuf buffer) {
        writeUnsignedInt(buffer, selectedHotbarSlot);
        buffer.writeByte(windowId);
    }

    @Override
    public void decode(ByteBuf buffer) {
        selectedHotbarSlot = readUnsignedInt(buffer);
        windowId = buffer.readByte();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
