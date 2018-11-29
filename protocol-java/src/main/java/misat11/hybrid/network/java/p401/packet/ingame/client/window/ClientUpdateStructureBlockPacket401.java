package misat11.hybrid.network.java.p401.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.window.UpdateStructureBlockAction;
import misat11.hybrid.network.java.pabstract.data.game.window.UpdateStructureBlockMode;
import misat11.hybrid.network.java.pabstract.data.game.world.block.StructureMirror;
import misat11.hybrid.network.java.pabstract.data.game.world.block.StructureRotation;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientUpdateStructureBlockPacket;

import java.io.IOException;

@Getter
public class ClientUpdateStructureBlockPacket401 extends MinecraftPacket implements ClientUpdateStructureBlockPacket {
    private Position position;
    private UpdateStructureBlockAction action;
    private UpdateStructureBlockMode mode;
    private String name;
    private Position offset;
    private Position size;
    private StructureMirror mirror;
    private StructureRotation rotation;
    private String metadata;
    private float integrity;
    private long seed;
    private boolean ignoreEntities;
    private boolean showAir;
    private boolean showBoundingBox;

    @SuppressWarnings("unused")
    private ClientUpdateStructureBlockPacket401() {
    }

    public ClientUpdateStructureBlockPacket401(Position position,
                                            UpdateStructureBlockAction action, UpdateStructureBlockMode mode,
                                            String name, Position offset, Position size,
                                            StructureMirror mirror, StructureRotation rotation,
                                            String metadata, float integrity, long seed,
                                            boolean ignoreEntities, boolean showAir, boolean showBoundingBox) {
        this.position = position;
        this.action = action;
        this.mode = mode;
        this.name = name;
        this.offset = offset;
        this.size = size;
        this.mirror = mirror;
        this.rotation = rotation;
        this.metadata = metadata;
        this.integrity = integrity;
        this.seed = seed;
        this.ignoreEntities = ignoreEntities;
        this.showAir = showAir;
        this.showBoundingBox = showBoundingBox;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = getUtil().readPosition(in);
        this.action = getMagic().key(UpdateStructureBlockAction.class, in.readVarInt());
        this.mode = getMagic().key(UpdateStructureBlockMode.class, in.readVarInt());
        this.name = in.readString();
        this.offset = new Position(in.readByte(), in.readByte(), in.readByte());
        this.size = new Position(in.readUnsignedByte(), in.readUnsignedByte(), in.readUnsignedByte());
        this.mirror = getMagic().key(StructureMirror.class, in.readVarInt());
        this.rotation = getMagic().key(StructureRotation.class, in.readVarInt());
        this.metadata = in.readString();
        this.integrity = in.readFloat();
        this.seed = in.readVarLong();
        int flags = in.readUnsignedByte();
        this.ignoreEntities = (flags & 0x01) != 0;
        this.showAir = (flags & 0x02) != 0;
        this.showBoundingBox = (flags & 0x04) != 0;
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writePosition(out, this.position);
        out.writeVarInt(getMagic().value(Integer.class, this.action));
        out.writeVarInt(getMagic().value(Integer.class, this.mode));
        out.writeString(this.name);
        out.writeByte(this.offset.getX());
        out.writeByte(this.offset.getY());
        out.writeByte(this.offset.getZ());
        out.writeByte(this.size.getX());
        out.writeByte(this.size.getY());
        out.writeByte(this.size.getZ());
        out.writeVarInt(getMagic().value(Integer.class, this.mirror));
        out.writeVarInt(getMagic().value(Integer.class, this.rotation));
        out.writeString(this.metadata);
        out.writeFloat(this.integrity);
        out.writeVarLong(this.seed);
        int flags = 0;
        if (this.ignoreEntities) flags |= 0x01;
        if (this.showAir) flags |= 0x02;
        if (this.showBoundingBox) flags |= 0x04;
        out.writeByte(flags);
    }
}
