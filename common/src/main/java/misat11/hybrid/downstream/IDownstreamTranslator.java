package misat11.hybrid.downstream;

import com.flowpowered.math.vector.Vector3i;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.Position;
import com.github.steveice10.packetlib.packet.Packet;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public interface IDownstreamTranslator<P extends Packet> {
	
	public BedrockPacket[] translate(HybridSession session, P packet);
	
	default Vector3i convertPosition(Position position) {
		return new Vector3i(position.getX(), position.getY(), position.getZ());
	}

}
