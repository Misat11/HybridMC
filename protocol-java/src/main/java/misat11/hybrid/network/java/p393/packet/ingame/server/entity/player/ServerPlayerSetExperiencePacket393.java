package misat11.hybrid.network.java.p393.packet.ingame.server.entity.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket;

import java.io.IOException;

@Getter
public class ServerPlayerSetExperiencePacket393 extends MinecraftPacket implements ServerPlayerSetExperiencePacket {
    private float slot;
    private int level;
    private int totalExperience;

    @SuppressWarnings("unused")
    private ServerPlayerSetExperiencePacket393() {
    }

    public ServerPlayerSetExperiencePacket393(float experience, int level, int totalExperience) {
        this.slot = experience;
        this.level = level;
        this.totalExperience = totalExperience;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.slot = in.readFloat();
        this.level = in.readVarInt();
        this.totalExperience = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat(this.slot);
        out.writeVarInt(this.level);
        out.writeVarInt(this.totalExperience);
    }
}
