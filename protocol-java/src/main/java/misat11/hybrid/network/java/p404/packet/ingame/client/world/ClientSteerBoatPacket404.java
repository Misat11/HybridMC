package misat11.hybrid.network.java.p404.packet.ingame.client.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.world.ClientSteerBoatPacket;

import java.io.IOException;

@Getter
public class ClientSteerBoatPacket404 extends MinecraftPacket implements ClientSteerBoatPacket {
    private boolean rightPaddleTurning;
    private boolean leftPaddleTurning;

    @SuppressWarnings("unused")
    private ClientSteerBoatPacket404() {
    }

    public ClientSteerBoatPacket404(boolean rightPaddleTurning, boolean leftPaddleTurning) {
        this.rightPaddleTurning = rightPaddleTurning;
        this.leftPaddleTurning = leftPaddleTurning;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.rightPaddleTurning = in.readBoolean();
        this.leftPaddleTurning = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeBoolean(this.rightPaddleTurning);
        out.writeBoolean(this.leftPaddleTurning);
    }
}
