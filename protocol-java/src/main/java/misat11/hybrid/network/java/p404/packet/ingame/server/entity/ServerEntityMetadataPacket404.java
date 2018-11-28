package misat11.hybrid.network.java.p404.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.EntityMetadata;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityMetadataPacket;

import java.io.IOException;

@Getter
public class ServerEntityMetadataPacket404 extends MinecraftPacket implements ServerEntityMetadataPacket {
    private int entityId;
    private EntityMetadata metadata[];

    @SuppressWarnings("unused")
    private ServerEntityMetadataPacket404() {
    }

    public ServerEntityMetadataPacket404(int entityId, EntityMetadata metadata[]) {
        this.entityId = entityId;
        this.metadata = metadata;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.metadata = getUtil().readEntityMetadata(in, getMagic());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        getUtil().writeEntityMetadata(out, this.metadata, getMagic());
    }
}
