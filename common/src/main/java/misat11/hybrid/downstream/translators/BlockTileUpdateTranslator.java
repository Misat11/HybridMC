package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTileEntityPacket;
import com.nukkitx.server.network.bedrock.BedrockPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class BlockTileUpdateTranslator implements IDownstreamTranslator<ServerUpdateTileEntityPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerUpdateTileEntityPacket packet) {
		return null;
	}

}
