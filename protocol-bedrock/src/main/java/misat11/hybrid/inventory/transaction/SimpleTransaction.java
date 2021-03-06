package misat11.hybrid.inventory.transaction;

import io.netty.buffer.ByteBuf;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import misat11.hybrid.inventory.transaction.action.InventoryAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@EqualsAndHashCode
public abstract class SimpleTransaction implements InventoryTransaction {
    private final long creationTime;
    private final List<InventoryAction> actions = new ArrayList<>();

    public SimpleTransaction() {
        creationTime = System.currentTimeMillis();
    }

    @Override
    public void read(ByteBuf buffer) {
    }

    @Override
    public void write(ByteBuf buffer) {
    }

    @Override
    public String toString() {
        return "(" +
                "type=" + getType() +
                ", records=" + Arrays.toString(getActions().toArray()) +
                ", creationTime=" + creationTime +
                ')';
    }
}
