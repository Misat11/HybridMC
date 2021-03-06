package misat11.hybrid.network.bedrock.wrapper;

import com.nukkitx.network.PacketCodec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.Cleanup;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.UnknownPacket;
import misat11.hybrid.network.util.VarInts;
import misat11.hybrid.network.util.Zlib;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class DefaultWrapperHandler implements WrapperHandler {
    public static final DefaultWrapperHandler DEFAULT = new DefaultWrapperHandler();
    public static final DefaultWrapperHandler HIGH_COMPRESSION = new DefaultWrapperHandler();

    private final Zlib zlib;

    private DefaultWrapperHandler() {
        zlib = Zlib.DEFAULT;
    }

    public DefaultWrapperHandler(int compression) {
        zlib = new Zlib(compression);
    }

    @Override
    public ByteBuf compressPackets(PacketCodec<BedrockPacket> packetCodec, BedrockPacket... packets) {
        ByteBuf source = PooledByteBufAllocator.DEFAULT.directBuffer();
        try {
            for (BedrockPacket packet : packets) {
                @Cleanup("release") ByteBuf packetBuf = packetCodec.tryEncode(packet);
                VarInts.writeUnsignedInt(source, packetBuf.readableBytes());
                source.writeBytes(packetBuf);
            }
            return zlib.deflate(source);
        } catch (DataFormatException e) {
            throw new RuntimeException("Unable to deflate buffer data", e);
        } finally {
            source.release();
        }
    }

    @Override
    public List<BedrockPacket> decompressPackets(PacketCodec<BedrockPacket> packetCodec, ByteBuf compressed) {
        List<BedrockPacket> packets = new ArrayList<>();
        ByteBuf decompressed = null;
        try {
            try {
                decompressed = zlib.inflate(compressed);
            } catch (Exception e) {
                throw new RuntimeException("Unable to decompress packet", e);
            }
            while (decompressed.isReadable()) {
                int length = VarInts.readUnsignedInt(decompressed);
                ByteBuf data = decompressed.readSlice(length);

                if (!data.isReadable()) {
                    throw new DataFormatException("Contained packet is empty.");
                }

                BedrockPacket pkg = packetCodec.tryDecode(data);
                if (pkg != null) {
                    packets.add(pkg);
                } else {
                    data.readerIndex(0);
                    UnknownPacket unknown = new UnknownPacket();
                    unknown.decode(data);
                    packets.add(unknown);
                }
            }
        } catch (DataFormatException e) {
            throw new RuntimeException("Unable to inflate buffer data", e);
        } finally {
            if (decompressed != null) {
                decompressed.release();
            }
        }
        return packets;
    }
}
