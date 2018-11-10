package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTileEntityPacket;
import com.nukkitx.nbt.CompoundTagBuilder;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.BlockEntityDataPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.typeremapper.TileRemapper;

public class BlockTileUpdateTranslator implements IDownstreamTranslator<ServerUpdateTileEntityPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerUpdateTileEntityPacket packet) {
		BlockEntityDataPacket bedp = new BlockEntityDataPacket();
		bedp.setBlockPostion(convertPosition(packet.getPosition()));
		CompoundTagBuilder tag = TileRemapper.remap(packet.getType(), packet.getNBT());
		if (tag == null) {
			return null; // Tag is null
		}
		tag.intTag("x", packet.getPosition().getX());
		tag.intTag("y", packet.getPosition().getY());
		tag.intTag("z", packet.getPosition().getZ());
		bedp.setData(tag.buildRootTag());
		return new BedrockPacket[] {bedp};
	}

}
