package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.TakeItemEntityPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityCollectItemPacket;

public class CollectEffectTranslator implements IDownstreamTranslator<ServerEntityCollectItemPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityCollectItemPacket packet) {
		TakeItemEntityPacket tiep = new TakeItemEntityPacket();
		tiep.setItemRuntimeEntityId(packet.getCollectedEntityId());
		tiep.setRuntimeEntityId(packet.getCollectorEntityId());
		return new BedrockPacket[] {tiep};
	}

}
