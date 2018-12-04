package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.EntityStatus;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityStatusPacket;

import java.io.IOException;

@Getter
public class ServerEntityStatusPacket393 extends MinecraftPacket implements ServerEntityStatusPacket {
    protected int entityId;
    protected EntityStatus status;

    @SuppressWarnings("unused")
    private ServerEntityStatusPacket393() {
    }

    public ServerEntityStatusPacket393(int entityId, EntityStatus status) {
        this.entityId = entityId;
        this.status = status;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readInt();
        this.status = getMagic().key(EntityStatus.class, in.readByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(this.entityId);
        out.writeByte(getMagic().value(Integer.class, this.status));
    }
}
