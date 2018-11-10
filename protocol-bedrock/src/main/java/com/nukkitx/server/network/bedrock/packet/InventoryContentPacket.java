package com.nukkitx.server.network.bedrock.packet;

import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.Arrays;

import static com.nukkitx.server.network.bedrock.BedrockUtil.readItemInstance;
import static com.nukkitx.server.network.bedrock.BedrockUtil.writeItemInstance;
import static com.nukkitx.server.network.util.VarInts.readUnsignedInt;
import static com.nukkitx.server.network.util.VarInts.writeUnsignedInt;
import misat11.hybrid.blockitems.ItemStack;

@Data
public class InventoryContentPacket implements BedrockPacket {
    private int windowId;
    private ItemStack[] items;

    @Override
    public void encode(ByteBuf buffer) {
        writeUnsignedInt(buffer, windowId);
        writeUnsignedInt(buffer, items.length);
        for (ItemStack item : items) {
            writeItemInstance(buffer, item);
        }
    }

    @Override
    public void decode(ByteBuf buffer) {
        windowId = readUnsignedInt(buffer);
        int itemSize = readUnsignedInt(buffer);
        items = new ItemStack[itemSize];
        for (int i = 0; i < itemSize; i++) {
            items[i] = readItemInstance(buffer);
        }
    }

    public void setItems(ItemStack[] items) {
        this.items = Arrays.copyOf(items, items.length);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
