package misat11.hybrid.downstream;

import com.flowpowered.math.vector.Vector3i;
import com.github.steveice10.packetlib.packet.Packet;

import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.data.game.entity.metadata.Position;

public interface IDownstreamTranslator<P extends Packet> {
	
	public BedrockPacket[] translate(HybridSession session, P packet);
	
	default Vector3i convertPosition(Position position) {
		return new Vector3i(position.getX(), position.getY(), position.getZ());
	}

}
