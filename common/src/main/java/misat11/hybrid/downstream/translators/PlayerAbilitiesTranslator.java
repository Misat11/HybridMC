package misat11.hybrid.downstream.translators;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket;
import com.nukkitx.api.permission.CommandPermission;
import com.nukkitx.api.permission.PlayerPermission;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.AdventureSettingsPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class PlayerAbilitiesTranslator implements IDownstreamTranslator<ServerPlayerAbilitiesPacket>{

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerPlayerAbilitiesPacket packet) {
		session.getDownstream().getMovementCache().updatePeFlying(packet.getCanFly(), packet.getFlying());
		AdventureSettingsPacket asp = new AdventureSettingsPacket();
		asp.setCommandPermission(CommandPermission.NORMAL);
		asp.setPlayerPermission(PlayerPermission.MEMBER);
		asp.setUniqueEntityId(session.getDownstream().playerEntityId);
		asp.setCustomFlags(0);
		asp.setFlags2(0x1FF);
		asp.setFlags(StartGameTranslator.getGameModeFlags(session.getDownstream().gamemode, session.getDownstream().getMovementCache()));
		return new BedrockPacket[] {asp};
	}

}
