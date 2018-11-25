package misat11.hybrid.downstream;

import com.flowpowered.math.vector.Vector3i;

import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface IDownstreamTranslator<P extends IMinecraftPacket> {
	
	public BedrockPacket[] translate(HybridSession session, P packet);
	
	default Vector3i convertPosition(Position position) {
		return new Vector3i(position.getX(), position.getY(), position.getZ());
	}

}
