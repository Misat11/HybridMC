package misat11.hybrid.network.java.p404.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.EquipmentSlot;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityEquipmentPacket;

import java.io.IOException;

@Getter
public class ServerEntityEquipmentPacket404 extends MinecraftPacket implements ServerEntityEquipmentPacket {
    private int entityId;
    private EquipmentSlot slot;
    private ItemStack item;

    @SuppressWarnings("unused")
    private ServerEntityEquipmentPacket404() {
    }

    public ServerEntityEquipmentPacket404(int entityId, EquipmentSlot slot, ItemStack item) {
        this.entityId = entityId;
        this.slot = slot;
        this.item = item;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.slot = MagicValues404.key(EquipmentSlot.class, in.readVarInt());
        this.item = NetUtil404.readItem(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeVarInt(MagicValues404.value(Integer.class, this.slot));
        NetUtil404.writeItem(out, this.item);
    }
}
