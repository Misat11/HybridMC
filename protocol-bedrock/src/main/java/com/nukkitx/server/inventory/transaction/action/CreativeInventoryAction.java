package com.nukkitx.server.inventory.transaction.action;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreativeInventoryAction extends InventoryAction {
    private static final Type type = Type.CREATIVE;
    private int inventoryId;

    @Override
    public Type getType() {
        return type;
    }
}
