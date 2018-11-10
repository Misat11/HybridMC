package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.blockitems.ItemStack;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.readItemInstance;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeItemInstance;
import static misat11.hybrid.network.util.VarInts.readUnsignedInt;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;


@Data
public class InventorySlotPacket implements BedrockPacket {
    private int windowId;
    private int inventorySlot;
    private ItemStack slot;

    @Override
    public void encode(ByteBuf buffer) {
        writeUnsignedInt(buffer, windowId);
        writeUnsignedInt(buffer, inventorySlot);
        writeItemInstance(buffer, slot);
    }

    @Override
    public void decode(ByteBuf buffer) {
        windowId = readUnsignedInt(buffer);
        inventorySlot = readUnsignedInt(buffer);
        slot = readItemInstance(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
