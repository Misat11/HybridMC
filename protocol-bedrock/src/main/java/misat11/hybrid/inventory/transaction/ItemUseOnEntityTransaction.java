package misat11.hybrid.inventory.transaction;

import com.flowpowered.math.vector.Vector3f;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static misat11.hybrid.network.bedrock.BedrockUtil.*;
import static misat11.hybrid.network.util.VarInts.readUnsignedInt;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItemUseOnEntityTransaction extends ComplexTransaction {
    private static final Type type = Type.ITEM_USE_ON_ENTITY;
    private long runtimeEntityId;
    private Action action;
    private Vector3f clickPosition;
    
    @Override
    public void read(ByteBuf buffer){
        runtimeEntityId = readRuntimeEntityId(buffer);
        action = Action.values()[readUnsignedInt(buffer)];
        super.read(buffer);
        clickPosition = readVector3f(buffer);
    }

    @Override
    public void write(ByteBuf buffer){
        writeRuntimeEntityId(buffer, runtimeEntityId);
        writeUnsignedInt(buffer, action.ordinal());
        super.write(buffer);
        writeVector3f(buffer, clickPosition);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ItemUseOnEntityTransaction" + super.toString() +
                ", runtimeEntityId=" + runtimeEntityId +
                ", action=" + action +
                ", clickPosition=" + clickPosition +
                ')';
    }

    public enum Action {
        INTERACT,
        ATTACK,
        ITEM_INTERACT
    }
}
