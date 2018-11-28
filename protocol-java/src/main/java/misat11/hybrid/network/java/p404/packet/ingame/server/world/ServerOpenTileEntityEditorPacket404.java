package misat11.hybrid.network.java.p404.packet.ingame.server.world;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.world.ServerOpenTileEntityEditorPacket;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerOpenTileEntityEditorPacket404 extends MinecraftPacket implements ServerOpenTileEntityEditorPacket {
    private Position position;

    @Override
    public void read(NetInput in) throws IOException {
        this.position = getUtil().readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writePosition(out, this.position);
    }
}
