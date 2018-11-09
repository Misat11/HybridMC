package com.nukkitx.server.network.bedrock.packet;

import misat11.hybrid.blockitems.ItemStack;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.nukkitx.server.network.bedrock.BedrockUtil.readItemInstance;
import static com.nukkitx.server.network.bedrock.BedrockUtil.readUuid;
import static com.nukkitx.server.network.util.VarInts.readInt;
import static com.nukkitx.server.network.util.VarInts.readUnsignedInt;

@Data
public class CraftingEventPacket implements BedrockPacket {
    private final List<ItemStack> input = new ArrayList<>();
    private final List<ItemStack> output = new ArrayList<>();
    private byte windowId;
    private Type type;
    private UUID uuid;

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        windowId = buffer.readByte();
        type = Type.values()[readInt(buffer)];
        uuid = readUuid(buffer);

        int inputCount = readUnsignedInt(buffer);
        for (int i = 0; i < inputCount; i++) {
            input.add(readItemInstance(buffer));
        }

        int outputCount = readUnsignedInt(buffer);
        for (int i = 0; i < outputCount; i++) {
            output.add(readItemInstance(buffer));
        }
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }

    public enum Type {
        SHAPELESS,
        SHAPED,
        FURNACE,
        FURNACE_DATA,
        MULTI,
        SHULKER_BOX
    }
}
