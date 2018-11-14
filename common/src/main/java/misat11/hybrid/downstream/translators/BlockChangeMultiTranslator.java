package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.UpdateBlockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.data.game.world.block.BlockChangeRecord;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerMultiBlockChangePacket;

public class BlockChangeMultiTranslator implements IDownstreamTranslator<ServerMultiBlockChangePacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerMultiBlockChangePacket packet) {
		List<UpdateBlockPacket> list = new ArrayList<>();
		for (BlockChangeRecord record : packet.getRecords()) {
			list.addAll(Arrays.asList(
					BlockChangeSingleTranslator.generate(session.getServer().getPaletteManager(), convertPosition(record.getPosition()),
							session.getServer().getBlockTranslator().blockPcToPe(record.getBlock().getId()))));
		}
		return list.toArray(new UpdateBlockPacket[list.size()]);
	}

}
