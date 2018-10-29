package misat11.hybrid.downstream;

import com.github.steveice10.packetlib.packet.Packet;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public interface IDownstreamTranslator<P extends Packet> {
	
	public BedrockPacket[] translate(HybridSession session, P packet);

}
