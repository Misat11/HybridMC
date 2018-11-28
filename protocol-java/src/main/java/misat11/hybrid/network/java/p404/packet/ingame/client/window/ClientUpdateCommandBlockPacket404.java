package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.world.block.CommandBlockMode;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.window.ClientUpdateCommandBlockPacket;

import java.io.IOException;

@Getter
public class ClientUpdateCommandBlockPacket404 extends MinecraftPacket implements ClientUpdateCommandBlockPacket {
    private Position position;
    private String command;
    private CommandBlockMode mode;
    private boolean doesTrackOutput;
    private boolean isConditional;
    private boolean isAutomatic;

    @SuppressWarnings("unused")
    private ClientUpdateCommandBlockPacket404() {
    }

    public ClientUpdateCommandBlockPacket404(Position position, String command, CommandBlockMode mode,
                                          boolean doesTrackOutput, boolean isConditional, boolean isAutomatic) {
        this.position = position;
        this.command = command;
        this.mode = mode;
        this.doesTrackOutput = doesTrackOutput;
        this.isConditional = isConditional;
        this.isAutomatic = isAutomatic;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = getUtil().readPosition(in);
        this.command = in.readString();
        this.mode = getMagic().key(CommandBlockMode.class, in.readVarInt());
        int flags = in.readUnsignedByte();
        this.doesTrackOutput = (flags & 0x01) != 0;
        this.isConditional = (flags & 0x02) != 0;
        this.isAutomatic = (flags & 0x04) != 0;
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writePosition(out, this.position);
        out.writeString(this.command);
        out.writeVarInt(getMagic().value(Integer.class, this.mode));
        int flags = 0;
        if (this.doesTrackOutput) flags |= 0x01;
        if (this.isConditional) flags |= 0x02;
        if (this.isAutomatic) flags |= 0x04;
        out.writeByte(flags);
    }
}
