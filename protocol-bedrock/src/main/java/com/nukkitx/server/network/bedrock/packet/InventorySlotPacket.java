package com.nukkitx.server.network.bedrock.packet;

import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.blockitems.ItemStack;

import static com.nukkitx.server.network.bedrock.BedrockUtil.readItemInstance;
import static com.nukkitx.server.network.bedrock.BedrockUtil.writeItemInstance;
import static com.nukkitx.server.network.util.VarInts.readUnsignedInt;
import static com.nukkitx.server.network.util.VarInts.writeUnsignedInt;


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
