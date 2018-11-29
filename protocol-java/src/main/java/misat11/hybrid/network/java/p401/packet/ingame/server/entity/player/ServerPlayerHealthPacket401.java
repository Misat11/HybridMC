package misat11.hybrid.network.java.p401.packet.ingame.server.entity.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player.ServerPlayerHealthPacket;

import java.io.IOException;

@Getter
public class ServerPlayerHealthPacket401 extends MinecraftPacket implements ServerPlayerHealthPacket {
    private float health;
    private int food;
    private float saturation;

    @SuppressWarnings("unused")
    private ServerPlayerHealthPacket401() {
    }

    public ServerPlayerHealthPacket401(float health, int food, float saturation) {
        this.health = health;
        this.food = food;
        this.saturation = saturation;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.health = in.readFloat();
        this.food = in.readVarInt();
        this.saturation = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat(this.health);
        out.writeVarInt(this.food);
        out.writeFloat(this.saturation);
    }
}
