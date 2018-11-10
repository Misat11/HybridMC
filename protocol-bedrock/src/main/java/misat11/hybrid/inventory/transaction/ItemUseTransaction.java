package misat11.hybrid.inventory.transaction;

import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import misat11.hybrid.network.bedrock.BedrockUtil;
import misat11.hybrid.network.util.VarInts;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItemUseTransaction extends ComplexTransaction {
    private static final Type type = Type.ITEM_USE;
    private Action action;
    private Vector3i position;
    private int face;
    private Vector3f clickPosition;

    public void read(ByteBuf buffer){
        action = Action.values()[VarInts.readUnsignedInt(buffer)];
        position = BedrockUtil.readVector3i(buffer);
        face = VarInts.readInt(buffer);
        super.read(buffer);
        clickPosition = BedrockUtil.readVector3f(buffer);
    }

    public void write(ByteBuf buffer){
        VarInts.writeUnsignedInt(buffer, action.ordinal());
        BedrockUtil.writeVector3i(buffer, position);
        VarInts.writeInt(buffer, face);
        super.write(buffer);
        BedrockUtil.writeVector3f(buffer, clickPosition);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ItemUseTransaction" + super.toString() +
                ", action=" + action +
                ", position=" + position +
                ", face=" + face +
                ", clickPosition=" + clickPosition +
                ')';
    }

    public enum Action {
        PLACE,
        USE,
        BREAK
    }
}
