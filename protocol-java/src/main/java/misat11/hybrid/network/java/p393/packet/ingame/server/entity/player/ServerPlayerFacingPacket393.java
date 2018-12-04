package misat11.hybrid.network.java.p393.packet.ingame.server.entity.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.FeetOrEyes;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.player.ServerPlayerFacingPacket;

import java.io.IOException;

@Getter
public class ServerPlayerFacingPacket393 extends MinecraftPacket implements ServerPlayerFacingPacket {
    private FeetOrEyes origin; // presumably the origin from which pitch is calculated at
    private double x;
    private double y;
    private double z;
    private Integer targetEntityId;
    private FeetOrEyes targetEntityFeetOrEyes;

    @SuppressWarnings("unused")
    private ServerPlayerFacingPacket393() {
    }

    public ServerPlayerFacingPacket393(FeetOrEyes origin, double x, double y, double z) {
        this.origin = origin;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ServerPlayerFacingPacket393(FeetOrEyes origin, int targetEntityId, FeetOrEyes lookAt) {
        this.origin = origin;
        this.targetEntityId = targetEntityId;
        this.targetEntityFeetOrEyes = lookAt;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.origin = getMagic().key(FeetOrEyes.class, in.readVarInt());
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
        if (in.readBoolean()) {
            this.targetEntityId = in.readVarInt();
            this.targetEntityFeetOrEyes = getMagic().key(FeetOrEyes.class, in.readVarInt());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.origin));
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        if (this.targetEntityId != null) {
            out.writeBoolean(true);
            out.writeVarInt(this.targetEntityId);
            out.writeVarInt(getMagic().value(Integer.class, this.targetEntityFeetOrEyes));
        } else {
            out.writeBoolean(false);
        }
    }
}
