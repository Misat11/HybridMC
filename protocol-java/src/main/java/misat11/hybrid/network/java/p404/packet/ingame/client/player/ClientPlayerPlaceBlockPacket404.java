package misat11.hybrid.network.java.p404.packet.ingame.client.player;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.Hand;
import misat11.hybrid.network.java.pabstract.data.game.world.block.BlockFace;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.client.player.ClientPlayerPlaceBlockPacket;

import java.io.IOException;

@Getter 
public class ClientPlayerPlaceBlockPacket404 extends MinecraftPacket implements ClientPlayerPlaceBlockPacket {
    private Position position;
    private BlockFace face;
    private Hand hand;
    private float cursorX;
    private float cursorY;
    private float cursorZ;

    @SuppressWarnings("unused")
    private ClientPlayerPlaceBlockPacket404() {
    }

    public ClientPlayerPlaceBlockPacket404(Position position, BlockFace face, Hand hand, float cursorX, float cursorY, float cursorZ) {
        this.position = position;
        this.face = face;
        this.hand = hand;
        this.cursorX = cursorX;
        this.cursorY = cursorY;
        this.cursorZ = cursorZ;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = getUtil().readPosition(in);
        this.face = getMagic().key(BlockFace.class, in.readVarInt());
        this.hand = getMagic().key(Hand.class, in.readVarInt());
        this.cursorX = in.readFloat();
        this.cursorY = in.readFloat();
        this.cursorZ = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
    	getUtil().writePosition(out, this.position);
        out.writeVarInt(getMagic().value(Integer.class, this.face));
        out.writeVarInt(getMagic().value(Integer.class, this.hand));
        out.writeFloat(this.cursorX);
        out.writeFloat(this.cursorY);
        out.writeFloat(this.cursorZ);
    }
}
