package misat11.hybrid.network.java.p404.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.EntityStatus;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityStatusPacket;

import java.io.IOException;

@Getter
public class ServerEntityStatusPacket404 extends MinecraftPacket implements ServerEntityStatusPacket {
    protected int entityId;
    protected EntityStatus status;

    @SuppressWarnings("unused")
    private ServerEntityStatusPacket404() {
    }

    public ServerEntityStatusPacket404(int entityId, EntityStatus status) {
        this.entityId = entityId;
        this.status = status;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readInt();
        this.status = MagicValues404.key(EntityStatus.class, in.readByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(this.entityId);
        out.writeByte(MagicValues404.value(Integer.class, this.status));
    }
}
