package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3f;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.AddPlayerPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.permission.CommandPermission;
import misat11.hybrid.permission.PlayerPermission;
import misat11.hybrid.util.Rotation;

public class SpawnNamedTranslator implements IDownstreamTranslator<ServerSpawnPlayerPacket> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnPlayerPacket packet) {
		AddPlayerPacket app = new AddPlayerPacket();
		app.setUuid(packet.getUUID());
		app.setUniqueEntityId(packet.getEntityId());
		app.setRuntimeEntityId(packet.getEntityId());
		app.setPosition(new Vector3f(packet.getX(), packet.getY(), packet.getZ()));
		app.setRotation(new Rotation(packet.getPitch(), packet.getYaw()));
		app.setMotion(new Vector3f(0, 0, 0));
		app.setPlatformChatId("");
		app.setDeviceId("");
		app.setCommandPermission(CommandPermission.NORMAL);
		app.setPlayerPermission(PlayerPermission.MEMBER);
		app.setUsername(session.getDownstream().getPlayerListEntryCache().get(packet.getUUID()).getProfile().getName());
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), EntityType.PLAYER.getType(), (float) packet.getX(),
						(float) packet.getY(), (float) packet.getZ(), packet.getYaw(), packet.getPitch()));
		return new BedrockPacket[] { app };
	}

}
