package misat11.hybrid.network.java.p404.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.entity.player.CombatState;
import misat11.hybrid.network.java.pabstract.data.message.Message;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ServerCombatPacket extends MinecraftPacket {
    private CombatState state;
    private int entityId;
    private int duration;
    private int playerId;
    private Message message;

    public ServerCombatPacket() {
        this.state = CombatState.ENTER_COMBAT;
    }

    public ServerCombatPacket(int entityId, int duration) {
        this.state = CombatState.END_COMBAT;
        this.entityId = entityId;
        this.duration = duration;
    }

    public ServerCombatPacket(int entityId, int playerId, Message message) {
        this.state = CombatState.ENTITY_DEAD;
        this.entityId = entityId;
        this.playerId = playerId;
        this.message = message;
    }

    public CombatState getCombatState() {
        return this.state;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public Message getMessage() {
        return this.message;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.state = MagicValues404.key(CombatState.class, in.readVarInt());
        if(this.state == CombatState.END_COMBAT) {
            this.duration = in.readVarInt();
            this.entityId = in.readInt();
        } else if(this.state == CombatState.ENTITY_DEAD) {
            this.playerId = in.readVarInt();
            this.entityId = in.readInt();
            this.message = Message.fromString(in.readString());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues404.value(Integer.class, this.state));
        if(this.state == CombatState.END_COMBAT) {
            out.writeVarInt(this.duration);
            out.writeInt(this.entityId);
        } else if(this.state == CombatState.ENTITY_DEAD) {
            out.writeVarInt(this.playerId);
            out.writeInt(this.entityId);
            out.writeString(this.message.toJsonString());
        }
    }
}
