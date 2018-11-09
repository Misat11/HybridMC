package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.nukkitx.api.permission.CommandPermission;
import com.nukkitx.api.permission.PlayerPermission;
import com.nukkitx.api.util.Rotation;
import com.nukkitx.server.entity.EntityType;
import com.nukkitx.server.level.NukkitLevelData;
import com.nukkitx.server.network.bedrock.BedrockPacket;
import com.nukkitx.server.network.bedrock.packet.AdventureSettingsPacket;
import com.nukkitx.server.network.bedrock.packet.ChunkRadiusUpdatePacket;
import com.nukkitx.server.network.bedrock.packet.PlayStatusPacket;
import com.nukkitx.server.network.bedrock.packet.PlayStatusPacket.Status;
import com.nukkitx.server.permission.NukkitAbilities;
import com.nukkitx.server.network.bedrock.packet.ResourcePackStackPacket;
import com.nukkitx.server.network.bedrock.packet.StartGamePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.downstream.cache.MovementCache;
import misat11.hybrid.network.bedrock.session.HybridSession;

public class StartGameTranslator implements IDownstreamTranslator<ServerJoinGamePacket> {

	public static final int ADVENTURE_MODE_ENABLED = 0x1;
	public static final int PVP_DISABLED = 0x2;
	public static final int PVE_DISABLED = 0x4;
	public static final int AUTOJUMP_ENABLED = 0x20;
	public static final int ALLOW_FLIGHT = 0x40;
	public static final int NOCLIP_ENABLED = 0x80;
	public static final int FLYING = 0x200;

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerJoinGamePacket packet) {
		session.getDownstream().playerEntityId = packet.getEntityId();
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), EntityType.PLAYER.getType()));
		List<BedrockPacket> packets = new ArrayList<BedrockPacket>();
		ResourcePackStackPacket rpsp = new ResourcePackStackPacket();
		packets.add(rpsp);
		StartGamePacket sgp = new StartGamePacket();
		sgp.setUniqueEntityId(packet.getEntityId());
		sgp.setRuntimeEntityId(packet.getEntityId());
		sgp.setGamemode(transl(packet.getGameMode()));
		sgp.setPlayerPosition(new Vector3f());
		sgp.setRotation(new Rotation(0, 0));
		sgp.setLevelId("");
		sgp.setWorldName("");
		sgp.setPremiumWorldTemplateId("");
		sgp.setMultiplayerCorrelationId("");
		NukkitLevelData settings = new NukkitLevelData() { // TODO change dimension
		};
		settings.setDefaultAbilities(new NukkitAbilities());
		settings.setDefaultSpawn(new Vector3f(0, 0, 0));
		sgp.setLevelSettings(settings);
		packets.add(sgp);

		AdventureSettingsPacket asp = new AdventureSettingsPacket();
		asp.setCommandPermission(CommandPermission.NORMAL);
		asp.setPlayerPermission(PlayerPermission.MEMBER);
		asp.setUniqueEntityId(packet.getEntityId());
		asp.setCustomFlags(0);
		asp.setFlags2(0x1FF);
		asp.setFlags(getGameModeFlags(packet.getGameMode(), session.getDownstream().getMovementCache()));
		packets.add(asp);
		session.getDownstream().gamemode = packet.getGameMode();

		// TODO pe entity metadata

		PlayStatusPacket psp = new PlayStatusPacket();
		psp.setStatus(Status.PLAYER_SPAWN);
		packets.add(psp);

		ChunkRadiusUpdatePacket crup = new ChunkRadiusUpdatePacket();
		crup.setRadius(8);
		packets.add(crup);

		session.getDownstream().switchFakePos = !session.getDownstream().switchFakePos;
		ChangeDimensionTranslator.addFakeChunksAndPos(packets, session.getDownstream().switchFakePos ? 20 : 30,
				packet.getEntityId());

		session.getDownstream().switchFakePos = !session.getDownstream().switchFakePos;
		ChangeDimensionTranslator.create(
				packet.getDimension() != ChangeDimensionTranslator.OVERWORLD ? ChangeDimensionTranslator.OVERWORLD
						: ChangeDimensionTranslator.THE_END,
				session.getDownstream().switchFakePos ? 20 : 30, packet.getEntityId());

		session.getDownstream().switchFakePos = !session.getDownstream().switchFakePos;
		ChangeDimensionTranslator.create(packet.getDimension(), session.getDownstream().switchFakePos ? 20 : 30,
				packet.getEntityId());
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

	public static com.nukkitx.api.util.GameMode transl(GameMode gm) {
		switch (gm) {
		case ADVENTURE:
			return com.nukkitx.api.util.GameMode.ADVENTURE;
		case CREATIVE:
			return com.nukkitx.api.util.GameMode.CREATIVE;
		case SPECTATOR:
			return com.nukkitx.api.util.GameMode.SPECTATOR;
		case SURVIVAL:
		default:
			return com.nukkitx.api.util.GameMode.SURVIVAL;
		}
	}

	public static int getGameModeFlags(GameMode gamemode, MovementCache cache) {
		switch (gamemode) {
		case ADVENTURE: {
			return ADVENTURE_MODE_ENABLED;
		}
		case SPECTATOR: {
			return PVP_DISABLED | PVE_DISABLED | ALLOW_FLIGHT | FLYING | NOCLIP_ENABLED;
		}
		default: {
			return (cache.canPeFly() ? ALLOW_FLIGHT : 0) | (cache.isPeFlying() ? FLYING : 0);
		}
		}
	}

}
