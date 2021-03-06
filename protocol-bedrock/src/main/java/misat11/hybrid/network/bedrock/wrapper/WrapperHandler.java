package misat11.hybrid.network.bedrock.wrapper;

import com.nukkitx.network.PacketCodec;

import io.netty.buffer.ByteBuf;
import misat11.hybrid.network.bedrock.BedrockPacket;

import java.util.Collection;
import java.util.List;

public interface WrapperHandler {
    default ByteBuf compressPackets(PacketCodec<BedrockPacket> packetCodec, Collection<BedrockPacket> packets) {
        return compressPackets(packetCodec, packets.toArray(new BedrockPacket[0]));
    }

    ByteBuf compressPackets(PacketCodec<BedrockPacket> packetCodec, BedrockPacket... packets);

    List<BedrockPacket> decompressPackets(PacketCodec<BedrockPacket> packetCodec, ByteBuf compressed);
}