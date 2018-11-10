package com.nukkitx.server.inventory.transaction.action;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.nukkitx.server.network.util.VarInts.readUnsignedInt;
import static com.nukkitx.server.network.util.VarInts.writeUnsignedInt;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorldInteractionInventoryAction extends InventoryAction {
    private static final Type type = Type.WORLD_INTERACTION;
    private Action action;

    @Override
    public void write(ByteBuf buffer){
        writeUnsignedInt(buffer, action.ordinal());
        super.write(buffer);
    }

    @Override
    public void read(ByteBuf buffer) {
        action = Action.values()[readUnsignedInt(buffer)];
        super.read(buffer);
    }

    @Override
    public Type getType() {
        return type;
    }

    public enum Action {
        DROP_ITEM,
        PICKUP_ITEM
    }
}
