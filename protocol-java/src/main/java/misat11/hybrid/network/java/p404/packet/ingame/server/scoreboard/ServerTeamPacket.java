package misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.CollisionRule;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.NameTagVisibility;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.TeamAction;
import misat11.hybrid.network.java.pabstract.data.game.scoreboard.TeamColor;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ServerTeamPacket extends MinecraftPacket {
    private String name;
    private TeamAction action;
    private Message displayName;
    private Message prefix;
    private Message suffix;
    private boolean friendlyFire;
    private boolean seeFriendlyInvisibles;
    private NameTagVisibility nameTagVisibility;
    private CollisionRule collisionRule;
    private TeamColor color;
    private String players[];

    @SuppressWarnings("unused")
    private ServerTeamPacket() {
    }

    public ServerTeamPacket(String name) {
        this.name = name;
        this.action = TeamAction.REMOVE;
    }

    public ServerTeamPacket(String name, TeamAction action, String players[]) {
        if(action != TeamAction.ADD_PLAYER && action != TeamAction.REMOVE_PLAYER) {
            throw new IllegalArgumentException("(name, action, players) constructor only valid for adding and removing players.");
        }

        this.name = name;
        this.action = action;
        this.players = players;
    }

    public ServerTeamPacket(String name, Message displayName, Message prefix, Message suffix, boolean friendlyFire, boolean seeFriendlyInvisibles, NameTagVisibility nameTagVisibility, CollisionRule collisionRule, TeamColor color) {
        this.name = name;
        this.displayName = displayName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.friendlyFire = friendlyFire;
        this.seeFriendlyInvisibles = seeFriendlyInvisibles;
        this.nameTagVisibility = nameTagVisibility;
        this.collisionRule = collisionRule;
        this.color = color;
        this.action = TeamAction.UPDATE;
    }

    public ServerTeamPacket(String name, Message displayName, Message prefix, Message suffix, boolean friendlyFire, boolean seeFriendlyInvisibles, NameTagVisibility nameTagVisibility, CollisionRule collisionRule, TeamColor color, String players[]) {
        this.name = name;
        this.displayName = displayName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.friendlyFire = friendlyFire;
        this.seeFriendlyInvisibles = seeFriendlyInvisibles;
        this.nameTagVisibility = nameTagVisibility;
        this.collisionRule = collisionRule;
        this.color = color;
        this.players = players;
        this.action = TeamAction.CREATE;
    }

    public String getTeamName() {
        return this.name;
    }

    public TeamAction getAction() {
        return this.action;
    }

    public Message getDisplayName() {
        return this.displayName;
    }

    public Message getPrefix() {
        return this.prefix;
    }

    public Message getSuffix() {
        return this.suffix;
    }

    public boolean getFriendlyFire() {
        return this.friendlyFire;
    }

    public boolean getSeeFriendlyInvisibles() {
        return seeFriendlyInvisibles;
    }

    public NameTagVisibility getNameTagVisibility() {
        return this.nameTagVisibility;
    }

    public CollisionRule getCollisionRule() {
        return this.collisionRule;
    }

    public TeamColor getColor() {
        return this.color;
    }

    public String[] getPlayers() {
        return this.players;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.name = in.readString();
        this.action = MagicValues404.key(TeamAction.class, in.readByte());
        if(this.action == TeamAction.CREATE || this.action == TeamAction.UPDATE) {
            this.displayName = Message.fromString(in.readString());
            byte flags = in.readByte();
            this.friendlyFire = (flags & 0x1) != 0;
            this.seeFriendlyInvisibles = (flags & 0x2) != 0;
            this.nameTagVisibility = MagicValues404.key(NameTagVisibility.class, in.readString());
            this.collisionRule = MagicValues404.key(CollisionRule.class, in.readString());

            try {
                this.color = MagicValues404.key(TeamColor.class, in.readVarInt());
            } catch(IllegalArgumentException e) {
                this.color = TeamColor.NONE;
            }

            this.prefix = Message.fromString(in.readString());
            this.suffix = Message.fromString(in.readString());
        }

        if(this.action == TeamAction.CREATE || this.action == TeamAction.ADD_PLAYER || this.action == TeamAction.REMOVE_PLAYER) {
            this.players = new String[in.readVarInt()];
            for(int index = 0; index < this.players.length; index++) {
                this.players[index] = in.readString();
            }
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.name);
        out.writeByte(MagicValues404.value(Integer.class, this.action));
        if(this.action == TeamAction.CREATE || this.action == TeamAction.UPDATE) {
            out.writeString(this.displayName.toJsonString());
            out.writeByte((this.friendlyFire ? 0x1 : 0x0) | (this.seeFriendlyInvisibles ? 0x2 : 0x0));
            out.writeString(MagicValues404.value(String.class, this.nameTagVisibility));
            out.writeString(MagicValues404.value(String.class, this.collisionRule));
            out.writeVarInt(MagicValues404.value(Integer.class, this.color));
            out.writeString(this.prefix.toJsonString());
            out.writeString(this.suffix.toJsonString());
        }

        if(this.action == TeamAction.CREATE || this.action == TeamAction.ADD_PLAYER || this.action == TeamAction.REMOVE_PLAYER) {
            out.writeVarInt(this.players.length);
            for(String player : this.players) {
                if(player != null) {
                    out.writeString(player);
                }
            }
        }
    }
}
