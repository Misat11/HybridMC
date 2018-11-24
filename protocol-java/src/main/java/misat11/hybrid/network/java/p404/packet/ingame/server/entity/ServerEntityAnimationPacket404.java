package misat11.hybrid.network.java.p404.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Animation;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityAnimationPacket;

import java.io.IOException;

@Getter
public class ServerEntityAnimationPacket404 extends MinecraftPacket implements ServerEntityAnimationPacket {
    private int entityId;
    private Animation animation;

    @SuppressWarnings("unused")
    private ServerEntityAnimationPacket404() {
    }

    public ServerEntityAnimationPacket404(int entityId, Animation animation) {
        this.entityId = entityId;
        this.animation = animation;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.animation = MagicValues404.key(Animation.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeByte(MagicValues404.value(Integer.class, this.animation));
    }
}
