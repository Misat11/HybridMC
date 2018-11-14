package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues;
import misat11.hybrid.network.java.p404.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.p404.data.game.window.ClickItemParam;
import misat11.hybrid.network.java.p404.data.game.window.CreativeGrabParam;
import misat11.hybrid.network.java.p404.data.game.window.DropItemParam;
import misat11.hybrid.network.java.p404.data.game.window.FillStackParam;
import misat11.hybrid.network.java.p404.data.game.window.MoveToHotbarParam;
import misat11.hybrid.network.java.p404.data.game.window.ShiftClickItemParam;
import misat11.hybrid.network.java.p404.data.game.window.SpreadItemParam;
import misat11.hybrid.network.java.p404.data.game.window.WindowAction;
import misat11.hybrid.network.java.p404.data.game.window.WindowActionParam;
import misat11.hybrid.network.java.p404.packet.MinecraftPacket;
import misat11.hybrid.network.java.p404.util.NetUtil;

import java.io.IOException;

public class ClientWindowActionPacket extends MinecraftPacket {
    private int windowId;
    private int slot;
    private WindowActionParam param;
    private int actionId;
    private WindowAction action;
    private ItemStack clicked;

    @SuppressWarnings("unused")
    private ClientWindowActionPacket() {
    }

    public ClientWindowActionPacket(int windowId, int actionId, int slot, ItemStack clicked, WindowAction action, WindowActionParam param) {
        if((param == DropItemParam.LEFT_CLICK_OUTSIDE_NOT_HOLDING || param == DropItemParam.RIGHT_CLICK_OUTSIDE_NOT_HOLDING) && slot != -999) {
            throw new IllegalArgumentException("Slot must be -999 with param LEFT_CLICK_OUTSIDE_NOT_HOLDING or RIGHT_CLICK_OUTSIDE_NOT_HOLDING");
        }

        this.windowId = windowId;
        this.actionId = actionId;
        this.slot = slot;
        this.clicked = clicked;
        this.action = action;
        this.param = param;
    }

    public int getWindowId() {
        return this.windowId;
    }

    public int getActionId() {
        return this.actionId;
    }

    public int getSlot() {
        return this.slot;
    }

    public ItemStack getClickedItem() {
        return this.clicked;
    }

    public WindowAction getAction() {
        return this.action;
    }

    public WindowActionParam getParam() {
        return this.param;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readByte();
        this.slot = in.readShort();
        byte param = in.readByte();
        this.actionId = in.readShort();
        this.action = MagicValues.key(WindowAction.class, in.readByte());
        this.clicked = NetUtil.readItem(in);
        if(this.action == WindowAction.CLICK_ITEM) {
            this.param = MagicValues.key(ClickItemParam.class, param);
        } else if(this.action == WindowAction.SHIFT_CLICK_ITEM) {
            this.param = MagicValues.key(ShiftClickItemParam.class, param);
        } else if(this.action == WindowAction.MOVE_TO_HOTBAR_SLOT) {
            this.param = MagicValues.key(MoveToHotbarParam.class, param);
        } else if(this.action == WindowAction.CREATIVE_GRAB_MAX_STACK) {
            this.param = MagicValues.key(CreativeGrabParam.class, param);
        } else if(this.action == WindowAction.DROP_ITEM) {
            this.param = MagicValues.key(DropItemParam.class, param + (this.slot != -999 ? 2 : 0));
        } else if(this.action == WindowAction.SPREAD_ITEM) {
            this.param = MagicValues.key(SpreadItemParam.class, param);
        } else if(this.action == WindowAction.FILL_STACK) {
            this.param = MagicValues.key(FillStackParam.class, param);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeShort(this.slot);

        int param = MagicValues.value(Integer.class, this.param);
        if(this.action == WindowAction.DROP_ITEM) {
            param %= 2;
        }

        out.writeByte(param);
        out.writeShort(this.actionId);
        out.writeByte(MagicValues.value(Integer.class, this.action));
        NetUtil.writeItem(out, this.clicked);
    }
}
