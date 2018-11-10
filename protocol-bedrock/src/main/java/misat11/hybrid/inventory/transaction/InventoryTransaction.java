package misat11.hybrid.inventory.transaction;


import io.netty.buffer.ByteBuf;
import misat11.hybrid.inventory.transaction.action.InventoryAction;

import java.util.Collection;

public interface InventoryTransaction {

    long getCreationTime();

    Collection<InventoryAction> getActions();

    void read(ByteBuf buffer);

    void write(ByteBuf buffer);

    Type getType();

    enum Type {
        NORMAL,
        INVENTORY_MISMATCH,
        ITEM_USE,
        ITEM_USE_ON_ENTITY,
        ITEM_RELEASE
    }
}
