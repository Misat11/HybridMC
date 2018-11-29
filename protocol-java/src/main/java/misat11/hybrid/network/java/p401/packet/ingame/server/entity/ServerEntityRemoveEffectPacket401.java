package misat11.hybrid.network.java.p401.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.entity.Effect;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityRemoveEffectPacket;

import java.io.IOException;

@Getter
public class ServerEntityRemoveEffectPacket401 extends MinecraftPacket implements ServerEntityRemoveEffectPacket {
    private int entityId;
    private Effect effect;

    @SuppressWarnings("unused")
    private ServerEntityRemoveEffectPacket401() {
    }

    public ServerEntityRemoveEffectPacket401(int entityId, Effect effect) {
        this.entityId = entityId;
        this.effect = effect;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.effect = getMagic().key(Effect.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeByte(getMagic().value(Integer.class, this.effect));
    }
}
