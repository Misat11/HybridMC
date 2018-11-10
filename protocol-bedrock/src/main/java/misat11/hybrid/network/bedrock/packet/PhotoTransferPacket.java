package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import static misat11.hybrid.network.bedrock.BedrockUtil.readString;
import static misat11.hybrid.network.bedrock.BedrockUtil.writeString;
import static misat11.hybrid.network.util.VarInts.readUnsignedInt;
import static misat11.hybrid.network.util.VarInts.writeUnsignedInt;

public class PhotoTransferPacket implements BedrockPacket {
    private String name;
    private byte[] data;
    private String bookId;

    @Override
    public void encode(ByteBuf buffer) {
        writeString(buffer, name);
        writeUnsignedInt(buffer, data.length);
        buffer.writeBytes(data);
        writeString(buffer, bookId);
    }

    @Override
    public void decode(ByteBuf buffer) {
        name = readString(buffer);
        data = new byte [readUnsignedInt(buffer)];
        buffer.readBytes(data);
        bookId = readString(buffer);
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
