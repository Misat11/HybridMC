package misat11.hybrid.inventory.transaction.action;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalInventoryAction extends InventoryAction {
    private static final Type type = Type.GLOBAL;

    @Override
    public Type getType() {
        return type;
    }
}
