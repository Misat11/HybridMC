package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.value.*;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerBlockValuePacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerBlockValuePacket404 extends MinecraftPacket implements ServerBlockValuePacket {
    private static final int NOTE_BLOCK = 73;
    private static final int STICKY_PISTON = 92;
    private static final int PISTON = 99;
    private static final int MOB_SPAWNER = 140;
    private static final int CHEST = 142;
    private static final int ENDER_CHEST = 249;
    private static final int BEACON = 257;
    private static final int TRAPPED_CHEST = 305;
    private static final int END_GATEWAY = 472;
    private static final int SHULKER_BOX_LOWER = 482;
    private static final int SHULKER_BOX_HIGHER = 498;

    private Position position;
    private BlockValueType type;
    private BlockValue value;
    private int blockId;

    @Override
    public void read(NetInput in) throws IOException {
        this.position = getUtil().readPosition(in);
        int type = in.readUnsignedByte();
        int value = in.readUnsignedByte();
        this.blockId = in.readVarInt() & 0xFFF;

        if(this.blockId == NOTE_BLOCK) {
            this.type = getMagic().key(NoteBlockValueType.class, type);
            this.value = new NoteBlockValue(value);
        } else if(this.blockId == STICKY_PISTON || this.blockId == PISTON) {
            this.type = getMagic().key(PistonValueType.class, type);
            this.value = getMagic().key(PistonValue.class, value);
        } else if(this.blockId == MOB_SPAWNER) {
            this.type = getMagic().key(MobSpawnerValueType.class, type);
            this.value = new MobSpawnerValue();
        } else if(this.blockId == CHEST || this.blockId == ENDER_CHEST || this.blockId == TRAPPED_CHEST
                || (this.blockId >= SHULKER_BOX_LOWER && this.blockId <= SHULKER_BOX_HIGHER)) {
            this.type = getMagic().key(ChestValueType.class, type);
            this.value = new ChestValue(value);
        } else if(this.blockId == BEACON) {
            this.type = getMagic().key(BeaconValueType.class, type);
            this.value = new BeaconValue();
        } else if(this.blockId == END_GATEWAY) {
            this.type = getMagic().key(EndGatewayValueType.class, type);
            this.value = new EndGatewayValue();
        } else {
            this.type = getMagic().key(GenericBlockValueType.class, type);
            this.value = new GenericBlockValue(value);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        int val = 0;
        if(this.type instanceof NoteBlockValueType) {
            val = ((NoteBlockValue) this.value).getPitch();
        } else if(this.type instanceof PistonValueType) {
            val = getMagic().value(Integer.class, this.value);
        } else if(this.type instanceof ChestValueType) {
            val = ((ChestValue) this.value).getViewers();
        } else if(this.type instanceof GenericBlockValueType) {
            val = ((GenericBlockValue) this.value).getValue();
        }

        getUtil().writePosition(out, this.position);
        out.writeByte(getMagic().value(Integer.class, this.type));
        out.writeByte(val);
        out.writeVarInt(this.blockId & 4095);
    }
}
