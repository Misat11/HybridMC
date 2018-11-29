package misat11.hybrid.network.java.p401.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntitySetPassengersPacket;

import java.io.IOException;

@Getter
public class ServerEntitySetPassengersPacket401 extends MinecraftPacket implements ServerEntitySetPassengersPacket {
    private int entityId;
    private int passengerIds[];

    @SuppressWarnings("unused")
    private ServerEntitySetPassengersPacket401() {
    }

    public ServerEntitySetPassengersPacket401(int entityId, int... passengerIds) {
        this.entityId = entityId;
        this.passengerIds = passengerIds;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.passengerIds = new int[in.readVarInt()];
        for(int index = 0; index < this.passengerIds.length; index++) {
            this.passengerIds[index] = in.readVarInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeVarInt(this.passengerIds.length);
        for(int entityId : this.passengerIds) {
            out.writeVarInt(entityId);
        }
    }
}
