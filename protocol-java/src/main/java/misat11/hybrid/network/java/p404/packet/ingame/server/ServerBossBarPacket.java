package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.BossBarAction;
import misat11.hybrid.network.java.pabstract.data.game.BossBarColor;
import misat11.hybrid.network.java.pabstract.data.game.BossBarDivision;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;
import java.util.UUID;

public class ServerBossBarPacket extends MinecraftPacket {
    private UUID uuid;
    private BossBarAction action;

    private Message title;
    private float health;
    private BossBarColor color;
    private BossBarDivision division;
    private boolean darkenSky;
    private boolean playEndMusic;
    private boolean showFog;

    @SuppressWarnings("unused")
    private ServerBossBarPacket() {
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, Message title, float health, BossBarColor color, BossBarDivision division, boolean darkenSky, boolean playEndMusic, boolean showFog) {
        this.uuid = uuid;
        this.action = BossBarAction.ADD;

        this.title = title;
        this.health = health;
        this.color = color;
        this.division = division;
        this.darkenSky = darkenSky;
        this.playEndMusic = playEndMusic;
        this.showFog = showFog;
    }

    public ServerBossBarPacket(UUID uuid) {
        this.uuid = uuid;
        this.action = BossBarAction.REMOVE;
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, float health) {
        this.uuid = uuid;
        this.action = BossBarAction.UPDATE_HEALTH;

        this.health = health;
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, Message title) {
        this.uuid = uuid;
        this.action = BossBarAction.UPDATE_TITLE;

        this.title = title;
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, BossBarColor color, BossBarDivision division) {
        this.uuid = uuid;
        this.action = BossBarAction.UPDATE_STYLE;

        this.color = color;
        this.division = division;
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, boolean darkenSky, boolean playEndMusic, boolean showFog) {
        this.uuid = uuid;
        this.action = BossBarAction.UPDATE_FLAGS;

        this.darkenSky = darkenSky;
        this.playEndMusic = playEndMusic;
        this.showFog = showFog;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public BossBarAction getAction() {
        return this.action;
    }

    public Message getTitle() {
        return this.title;
    }

    public float getHealth() {
        return this.health;
    }

    public BossBarColor getColor() {
        return this.color;
    }

    public BossBarDivision getDivision() {
        return this.division;
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
        this.uuid = in.readUUID();
        this.action = MagicValues404.key(BossBarAction.class, in.readVarInt());

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_TITLE) {
            this.title = Message.fromString(in.readString());
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_HEALTH) {
            this.health = in.readFloat();
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_STYLE) {
            this.color = MagicValues404.key(BossBarColor.class, in.readVarInt());
            this.division = MagicValues404.key(BossBarDivision.class, in.readVarInt());
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
        out.writeUUID(this.uuid);
        out.writeVarInt(MagicValues404.value(Integer.class, this.action));

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_TITLE) {
            out.writeString(this.title.toJsonString());
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_HEALTH) {
            out.writeFloat(this.health);
        }

        if(this.action == BossBarAction.ADD || this.action == BossBarAction.UPDATE_STYLE) {
            out.writeVarInt(MagicValues404.value(Integer.class, this.color));
            out.writeVarInt(MagicValues404.value(Integer.class, this.division));
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
