package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityHeadLookPacket;

import java.io.IOException;

@Getter
public class ServerEntityHeadLookPacket393 extends MinecraftPacket implements ServerEntityHeadLookPacket {
    private int entityId;
    private float headYaw;

    @SuppressWarnings("unused")
    private ServerEntityHeadLookPacket393() {
    }

    public ServerEntityHeadLookPacket393(int entityId, float headYaw) {
        this.entityId = entityId;
        this.headYaw = headYaw;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.headYaw = in.readByte() * 360 / 256f;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeByte((byte) (this.headYaw * 256 / 360));
    }
}
