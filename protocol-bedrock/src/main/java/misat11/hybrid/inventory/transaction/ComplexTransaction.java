package misat11.hybrid.inventory.transaction;

import com.flowpowered.math.vector.Vector3f;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import misat11.hybrid.blockitems.ItemStack;
import misat11.hybrid.inventory.transaction.action.InventoryAction;
import misat11.hybrid.network.bedrock.packet.UpdateBlockPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;
import static misat11.hybrid.network.util.VarInts.readInt;
import static misat11.hybrid.network.util.VarInts.writeInt;

@Data
@EqualsAndHashCode
public abstract class ComplexTransaction implements InventoryTransaction {
    private final long creationTime;
    private final List<InventoryAction> actions = new ArrayList<>();
    private int slot;
    private ItemStack item;
    private Vector3f fromPosition;

    public ComplexTransaction() {
        creationTime = System.currentTimeMillis();
    }

    public void read(ByteBuf buffer) {
        slot = readInt(buffer);
        item = readItemInstance(buffer);
        fromPosition = readVector3f(buffer);
    }

    public void write(ByteBuf buffer) {
        writeInt(buffer, slot);
        writeItemInstance(buffer, item);
        writeVector3f(buffer, fromPosition);
    }

    @Override
    public String toString() {
        return "(" +
                "type=" + getType() +
                ", records=" + Arrays.toString(getActions().toArray()) +
                ", creationTime=" + creationTime +
                ", slot=" + slot +
                ", item=" + item +
                ", fromPosition=" + fromPosition;
    }
}
