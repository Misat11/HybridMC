package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.ItemStack;
import misat11.hybrid.network.java.pabstract.data.game.window.ClickItemParam;
import misat11.hybrid.network.java.pabstract.data.game.window.CreativeGrabParam;
import misat11.hybrid.network.java.pabstract.data.game.window.DropItemParam;
import misat11.hybrid.network.java.pabstract.data.game.window.FillStackParam;
import misat11.hybrid.network.java.pabstract.data.game.window.MoveToHotbarParam;
import misat11.hybrid.network.java.pabstract.data.game.window.ShiftClickItemParam;
import misat11.hybrid.network.java.pabstract.data.game.window.SpreadItemParam;
import misat11.hybrid.network.java.pabstract.data.game.window.WindowAction;
import misat11.hybrid.network.java.pabstract.data.game.window.WindowActionParam;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientWindowActionPacket;

import java.io.IOException;

@Getter
public class ClientWindowActionPacket404 extends MinecraftPacket implements ClientWindowActionPacket {
    private int windowId;
    private int slot;
    private WindowActionParam param;
    private int actionId;
    private WindowAction action;
    private ItemStack clickedItem;

    @SuppressWarnings("unused")
    private ClientWindowActionPacket404() {
    }

    public ClientWindowActionPacket404(int windowId, int actionId, int slot, ItemStack clickedItem, WindowAction action, WindowActionParam param) {
        if((param == DropItemParam.LEFT_CLICK_OUTSIDE_NOT_HOLDING || param == DropItemParam.RIGHT_CLICK_OUTSIDE_NOT_HOLDING) && slot != -999) {
            throw new IllegalArgumentException("Slot must be -999 with param LEFT_CLICK_OUTSIDE_NOT_HOLDING or RIGHT_CLICK_OUTSIDE_NOT_HOLDING");
        }

        this.windowId = windowId;
        this.actionId = actionId;
        this.slot = slot;
        this.clickedItem = clickedItem;
        this.action = action;
        this.param = param;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readByte();
        this.slot = in.readShort();
        byte param = in.readByte();
        this.actionId = in.readShort();
        this.action = MagicValues404.key(WindowAction.class, in.readByte());
        this.clickedItem = NetUtil404.readItem(in);
        if(this.action == WindowAction.CLICK_ITEM) {
            this.param = MagicValues404.key(ClickItemParam.class, param);
        } else if(this.action == WindowAction.SHIFT_CLICK_ITEM) {
            this.param = MagicValues404.key(ShiftClickItemParam.class, param);
        } else if(this.action == WindowAction.MOVE_TO_HOTBAR_SLOT) {
            this.param = MagicValues404.key(MoveToHotbarParam.class, param);
        } else if(this.action == WindowAction.CREATIVE_GRAB_MAX_STACK) {
            this.param = MagicValues404.key(CreativeGrabParam.class, param);
        } else if(this.action == WindowAction.DROP_ITEM) {
            this.param = MagicValues404.key(DropItemParam.class, param + (this.slot != -999 ? 2 : 0));
        } else if(this.action == WindowAction.SPREAD_ITEM) {
            this.param = MagicValues404.key(SpreadItemParam.class, param);
        } else if(this.action == WindowAction.FILL_STACK) {
            this.param = MagicValues404.key(FillStackParam.class, param);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeShort(this.slot);

        int param = MagicValues404.value(Integer.class, this.param);
        if(this.action == WindowAction.DROP_ITEM) {
            param %= 2;
        }

        out.writeByte(param);
        out.writeShort(this.actionId);
        out.writeByte(MagicValues404.value(Integer.class, this.action));
        NetUtil404.writeItem(out, this.clickedItem);
    }
}
