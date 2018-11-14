package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.packet.MinecraftPacket;

import java.io.IOException;

public class ClientSetBeaconEffectPacket extends MinecraftPacket {
    private int primaryEffect;
    private int secondaryEffect;

    @SuppressWarnings("unused")
    private ClientSetBeaconEffectPacket() {
    }

    public ClientSetBeaconEffectPacket(int primaryEffect, int secondaryEffect) {
        this.primaryEffect = primaryEffect;
        this.secondaryEffect = secondaryEffect;
    }

    public int getPrimaryEffect() {
        return this.primaryEffect;
    }

    public int getSecondaryEffect() {
        return this.secondaryEffect;
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
