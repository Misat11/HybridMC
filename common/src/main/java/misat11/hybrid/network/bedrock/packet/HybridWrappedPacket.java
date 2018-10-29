package misat11.hybrid.network.bedrock.packet;

import com.nukkitx.network.raknet.CustomRakNetPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import io.netty.buffer.ByteBuf;
import misat11.hybrid.network.bedrock.session.HybridSession;

import java.util.ArrayList;
import java.util.List;

public class HybridWrappedPacket implements CustomRakNetPacket<HybridSession> {

	private final List<BedrockPacket> packets = new ArrayList<>();
	private boolean encrypted;
	private ByteBuf batched;
	private ByteBuf payload;

	public List<BedrockPacket> getPackets() {
		return packets;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public ByteBuf getBatched() {
		return batched;
	}

	public void setBatched(ByteBuf batched) {
		this.batched = batched;
	}

	public ByteBuf getPayload() {
		return payload;
	}

	public void setPayload(ByteBuf payload) {
		this.payload = payload;
	}

	@Override
	public void encode(ByteBuf buffer) {
		buffer.writeBytes(payload);
		payload.release();
	}

	@Override
	public void decode(ByteBuf buffer) {
		payload = buffer.readBytes(buffer.readableBytes());
	}

	@Override
	public void handle(HybridSession session) throws Exception {
		session.onWrappedPacket(this);
	}
}
