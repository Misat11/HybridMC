package misat11.hybrid.network.java.p393.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.world.block.ExplodedBlockRecord;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerExplosionPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerExplosionPacket393 extends MinecraftPacket implements ServerExplosionPacket {
    private float x;
    private float y;
    private float z;
    private float radius;
    private List<ExplodedBlockRecord> exploded;
    private float pushX;
    private float pushY;
    private float pushZ;

    @Override
    public void read(NetInput in) throws IOException {
        this.x = in.readFloat();
        this.y = in.readFloat();
        this.z = in.readFloat();
        this.radius = in.readFloat();
        this.exploded = new ArrayList<ExplodedBlockRecord>();
        int length = in.readInt();
        for(int count = 0; count < length; count++) {
            this.exploded.add(new ExplodedBlockRecord(in.readByte(), in.readByte(), in.readByte()));
        }

        this.pushX = in.readFloat();
        this.pushY = in.readFloat();
        this.pushZ = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat(this.x);
        out.writeFloat(this.y);
        out.writeFloat(this.z);
        out.writeFloat(this.radius);
        out.writeInt(this.exploded.size());
        for(ExplodedBlockRecord record : this.exploded) {
            out.writeByte(record.getX());
            out.writeByte(record.getY());
            out.writeByte(record.getZ());
        }

        out.writeFloat(this.pushX);
        out.writeFloat(this.pushY);
        out.writeFloat(this.pushZ);
    }
}
