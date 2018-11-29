package misat11.hybrid.network.java.p401.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.BossBarAction;
import misat11.hybrid.network.java.pabstract.data.game.BossBarColor;
import misat11.hybrid.network.java.pabstract.data.game.BossBarDivision;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerBossBarPacket;

import java.io.IOException;
import java.util.UUID;

@Getter
public class ServerBossBarPacket401 extends MinecraftPacket implements ServerBossBarPacket {
    private UUID UUID;
    private BossBarAction action;

    private Message title;
    private float health;
    private BossBarColor color;
    private BossBarDivision division;
    private boolean darkenSky;
    private boolean playEndMusic;
    private boolean showFog;

    @SuppressWarnings("unused")
    private ServerBossBarPacket401() {
    }

    public ServerBossBarPacket401(UUID uuid, BossBarAction action, Message title, float health, BossBarColor color, BossBarDivision division, boolean darkenSky, boolean playEndMusic, boolean showFog) {
        this.UUID = uuid;
        this.action = BossBarAction.ADD;

        this.title = title;
        this.health = health;
        this.color = color;
        this.division = division;
        this.darkenSky = darkenSky;
        this.playEndMusic = playEndMusic;
        this.showFog = showFog;
    }

    public ServerBossBarPacket401(UUID uuid) {
        this.UUID = uuid;
        this.action = BossBarAction.REMOVE;
    }

    public ServerBossBarPacket401(UUID uuid, BossBarAction action, float health) {
        this.UUID = uuid;
        this.action = BossBarAction.UPDATE_HEALTH;

        this.health = health;
    }

    public ServerBossBarPacket401(UUID uuid, BossBarAction action, Message title) {
        this.UUID = uuid;
        this.action = BossBarAction.UPDATE_TITLE;

        this.title = title;
    }

    public ServerBossBarPacket401(UUID uuid, BossBarAction action, BossBarColor color, BossBarDivision division) {
        this.UUID = uuid;
        this.action = BossBarAction.UPDATE_STYLE;

        this.color = color;
        this.division = division;
    }

    public ServerBossBarPacket401(UUID uuid, BossBarAction action, boolean darkenSky, boolean playEndMusic, boolean showFog) {
        this.UUID = uuid;
        this.action = BossBarAction.UPDATE_FLAGS;

        this.darkenSky = darkenSky;
        this.playEndMusic = playEndMusic;
        this.showFog = showFog;
    }

    public boolean getDarkenSky() {
        return this.darkenSky;
    }

    public boolean shouldPlayEndMusic() {
        return this.playEndMusic;
    }

    public boolean shouldShowFog() {
        return this.showFog;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.UUID = in.readUUID();
        this.action = getMagic().key(BossBarAction.class, in.readVarInt());

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_TITLE) {
            this.title = Message.fromString(in.readString());
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_HEALTH) {
            this.health = in.readFloat();
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_STYLE) {
            this.color = getMagic().key(BossBarColor.class, in.readVarInt());
            this.division = getMagic().key(BossBarDivision.class, in.readVarInt());
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_FLAGS) {
            int flags = in.readUnsignedByte();
            this.darkenSky = (flags & 0x1) == 0x1;
            this.playEndMusic = (flags & 0x2) == 0x2;
            this.showFog = (flags & 0x4) == 0x4;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeUUID(this.UUID);
        out.writeVarInt(getMagic().value(Integer.class, this.action));

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_TITLE) {
            out.writeString(this.title.toJsonString());
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_HEALTH) {
            out.writeFloat(this.health);
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_STYLE) {
            out.writeVarInt(getMagic().value(Integer.class, this.color));
            out.writeVarInt(getMagic().value(Integer.class, this.division));
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_FLAGS) {
            int flags = 0;
            if(this.darkenSky) {
                flags |= 0x1;
            }

            if(this.playEndMusic) {
                flags |= 0x2;
            }

            if(this.showFog) {
                flags |= 0x4;
            }

            out.writeByte(flags);
        }
    }
}
