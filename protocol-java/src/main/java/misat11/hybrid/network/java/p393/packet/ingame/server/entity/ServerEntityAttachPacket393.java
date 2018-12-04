package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityAttachPacket;

import java.io.IOException;

@Getter
public class ServerEntityAttachPacket393 extends MinecraftPacket implements ServerEntityAttachPacket {
    private int entityId;
    private int attachedToId;

    @SuppressWarnings("unused")
    private ServerEntityAttachPacket393() {
    }

    public ServerEntityAttachPacket393(int entityId, int attachedToId) {
        this.entityId = entityId;
        this.attachedToId = attachedToId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readInt();
        this.attachedToId = in.readInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(this.entityId);
        out.writeInt(this.attachedToId);
    }
}
