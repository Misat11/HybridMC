package misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.PaintingType;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.object.HangingDirection;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket;

import java.io.IOException;
import java.util.UUID;

@Getter
public class ServerSpawnPaintingPacket401 extends MinecraftPacket implements ServerSpawnPaintingPacket {
    private int entityId;
    private UUID UUID;
    private PaintingType paintingType;
    private Position position;
    private HangingDirection direction;

    @SuppressWarnings("unused")
    private ServerSpawnPaintingPacket401() {
    }

    public ServerSpawnPaintingPacket401(int entityId, UUID uuid, PaintingType paintingType, Position position, HangingDirection direction) {
        this.entityId = entityId;
        this.UUID = uuid;
        this.paintingType = paintingType;
        this.position = position;
        this.direction = direction;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.UUID = in.readUUID();
        this.paintingType = getMagic().key(PaintingType.class, in.readVarInt());
        this.position = getUtil().readPosition(in);
        this.direction = getMagic().key(HangingDirection.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeUUID(this.UUID);
        out.writeVarInt(getMagic().value(Integer.class, this.paintingType));
        getUtil().writePosition(out, this.position);
        out.writeByte(getMagic().value(Integer.class, this.direction));
    }
}
