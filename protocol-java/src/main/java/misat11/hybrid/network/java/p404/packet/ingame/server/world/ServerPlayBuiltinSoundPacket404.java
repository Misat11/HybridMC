package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.BuiltinSound;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.SoundCategory;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerPlayBuiltinSoundPacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerPlayBuiltinSoundPacket404 extends MinecraftPacket implements ServerPlayBuiltinSoundPacket {
    private BuiltinSound sound;
    private SoundCategory category;
    private double x;
    private double y;
    private double z;
    private float volume;
    private float pitch;

    @Override
    public void read(NetInput in) throws IOException {
        this.sound = getMagic().key(BuiltinSound.class, in.readVarInt());
        this.category = getMagic().key(SoundCategory.class, in.readVarInt());
        this.x = in.readInt() / 8D;
        this.y = in.readInt() / 8D;
        this.z = in.readInt() / 8D;
        this.volume = in.readFloat();
        this.pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.sound));
        out.writeVarInt(getMagic().value(Integer.class, this.category));
        out.writeInt((int) (this.x * 8));
        out.writeInt((int) (this.y * 8));
        out.writeInt((int) (this.z * 8));
        out.writeFloat(this.volume);
        out.writeFloat(this.pitch);
    }
}
