package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.RemoveEntityPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityDestroyPacket;

public class EntityDestroyTranslator implements IDownstreamTranslator<ServerEntityDestroyPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityDestroyPacket packet) {
		List<RemoveEntityPacket> list = new ArrayList<>();
		Map<Long, WatchedEntity> watched = session.getDownstream().getWatchedEntities();
		for (int eid : packet.getEntityIds()) {
			RemoveEntityPacket rep = new RemoveEntityPacket();
			rep.setUniqueEntityId(eid);
			list.add(rep);
			if (watched.containsKey((long) eid)) {
				watched.remove((long) eid);
			}
		}
		return list.toArray(new RemoveEntityPacket[list.size()]);
	}

}
