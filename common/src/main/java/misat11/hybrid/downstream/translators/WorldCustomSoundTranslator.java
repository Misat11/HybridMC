package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3i;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.SoundTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.PlaySoundPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlaySoundPacket404;
import misat11.hybrid.network.java.pabstract.data.game.world.sound.BuiltinSound;

public class WorldCustomSoundTranslator implements IDownstreamTranslator<ServerPlaySoundPacket404>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlaySoundPacket404 packet) {
		PlaySoundPacket psp = new PlaySoundPacket();
		psp.setBlockPosition(new Vector3i(packet.getX(), packet.getY(), packet.getZ()));
		psp.setPitch(packet.getPitch());
		psp.setVolume(packet.getVolume());
		if (packet.getSound() instanceof BuiltinSound) {
			BuiltinSound sound = (BuiltinSound) packet.getSound();
			if (!SoundTranslator.isIgnored(sound) && SoundTranslator.isTranslatable(sound)) {
				psp.setSound(SoundTranslator.translate(sound));
				return new BedrockPacket[] {psp};
			}
		}
		return null;
	}

}
