package misat11.hybrid.network.java.p393.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientSetBeaconEffectPacket;

import java.io.IOException;

@Getter
public class ClientSetBeaconEffectPacket393 extends MinecraftPacket implements ClientSetBeaconEffectPacket {
    private int primaryEffect;
    private int secondaryEffect;

    @SuppressWarnings("unused")
    private ClientSetBeaconEffectPacket393() {
    }

    public ClientSetBeaconEffectPacket393(int primaryEffect, int secondaryEffect) {
        this.primaryEffect = primaryEffect;
        this.secondaryEffect = secondaryEffect;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.primaryEffect = in.readVarInt();
        this.secondaryEffect = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.primaryEffect);
        out.writeVarInt(this.secondaryEffect);
    }
}
