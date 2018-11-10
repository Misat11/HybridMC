package com.nukkitx.server.network.bedrock.packet;

import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import static com.nukkitx.server.network.bedrock.BedrockUtil.writeString;
import static com.nukkitx.server.network.util.VarInts.writeInt;

@Data
public class SetDisplayObjectivePacket implements BedrockPacket {
    private String slotName;
    private String name;
    private String displayName;
    private String criteriaName;
    private int order;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, slotName);
        writeString(buffer, name);
        writeString(buffer, displayName);
        writeString(buffer, criteriaName);
        writeInt(buffer, order);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Client bound only
    }
}
