package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class PlayerListSetEntryTranslator implements IDownstreamTranslator<ServerPlayerListEntryPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerListEntryPacket packet) {
		return null;
	}

}
