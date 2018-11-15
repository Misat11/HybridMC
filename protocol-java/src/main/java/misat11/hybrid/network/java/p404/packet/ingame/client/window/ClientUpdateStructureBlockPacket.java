package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.window.UpdateStructureBlockAction;
import misat11.hybrid.network.java.pabstract.data.game.window.UpdateStructureBlockMode;
import misat11.hybrid.network.java.pabstract.data.game.world.block.StructureMirror;
import misat11.hybrid.network.java.pabstract.data.game.world.block.StructureRotation;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ClientUpdateStructureBlockPacket extends MinecraftPacket {
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
    private ClientUpdateStructureBlockPacket() {
    }

    public ClientUpdateStructureBlockPacket(Position position,
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

    public Position getPosition() {
        return this.position;
    }

    public UpdateStructureBlockAction getAction() {
        return this.action;
    }

    public UpdateStructureBlockMode getMode() {
        return this.mode;
    }

    public String getName() {
        return this.name;
    }

    public Position getOffset() {
        return this.offset;
    }

    public Position getSize() {
        return this.size;
    }

    public StructureMirror getMirror() {
        return this.mirror;
    }

    public StructureRotation getRotation() {
        return this.rotation;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public float getIntegrity() {
        return this.integrity;
    }

    public long getSeed() {
        return this.seed;
    }

    public boolean isIgnoreEntities() {
        return this.ignoreEntities;
    }

    public boolean isShowAir() {
        return this.showAir;
    }

    public boolean isShowBoundingBox() {
        return this.showBoundingBox;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = NetUtil404.readPosition(in);
        this.action = MagicValues404.key(UpdateStructureBlockAction.class, in.readVarInt());
        this.mode = MagicValues404.key(UpdateStructureBlockMode.class, in.readVarInt());
        this.name = in.readString();
        this.offset = new Position(in.readByte(), in.readByte(), in.readByte());
        this.size = new Position(in.readUnsignedByte(), in.readUnsignedByte(), in.readUnsignedByte());
        this.mirror = MagicValues404.key(StructureMirror.class, in.readVarInt());
        this.rotation = MagicValues404.key(StructureRotation.class, in.readVarInt());
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
        NetUtil404.writePosition(out, this.position);
        out.writeVarInt(MagicValues404.value(Integer.class, this.action));
        out.writeVarInt(MagicValues404.value(Integer.class, this.mode));
        out.writeString(this.name);
        out.writeByte(this.offset.getX());
        out.writeByte(this.offset.getY());
        out.writeByte(this.offset.getZ());
        out.writeByte(this.size.getX());
        out.writeByte(this.size.getY());
        out.writeByte(this.size.getZ());
        out.writeVarInt(MagicValues404.value(Integer.class, this.mirror));
        out.writeVarInt(MagicValues404.value(Integer.class, this.rotation));
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
