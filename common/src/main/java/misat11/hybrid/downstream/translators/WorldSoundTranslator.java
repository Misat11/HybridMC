package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3i;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlayBuiltinSoundPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.SoundTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.PlaySoundPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class WorldSoundTranslator implements IDownstreamTranslator<ServerPlayBuiltinSoundPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayBuiltinSoundPacket packet) {
		PlaySoundPacket psp = new PlaySoundPacket();
		psp.setBlockPosition(new Vector3i(packet.getX(), packet.getY(), packet.getZ()));
		psp.setPitch(packet.getPitch());
		psp.setVolume(packet.getVolume());
		if (!SoundTranslator.isIgnored(packet.getSound()) && SoundTranslator.isTranslatable(packet.getSound())) {
			psp.setSound(SoundTranslator.translate(packet.getSound()));
			return new BedrockPacket[] {psp};
		}
		return null;
	}

}
