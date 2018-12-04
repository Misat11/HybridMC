package misat11.hybrid.network.java.p393.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityDestroyPacket;

import java.io.IOException;

@Getter
public class ServerEntityDestroyPacket393 extends MinecraftPacket implements ServerEntityDestroyPacket {
    private int entityIds[];

    @SuppressWarnings("unused")
    private ServerEntityDestroyPacket393() {
    }

    public ServerEntityDestroyPacket393(int... entityIds) {
        this.entityIds = entityIds;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityIds = new int[in.readVarInt()];
        for(int index = 0; index < this.entityIds.length; index++) {
            this.entityIds[index] = in.readVarInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityIds.length);
        for(int entityId : this.entityIds) {
            out.writeVarInt(entityId);
        }
    }
}
