package misat11.hybrid.network.java.p401.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientUpdateCommandBlockMinecartPacket;

import java.io.IOException;

@Getter
public class ClientUpdateCommandBlockMinecartPacket401 extends MinecraftPacket implements ClientUpdateCommandBlockMinecartPacket {
    private int entityId;
    private String command;
    private boolean doesTrackOutput;

    @SuppressWarnings("unused")
    private ClientUpdateCommandBlockMinecartPacket401() {
    }

    public ClientUpdateCommandBlockMinecartPacket401(int entityId, String command, boolean doesTrackOutput) {
        this.entityId = entityId;
        this.command = command;
        this.doesTrackOutput = doesTrackOutput;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.command = in.readString();
        this.doesTrackOutput = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeString(this.command);
        out.writeBoolean(this.doesTrackOutput);
    }
}
