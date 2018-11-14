package misat11.hybrid.downstream.translators;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.SetTimePacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUpdateTimePacket;

public class TimeUpdateTranslator implements IDownstreamTranslator<ServerUpdateTimePacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerUpdateTimePacket packet) {
		SetTimePacket stp = new SetTimePacket();
		stp.setTime((int) Math.abs(packet.getTime()));
		return new BedrockPacket[] { stp };
	}

}
