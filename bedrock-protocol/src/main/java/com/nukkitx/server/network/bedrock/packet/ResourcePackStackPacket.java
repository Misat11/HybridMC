package com.nukkitx.server.network.bedrock.packet;

import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;

public class ResourcePackStackPacket implements BedrockPacket {

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeBoolean(false);
        buffer.writeShortLE(0);
        buffer.writeShortLE(0);
        // TODO add resourcepack support
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound
    }
}
