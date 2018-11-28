package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.PlayerListEntry;
import misat11.hybrid.network.java.pabstract.data.game.PlayerListEntryAction;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerPlayerListEntryPacket;

import java.io.IOException;
import java.util.UUID;

@Getter
public class ServerPlayerListEntryPacket404 extends MinecraftPacket implements ServerPlayerListEntryPacket {
    private PlayerListEntryAction action;
    private PlayerListEntry entries[];

    @SuppressWarnings("unused")
    private ServerPlayerListEntryPacket404() {
    }

    public ServerPlayerListEntryPacket404(PlayerListEntryAction action, PlayerListEntry entries[]) {
        this.action = action;
        this.entries = entries;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.action = getMagic().key(PlayerListEntryAction.class, in.readVarInt());
        this.entries = new PlayerListEntry[in.readVarInt()];
        for(int count = 0; count < this.entries.length; count++) {
            UUID uuid = in.readUUID();
            GameProfile profile;
            if(this.action == PlayerListEntryAction.ADD_PLAYER) {
                profile = new GameProfile(uuid, in.readString());
            } else {
                profile = new GameProfile(uuid, null);
            }

            PlayerListEntry entry = null;
            switch(this.action) {
                case ADD_PLAYER:
                    int properties = in.readVarInt();
                    for(int index = 0; index < properties; index++) {
                        String propertyName = in.readString();
                        String value = in.readString();
                        String signature = null;
                        if(in.readBoolean()) {
                            signature = in.readString();
                        }

                        profile.getProperties().add(new GameProfile.Property(propertyName, value, signature));
                    }

                    int g = in.readVarInt();
                    GameMode gameMode = getMagic().key(GameMode.class, g < 0 ? 0 : g);
                    int ping = in.readVarInt();
                    Message displayName = null;
                    if(in.readBoolean()) {
                        displayName = Message.fromString(in.readString());
                    }

                    entry = new PlayerListEntry(profile, gameMode, ping, displayName);
                    break;
                case UPDATE_GAMEMODE:
                    g = in.readVarInt();
                    GameMode mode = getMagic().key(GameMode.class, g < 0 ? 0 : g);
                    entry = new PlayerListEntry(profile, mode);
                    break;
                case UPDATE_LATENCY:
                    int png = in.readVarInt();
                    entry = new PlayerListEntry(profile, png);
                    break;
                case UPDATE_DISPLAY_NAME:
                    Message disp = null;
                    if(in.readBoolean()) {
                        disp = Message.fromString(in.readString());
                    }

                    entry = new PlayerListEntry(profile, disp);
                case REMOVE_PLAYER:
                    entry = new PlayerListEntry(profile);
                    break;
            }

            this.entries[count] = entry;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.action));
        out.writeVarInt(this.entries.length);
        for(PlayerListEntry entry : this.entries) {
            out.writeUUID(entry.getProfile().getId());
            switch(this.action) {
                case ADD_PLAYER:
                    out.writeString(entry.getProfile().getName());
                    out.writeVarInt(entry.getProfile().getProperties().size());
                    for(GameProfile.Property property : entry.getProfile().getProperties()) {
                        out.writeString(property.getName());
                        out.writeString(property.getValue());
                        out.writeBoolean(property.hasSignature());
                        if(property.hasSignature()) {
                            out.writeString(property.getSignature());
                        }
                    }

                    out.writeVarInt(getMagic().value(Integer.class, entry.getGameMode()));
                    out.writeVarInt(entry.getPing());
                    out.writeBoolean(entry.getDisplayName() != null);
                    if(entry.getDisplayName() != null) {
                        out.writeString(entry.getDisplayName().toJsonString());
                    }

                    break;
                case UPDATE_GAMEMODE:
                    out.writeVarInt(getMagic().value(Integer.class, entry.getGameMode()));
                    break;
                case UPDATE_LATENCY:
                    out.writeVarInt(entry.getPing());
                    break;
                case UPDATE_DISPLAY_NAME:
                    out.writeBoolean(entry.getDisplayName() != null);
                    if(entry.getDisplayName() != null) {
                        out.writeString(entry.getDisplayName().toJsonString());
                    }

                    break;
                case REMOVE_PLAYER:
                    break;
            }
        }
    }
}
