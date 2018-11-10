package misat11.hybrid.downstream.translators;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.downstream.cache.MovementCache;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.level.LevelSettings;
import misat11.hybrid.level.data.Dimension;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.AdventureSettingsPacket;
import misat11.hybrid.network.bedrock.packet.ChunkRadiusUpdatePacket;
import misat11.hybrid.network.bedrock.packet.PlayStatusPacket;
import misat11.hybrid.network.bedrock.packet.ResourcePackStackPacket;
import misat11.hybrid.network.bedrock.packet.StartGamePacket;
import misat11.hybrid.network.bedrock.packet.PlayStatusPacket.Status;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.permission.CommandPermission;
import misat11.hybrid.permission.NukkitAbilities;
import misat11.hybrid.permission.PlayerPermission;
import misat11.hybrid.util.Rotation;

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
		LevelSettings settings = new LevelSettings() { // TODO change dimension
		};
		settings.setDimension(Dimension.values()[ChangeDimensionTranslator.getPeDimensionId(packet.getDimension())]);
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
		ChangeDimensionTranslator.create(ChangeDimensionTranslator.getPeDimensionId(packet.getDimension()), session.getDownstream().switchFakePos ? 20 : 30,
				packet.getEntityId());
		return packets.toArray(new BedrockPacket[packets.size()]);
	}

	public static misat11.hybrid.util.GameMode transl(GameMode gm) {
		switch (gm) {
		case CREATIVE:
			return misat11.hybrid.util.GameMode.CREATIVE;
		default:
			return misat11.hybrid.util.GameMode.SURVIVAL;
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
