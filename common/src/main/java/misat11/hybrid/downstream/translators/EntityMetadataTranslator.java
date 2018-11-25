package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetEntityDataPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.entity.ServerEntityMetadataPacket;

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
