package misat11.hybrid.inventory.transaction.action;

import static misat11.hybrid.network.util.VarInts.readInt;
import static misat11.hybrid.network.util.VarInts.writeInt;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContainerInventoryAction extends InventoryAction {
    private static final Type type = Type.CONTAINER;
    private int inventoryId;

    @Override
    public void write(ByteBuf buffer) {
        writeInt(buffer, inventoryId);
        super.write(buffer);
    }

    @Override
    public void read(ByteBuf buffer) {
        inventoryId = readInt(buffer);
        super.read(buffer);
    }

    @Override
    public Type getType() {
        return type;
    }
}
