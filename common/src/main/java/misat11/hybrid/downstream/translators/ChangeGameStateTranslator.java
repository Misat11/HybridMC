package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerNotifyClientPacket;
import com.nukkitx.api.permission.CommandPermission;
import com.nukkitx.api.permission.PlayerPermission;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.AdventureSettingsPacket;
import com.nukkitx.server.network.bedrock.packet.LevelEventPacket;
import com.nukkitx.server.network.bedrock.packet.LevelEventPacket.Event;
import com.nukkitx.server.network.bedrock.packet.SetPlayerGameTypePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class ChangeGameStateTranslator implements IDownstreamTranslator<ServerNotifyClientPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerNotifyClientPacket packet) {
		List<BedrockPacket> packets = new ArrayList<>();
		switch (packet.getNotification()) {
		case AFFECTED_BY_ELDER_GUARDIAN:
			break;
		case ARROW_HIT_PLAYER:
			break;
		case CHANGE_GAMEMODE:
			GameMode gamemode = (GameMode) packet.getValue();
			SetPlayerGameTypePacket spgtp = new SetPlayerGameTypePacket();
			spgtp.setGamemode(StartGameTranslator.transl(gamemode));
			packets.add(spgtp);
			session.getDownstream().gamemode = gamemode;
			
			AdventureSettingsPacket asp = new AdventureSettingsPacket();
			asp.setCommandPermission(CommandPermission.NORMAL);
			asp.setPlayerPermission(PlayerPermission.MEMBER);
			asp.setUniqueEntityId(session.getDownstream().playerEntityId);
			asp.setCustomFlags(0);
			asp.setFlags2(0x1FF);
			asp.setFlags(StartGameTranslator.getGameModeFlags(gamemode) | StartGameTranslator.AUTOJUMP_ENABLED /* | can pe fly and flying*/);
			packets.add(asp);
			break;
		case DEMO_MESSAGE:
			break;
		case ENTER_CREDITS:
			break;
		case INVALID_BED:
			break;
		case RAIN_STRENGTH:
			break;
		case START_RAIN:
			LevelEventPacket startRain = new LevelEventPacket();
			startRain.setEvent(Event.START_RAIN);
			startRain.setData(60000);
			startRain.setPosition(new Vector3f(0,0,0));
			packets.add(startRain);
			break;
		case STOP_RAIN:
			LevelEventPacket stopRain = new LevelEventPacket();
			stopRain.setEvent(Event.STOP_RAIN);
			stopRain.setPosition(new Vector3f(0,0,0));
			packets.add(stopRain);
			break;
		case THUNDER_STRENGTH:
			break;
		}
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

}
