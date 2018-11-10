package misat11.hybrid.inventory.transaction.action;

import static misat11.hybrid.network.util.VarInts.readInt;
import static misat11.hybrid.network.util.VarInts.writeInt;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CraftInventoryAction extends InventoryAction {
    public static final int TYPE_CRAFTING_ADD_INGREDIENT = -2;
    public static final int TYPE_CRAFTING_REMOVE_INGREDIENT = -2;
    public static final int TYPE_CRAFTING_RESULT = -2;
    public static final int TYPE_CRAFTING_USE_INGREDIENT = -2;
    public static final int TYPE_CONTAINER_DROP_CONTENTS = -100;
    private static final Type type = Type.CRAFT;
    private int inventoryId;

    @Override
    public void write(ByteBuf buffer){
        writeInt(buffer, inventoryId);
        super.write(buffer);
    }

    @Override
    public void read(ByteBuf buffer){
        inventoryId = readInt(buffer);
        super.read(buffer);
    }

    @Override
    public Type getType() {
        return type;
    }
}
