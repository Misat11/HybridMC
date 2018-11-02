package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3i;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.Position;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerBlockChangePacket;
import com.nukkitx.server.level.manager.LevelPaletteManager;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.UpdateBlockPacket;
import com.nukkitx.server.network.bedrock.packet.UpdateBlockPacket.DataLayer;

import misat11.hybrid.blockitems.BlockEntry;
import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class BlockChangeSingleTranslator implements IDownstreamTranslator<ServerBlockChangePacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerBlockChangePacket packet) {
		return generate(session.getServer().getPaletteManager(), convertPosition(packet.getRecord().getPosition()),
				session.getServer().getBlockTranslator().blockPcToPe(packet.getRecord().getBlock().getId()));
	}

	public static UpdateBlockPacket[] generate(LevelPaletteManager palette, Vector3i position, BlockEntry entry) {
		List<UpdateBlockPacket> list = new ArrayList<>();
		UpdateBlockPacket ubp = new UpdateBlockPacket();
		ubp.setBlockPosition(position);
		ubp.setDataLayer(DataLayer.NORMAL);
		ubp.setRuntimeId(palette.fromLegacy(entry.getId(), (byte) entry.getData()));
		ubp.getFlags().addAll(UpdateBlockPacket.FLAG_ALL_PRIORITY);
		list.add(ubp);
		if (entry.canBeWaterLogged() || entry.getId() == 0) {
			UpdateBlockPacket water = new UpdateBlockPacket();
			water.setBlockPosition(position);
			water.setDataLayer(DataLayer.LIQUID);
			water.setRuntimeId(entry.isWaterlogged() ? palette.fromLegacy(9, (byte) 0) : 0);
			water.getFlags().addAll(UpdateBlockPacket.FLAG_ALL_PRIORITY);
			list.add(water);
		}
		return list.toArray(new UpdateBlockPacket[list.size()]);
	}

}
