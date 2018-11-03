package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMetadataPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.SetEntityDataPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class EntityMetadataTranslator implements IDownstreamTranslator<ServerEntityMetadataPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityMetadataPacket packet) {
		WatchedEntity entity = session.getDownstream().getWatchedEntities().get((long) packet.getEntityId());
		if (entity == null) {
			return null;
		}
		// TODO

		return null;
	}
	
	public static SetEntityDataPacket create(long entityID) {
		SetEntityDataPacket sedp = new SetEntityDataPacket();
		sedp.setRuntimeEntityId(entityID);
		// TODO
		return sedp;
	}

}
