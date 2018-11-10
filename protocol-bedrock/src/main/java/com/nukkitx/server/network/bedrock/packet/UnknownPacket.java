package com.nukkitx.server.network.bedrock.packet;

import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import lombok.Data;

@Data
public class UnknownPacket implements BedrockPacket {
    private short id;
    private ByteBuf payload;

    @Override
    public void encode(ByteBuf buffer) {
        buffer.writeShort(id);
        buffer.writeBytes(payload);
    }

    @Override
    public void decode(ByteBuf buffer) {
        id = buffer.readUnsignedByte();
        payload = buffer.readBytes(buffer.readableBytes());
    }

    @Override
    public String toString() {
        return "UNKNOWN - " + Integer.toHexString(id) + " - Hex: " + ByteBufUtil.hexDump(payload);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        payload.release();
    }
}
