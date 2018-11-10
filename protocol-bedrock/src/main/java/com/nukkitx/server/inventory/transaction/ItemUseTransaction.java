package com.nukkitx.server.inventory.transaction;

import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;
import com.nukkitx.server.network.bedrock.BedrockUtil;
import com.nukkitx.server.network.util.VarInts;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
