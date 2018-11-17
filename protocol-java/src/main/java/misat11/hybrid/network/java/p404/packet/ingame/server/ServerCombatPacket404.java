package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.CombatState;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerCombatPacket;

import java.io.IOException;

@Getter
public class ServerCombatPacket404 extends MinecraftPacket implements ServerCombatPacket {
    private CombatState combatState;
    private int entityId;
    private int duration;
    private int playerId;
    private Message message;

    public ServerCombatPacket404() {
        this.combatState = CombatState.ENTER_COMBAT;
    }

    public ServerCombatPacket404(int entityId, int duration) {
        this.combatState = CombatState.END_COMBAT;
        this.entityId = entityId;
        this.duration = duration;
    }

    public ServerCombatPacket404(int entityId, int playerId, Message message) {
        this.combatState = CombatState.ENTITY_DEAD;
        this.entityId = entityId;
        this.playerId = playerId;
        this.message = message;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.combatState = MagicValues404.key(CombatState.class, in.readVarInt());
        if(this.combatState == CombatState.END_COMBAT) {
            this.duration = in.readVarInt();
            this.entityId = in.readInt();
        } else if(this.combatState == CombatState.ENTITY_DEAD) {
            this.playerId = in.readVarInt();
            this.entityId = in.readInt();
            this.message = Message.fromString(in.readString());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues404.value(Integer.class, this.combatState));
        if(this.combatState == CombatState.END_COMBAT) {
            out.writeVarInt(this.duration);
            out.writeInt(this.entityId);
        } else if(this.combatState == CombatState.ENTITY_DEAD) {
            out.writeVarInt(this.playerId);
            out.writeInt(this.entityId);
            out.writeString(this.message.toJsonString());
        }
    }
}
