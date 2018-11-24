package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.TakeItemEntityPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityCollectItemPacket404;

public class CollectEffectTranslator implements IDownstreamTranslator<ServerEntityCollectItemPacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityCollectItemPacket404 packet) {
		TakeItemEntityPacket tiep = new TakeItemEntityPacket();
		tiep.setItemRuntimeEntityId(packet.getCollectedEntityId());
		tiep.setRuntimeEntityId(packet.getCollectorEntityId());
		return new BedrockPacket[] {tiep};
	}

}
