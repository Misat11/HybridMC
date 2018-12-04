package misat11.hybrid.network.java.p393.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.TitleAction;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerTitlePacket;

import java.io.IOException;

@Getter
public class ServerTitlePacket393 extends MinecraftPacket implements ServerTitlePacket {
    private TitleAction action;

    private Message title;

    private Message subtitle;

    private Message actionBar;

    private int fadeIn;
    private int stay;
    private int fadeOut;

    @SuppressWarnings("unused")
    private ServerTitlePacket393() {
    }

    public ServerTitlePacket393(String title, boolean sub) {
        this(Message.fromString(title), sub);
    }

    public ServerTitlePacket393(Message title, boolean sub) {
        this(sub ? TitleAction.SUBTITLE : TitleAction.TITLE, title);
    }

    public ServerTitlePacket393(TitleAction action, String title) {
        this(action, Message.fromString(title));
    }

    public ServerTitlePacket393(TitleAction action, Message title) {
        this.action = action;

        switch(action) {
            case TITLE:
                this.title = title;
                break;
            case SUBTITLE:
                this.subtitle = title;
                break;
            case ACTION_BAR:
                this.actionBar = title;
                break;
            default:
                throw new IllegalArgumentException("action must be one of TITLE, SUBTITLE, ACTION_BAR");
        }
    }

    public ServerTitlePacket393(int fadeIn, int stay, int fadeOut) {
        this.action = TitleAction.TIMES;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public ServerTitlePacket393(boolean clear) {
        if(clear) {
            this.action = TitleAction.CLEAR;
        } else {
            this.action = TitleAction.RESET;
        }
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.action = getMagic().key(TitleAction.class, in.readVarInt());
        switch(this.action) {
            case TITLE:
                this.title = Message.fromString(in.readString());
                break;
            case SUBTITLE:
                this.subtitle = Message.fromString(in.readString());
                break;
            case ACTION_BAR:
                this.actionBar = Message.fromString(in.readString());
                break;
            case TIMES:
                this.fadeIn = in.readInt();
                this.stay = in.readInt();
                this.fadeOut = in.readInt();
                break;
            case CLEAR:
                break;
            case RESET:
                break;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.action));
        switch(this.action) {
            case TITLE:
                out.writeString(this.title.toJsonString());
                break;
            case SUBTITLE:
                out.writeString(this.subtitle.toJsonString());
                break;
            case ACTION_BAR:
                out.writeString(this.actionBar.toJsonString());
                break;
            case TIMES:
                out.writeInt(this.fadeIn);
                out.writeInt(this.stay);
                out.writeInt(this.fadeOut);
                break;
            case CLEAR:
                break;
            case RESET:
                break;
        }
    }
}
