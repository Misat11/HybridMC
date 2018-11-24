package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetEntityDataPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityMetadataPacket404;

public class EntityMetadataTranslator implements IDownstreamTranslator<ServerEntityMetadataPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityMetadataPacket404 packet) {
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
