package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;

import misat11.hybrid.blockitems.BlockEntry;
import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.level.SoundEvent;
import misat11.hybrid.level.manager.LevelPaletteManager;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.LevelSoundEventPacket;
import misat11.hybrid.network.bedrock.packet.UpdateBlockPacket;
import misat11.hybrid.network.bedrock.packet.UpdateBlockPacket.DataLayer;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerBlockChangePacket404;

public class BlockChangeSingleTranslator implements IDownstreamTranslator<ServerBlockChangePacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerBlockChangePacket404 packet) {
		// TODO block open
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
		if (entry.isCanBeWaterLogged() || entry.getId() == 0) {
			UpdateBlockPacket water = new UpdateBlockPacket();
			water.setBlockPosition(position);
			water.setDataLayer(DataLayer.LIQUID);
			water.setRuntimeId(entry.isWaterlogged() ? palette.fromLegacy(9, (byte) 0) : 0);
			water.getFlags().addAll(UpdateBlockPacket.FLAG_ALL_PRIORITY);
			list.add(water);
		}
		return list.toArray(new UpdateBlockPacket[list.size()]);
	}

    public static void build(HybridSession session, Vector3f pos, BlockEntry entry) {
        LevelSoundEventPacket pk = new LevelSoundEventPacket();
        pk.setSoundEvent(SoundEvent.PLACE);
        pk.setPosition(pos);
        pk.setExtraData(session.getServer().getPaletteManager().fromLegacy(entry.getId(), (byte) entry.getData()));
        pk.setPitch(1);
        session.sendImmediatePackage(pk);
    }

    public static boolean isDoor(int id) {
        return id == 64 || id == 193 || id == 194 || id == 195 || id == 196 || id == 197 || id == 71;
    }

    public static boolean isGate(int id) {
        return id == 107 || id == 183 || id == 184 || id == 185 || id == 186 || id == 187;
    }

    public static boolean isTrapdoor(int id) {
        return id == 96 || id == 167 || id == 400 || id == 401 || id == 402 || id == 403 || id == 404;
    }

}
