package misat11.hybrid.network.bedrock.packet;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.blockitems.ItemStack;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

@Data
public class MobArmorEquipmentPacket implements BedrockPacket {
    private long runtimeEntityId;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    @Override
    public void encode(ByteBuf buffer) {
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeItemInstance(buffer, helmet);
        writeItemInstance(buffer, chestplate);
        writeItemInstance(buffer, leggings);
        writeItemInstance(buffer, boots);
    }

    @Override
    public void decode(ByteBuf buffer) {
        runtimeEntityId = readRuntimeEntityId(buffer);
        helmet = readItemInstance(buffer);
        chestplate = readItemInstance(buffer);
        leggings = readItemInstance(buffer);
        boots = readItemInstance(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
