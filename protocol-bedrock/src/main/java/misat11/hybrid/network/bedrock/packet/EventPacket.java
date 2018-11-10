package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.readUniqueEntityId;
import static misat11.hybrid.network.util.VarInts.readInt;

@Data
public class EventPacket implements BedrockPacket {
    private long uniqueEntityId;
    private int data;
    private Type type;
    //private EventDetails details; Haven't quite figured this out yet.

    @Override
    public void encode(ByteBuf buffer) {
    }

    @Override
    public void decode(ByteBuf buffer) {
        uniqueEntityId = readUniqueEntityId(buffer);
        data = readInt(buffer);
        type = Type.values()[buffer.readByte()];

        //TODO: Figure out what the data after does.
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }

    public enum Type {
        ACHIEVEMENT_AWARDED,
        ENTITY_INTERACT,
        PORTAL_BUILT,
        PORTAL_USED,
        MOB_KILLED,
        CAULDRON_USED,
        PLAYER_DEATH,
        BOSS_KILLED,
        AGENT_COMMAND,
        AGENT_CREATED,
        PATTERN_REMOVED,
        COMMAND_EXECUTED,
        FISH_BUCKETED
    }
}
