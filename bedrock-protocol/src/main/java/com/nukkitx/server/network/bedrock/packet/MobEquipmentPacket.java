package com.nukkitx.server.network.bedrock.packet;

import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import static com.nukkitx.server.network.bedrock.BedrockUtil.*;
import misat11.hybrid.blockitems.ItemStack;

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
