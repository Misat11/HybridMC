package com.nukkitx.server.network.bedrock.packet;

import com.google.common.collect.EnumHashBiMap;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import static com.nukkitx.server.network.bedrock.BedrockUtil.readRuntimeEntityId;
import static com.nukkitx.server.network.bedrock.BedrockUtil.writeRuntimeEntityId;
import static com.nukkitx.server.network.util.VarInts.readInt;
import static com.nukkitx.server.network.util.VarInts.writeInt;

@Data
public class AnimatePacket implements BedrockPacket {
    private static final EnumHashBiMap<Animation, Integer> actions = EnumHashBiMap.create(Animation.class);
    private float rowingTime;
    private Animation action;
    private long runtimeEntityId;

    static {
        actions.put(Animation.SWING_ARM, 1);
        actions.put(Animation.WAKE_UP, 2);
        actions.put(Animation.CRITICAL_HIT, 3);
        actions.put(Animation.MAGIC_CRITICAL_HIT, 4);
        actions.put(Animation.ROW_RIGHT, 128);
        actions.put(Animation.ROW_LEFT, 129);
    }

    @Override
    public void encode(ByteBuf buffer) {
        writeInt(buffer, actions.get(action));
        writeRuntimeEntityId(buffer, runtimeEntityId);
        if (action == Animation.ROW_RIGHT || action == Animation.ROW_LEFT) {
            buffer.writeFloatLE(rowingTime);
        }
    }

    @Override
    public void decode(ByteBuf buffer) {
        action = actions.inverse().get(readInt(buffer));
        runtimeEntityId = readRuntimeEntityId(buffer);
        if (action == Animation.ROW_RIGHT || action == Animation.ROW_LEFT) {
            rowingTime = buffer.readFloatLE();
        }
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
    
    public enum Animation {
        SWING_ARM,
        WAKE_UP,
        CRITICAL_HIT,
        MAGIC_CRITICAL_HIT,
        ROW_RIGHT,
        ROW_LEFT
    }
}
