package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityCollectItemPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.TakeItemEntityPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class CollectEffectTranslator implements IDownstreamTranslator<ServerEntityCollectItemPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerEntityCollectItemPacket packet) {
		TakeItemEntityPacket tiep = new TakeItemEntityPacket();
		tiep.setItemRuntimeEntityId(packet.getCollectedEntityId());
		tiep.setRuntimeEntityId(packet.getCollectorEntityId());
		return new BedrockPacket[] {tiep};
	}

}
