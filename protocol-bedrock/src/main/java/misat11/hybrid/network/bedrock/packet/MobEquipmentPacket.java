package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.blockitems.ItemStack;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class MobEquipmentPacket implements BedrockPacket {
    private long runtimeEntityId;
    private ItemStack item;
    private byte inventorySlot;
    private byte hotbarSlot;
    private byte windowId;

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeItemInstance(buffer, item);
        buffer.writeByte(inventorySlot);
        buffer.writeByte(hotbarSlot);
        buffer.writeByte(windowId);
    }

    @Override
    public void decode(ByteBuf buffer) {
        runtimeEntityId = readRuntimeEntityId(buffer);
        item = readItemInstance(buffer);
        inventorySlot = buffer.readByte();
        hotbarSlot = buffer.readByte();
        windowId = buffer.readByte();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
