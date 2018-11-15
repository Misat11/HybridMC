package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.BuiltinSound;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.CustomSound;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.Sound;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.SoundCategory;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ServerStopSoundPacket extends MinecraftPacket {
    private SoundCategory category;
    private Sound sound;

    @SuppressWarnings("unused")
    private ServerStopSoundPacket() {
    }

    public ServerStopSoundPacket(SoundCategory category, Sound sound) {
        this.category = category;
        this.sound = sound;
    }

    public SoundCategory getCategory() {
        return this.category;
    }

    public Sound getSound() {
        return sound;
    }

    @Override
    public void read(NetInput in) throws IOException {
        int flags = in.readByte();
        if((flags & 0x1) != 0) {
            this.category = MagicValues404.key(SoundCategory.class, in.readVarInt());
        } else {
            this.category = null;
        }
        if((flags & 0x2) != 0) {
            String value = in.readString();
            try {
                this.sound = MagicValues404.key(BuiltinSound.class, value);
            } catch(IllegalArgumentException e) {
                this.sound = new CustomSound(value);
            }
        } else {
            this.sound = null;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte((this.category != null ? 0x1 : 0) | (this.sound != null ? 0x2 : 0));
        if (this.category != null) {
            out.writeByte(MagicValues404.value(Integer.class, this.category));
        }
        if (this.sound != null) {
            String value = "";
            if (this.sound instanceof CustomSound) {
                value = ((CustomSound) this.sound).getName();
            } else if (this.sound instanceof BuiltinSound) {
                value = MagicValues404.value(String.class, this.sound);
            }
            out.writeString(value);
        }
    }
}
