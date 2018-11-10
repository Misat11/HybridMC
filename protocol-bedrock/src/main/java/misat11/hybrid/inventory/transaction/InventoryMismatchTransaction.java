package misat11.hybrid.inventory.transaction;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryMismatchTransaction extends SimpleTransaction {
    private static final Type type = Type.INVENTORY_MISMATCH;

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "InventoryMismatchTransaction" + super.toString();
    }
}
