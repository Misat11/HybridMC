package com.nukkitx.server.inventory.transaction;


import com.nukkitx.server.inventory.transaction.action.InventoryAction;
import io.netty.buffer.ByteBuf;

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
