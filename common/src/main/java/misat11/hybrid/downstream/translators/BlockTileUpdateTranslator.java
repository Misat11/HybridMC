package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTileEntityPacket;
import com.nukkitx.nbt.tag.CompoundTag;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.BlockEntityDataPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.typeremapper.TileRemapper;

public class BlockTileUpdateTranslator implements IDownstreamTranslator<ServerUpdateTileEntityPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerUpdateTileEntityPacket packet) {
		BlockEntityDataPacket bedp = new BlockEntityDataPacket();
		bedp.setBlockPostion(convertPosition(packet.getPosition()));
		CompoundTag tag = TileRemapper.remap(packet.getType(), packet.getNBT());
		if (tag == null) {
			return null; // Tag is null
		}
		tag.tagInt("x", packet.getPosition().getX());
		tag.tagInt("y", packet.getPosition().getY());
		tag.tagInt("z", packet.getPosition().getZ());
		bedp.setData(tag);
		return new BedrockPacket[] {bedp};
	}

}
