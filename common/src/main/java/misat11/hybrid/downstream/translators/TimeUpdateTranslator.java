package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTimePacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class TimeUpdateTranslator implements IDownstreamTranslator<ServerUpdateTimePacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerUpdateTimePacket packet) {
		return null;
	}

}
