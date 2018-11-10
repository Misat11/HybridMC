package misat11.hybrid.network.bedrock.packet;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;

import java.util.ArrayList;
import java.util.List;

import static misat11.hybrid.network.bedrock.BedrockUtil.readString;
import static misat11.hybrid.network.util.VarInts.readUnsignedInt;

@Data
public class PurchaseReceiptPacket implements BedrockPacket {
    private final List<String> receipts = new ArrayList<>();

    @Override
    public void encode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(ByteBuf buffer) {
        int receiptCount = readUnsignedInt(buffer);

        for (int i = 0; i < receiptCount; i++) {
            receipts.add(readString(buffer));
        }
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        handler.handle(this);
    }
}
