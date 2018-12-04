package misat11.hybrid.network.java.p393.packet.ingame.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.data.game.setting.ChatVisibility;
import misat11.hybrid.network.java.pabstract.data.game.setting.SkinPart;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.ClientSettingsPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ClientSettingsPacket393 extends MinecraftPacket implements ClientSettingsPacket {
    private String locale;
    private int renderDistance;
    private ChatVisibility chatVisibility;
    private boolean usedChatColors;
    private List<SkinPart> visibleParts;
    private Hand mainHand;

    @SuppressWarnings("unused")
    private ClientSettingsPacket393() {
    }

    public ClientSettingsPacket393(String locale, int renderDistance, ChatVisibility chatVisibility, boolean usedChatColors, SkinPart[] visibleParts, Hand mainHand) {
        this.locale = locale;
        this.renderDistance = renderDistance;
        this.chatVisibility = chatVisibility;
        this.usedChatColors = usedChatColors;
        this.visibleParts = Arrays.asList(visibleParts);
        this.mainHand = mainHand;
    }
    @Override
    public void read(NetInput in) throws IOException {
        this.locale = in.readString();
        this.renderDistance = in.readByte();
        this.chatVisibility = getMagic().key(ChatVisibility.class, in.readVarInt());
        this.usedChatColors = in.readBoolean();
        this.visibleParts = new ArrayList<SkinPart>();

        int flags = in.readUnsignedByte();
        for(SkinPart part : SkinPart.values()) {
            int bit = 1 << part.ordinal();
            if((flags & bit) == bit) {
                this.visibleParts.add(part);
            }
        }

        this.mainHand = getMagic().key(Hand.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.locale);
        out.writeByte(this.renderDistance);
        out.writeVarInt(getMagic().value(Integer.class, this.chatVisibility));
        out.writeBoolean(this.usedChatColors);

        int flags = 0;
        for(SkinPart part : this.visibleParts) {
            flags |= 1 << part.ordinal();
        }

        out.writeByte(flags);

        out.writeVarInt(getMagic().value(Integer.class, this.mainHand));
    }
}
