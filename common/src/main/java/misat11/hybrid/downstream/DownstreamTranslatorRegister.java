package misat11.hybrid.downstream;

import java.util.HashMap;
import java.util.Map;

import com.github.steveice10.packetlib.packet.Packet;

import misat11.hybrid.downstream.translators.AnimationPacketTranslator;
import misat11.hybrid.downstream.translators.BlockChangeMultiTranslator;
import misat11.hybrid.downstream.translators.BlockChangeSingleTranslator;
import misat11.hybrid.downstream.translators.BlockTileUpdateTranslator;
import misat11.hybrid.downstream.translators.ChangeDimensionTranslator;
import misat11.hybrid.downstream.translators.ChangeGameStateTranslator;
import misat11.hybrid.downstream.translators.ChatTranslator;
import misat11.hybrid.downstream.translators.ChunkTranslator;
import misat11.hybrid.downstream.translators.ClosedWindowTranslator;
import misat11.hybrid.downstream.translators.CollectEffectTranslator;
import misat11.hybrid.downstream.translators.CustomPayloadTranslator;
import misat11.hybrid.downstream.translators.EntityDeltaPositionRotationTranslator;
import misat11.hybrid.downstream.translators.EntityDestroyTranslator;
import misat11.hybrid.downstream.translators.EntityEffectAddTranslator;
import misat11.hybrid.downstream.translators.EntityEffectRemoveTranslator;
import misat11.hybrid.downstream.translators.EntityEquipmentTranslator;
import misat11.hybrid.downstream.translators.EntityHeadRotationTranslator;
import misat11.hybrid.downstream.translators.EntityLeashTranslator;
import misat11.hybrid.downstream.translators.EntityMetadataTranslator;
import misat11.hybrid.downstream.translators.EntitySetAttributesTranslator;
import misat11.hybrid.downstream.translators.EntityStatusTranslator;
import misat11.hybrid.downstream.translators.EntityTeleportTranslator;
import misat11.hybrid.downstream.translators.EntityVelocityTranslator;
import misat11.hybrid.downstream.translators.ExplosionTranslator;
import misat11.hybrid.downstream.translators.MapTranslator;
import misat11.hybrid.downstream.translators.OpenWindowTranslator;
import misat11.hybrid.downstream.translators.PlayerAbilitiesTranslator;
import misat11.hybrid.downstream.translators.PlayerListSetEntryTranslator;
import misat11.hybrid.downstream.translators.ServerDifficultyTranslator;
import misat11.hybrid.downstream.translators.SetExperienceTranslator;
import misat11.hybrid.downstream.translators.SetHealthTranslator;
import misat11.hybrid.downstream.translators.SetPositionTranslator;
import misat11.hybrid.downstream.translators.SpawnExpOrbTranslator;
import misat11.hybrid.downstream.translators.SpawnGlobalTranslator;
import misat11.hybrid.downstream.translators.SpawnLivingTranslator;
import misat11.hybrid.downstream.translators.SpawnNamedTranslator;
import misat11.hybrid.downstream.translators.SpawnObjectTranslator;
import misat11.hybrid.downstream.translators.SpawnPaintingTranslator;
import misat11.hybrid.downstream.translators.SpawnPositionTranslator;
import misat11.hybrid.downstream.translators.StartGameTranslator;
import misat11.hybrid.downstream.translators.TimeUpdateTranslator;
import misat11.hybrid.downstream.translators.TitleTranslator;
import misat11.hybrid.downstream.translators.TransactionConfirmTranslator;
import misat11.hybrid.downstream.translators.UnloadChunkTranslator;
import misat11.hybrid.downstream.translators.VehiclePassengersTranslator;
import misat11.hybrid.downstream.translators.WindowItemsTranslator;
import misat11.hybrid.downstream.translators.WorldCustomSoundTranslator;
import misat11.hybrid.downstream.translators.WorldEventTranslator;
import misat11.hybrid.downstream.translators.WorldParticleTranslator;
import misat11.hybrid.downstream.translators.WorldSoundTranslator;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerChatPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDifficultyPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerJoinGamePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPlayerListEntryPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPluginMessagePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerRespawnPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerTitlePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityAnimationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityAttachPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityCollectItemPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityDestroyPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityEffectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityEquipmentPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityHeadLookPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityMetadataPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityMovementPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPropertiesPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityRemoveEffectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntitySetPassengersPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityStatusPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityTeleportPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityVelocityPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerHealthPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnMobPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerCloseWindowPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerConfirmTransactionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerOpenWindowPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerWindowItemsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerBlockChangePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerChunkDataPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerExplosionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerMapDataPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerMultiBlockChangePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerNotifyClientPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlayBuiltinSoundPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlayEffectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlaySoundPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerSpawnParticlePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerSpawnPositionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUnloadChunkPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUpdateTileEntityPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUpdateTimePacket404;

public class DownstreamTranslatorRegister {

	private static final Map<Class<? extends Packet>, IDownstreamTranslator<? extends Packet>> translators = new HashMap<>();

	static {
		translators.put(ServerEntityAnimationPacket404.class, new AnimationPacketTranslator());
		translators.put(ServerMultiBlockChangePacket404.class, new BlockChangeMultiTranslator());
		translators.put(ServerBlockChangePacket404.class, new BlockChangeSingleTranslator());
		translators.put(ServerUpdateTileEntityPacket404.class, new BlockTileUpdateTranslator());
		translators.put(ServerEntityCollectItemPacket404.class, new CollectEffectTranslator());
		translators.put(ServerPluginMessagePacket404.class, new CustomPayloadTranslator());
		translators.put(ServerEntityMovementPacket404.class, new EntityDeltaPositionRotationTranslator());
		translators.put(ServerEntityDestroyPacket404.class, new EntityDestroyTranslator());
		translators.put(ServerEntityEffectPacket404.class, new EntityEffectAddTranslator());
		translators.put(ServerEntityRemoveEffectPacket404.class, new EntityEffectRemoveTranslator());
		translators.put(ServerEntityEquipmentPacket404.class, new EntityEquipmentTranslator());
		translators.put(ServerEntityHeadLookPacket404.class, new EntityHeadRotationTranslator());
		translators.put(ServerEntityAttachPacket404.class, new EntityLeashTranslator());
		translators.put(ServerEntityMetadataPacket404.class, new EntityMetadataTranslator());
		translators.put(ServerEntityPropertiesPacket404.class, new EntitySetAttributesTranslator());
		translators.put(ServerEntityStatusPacket404.class, new EntityStatusTranslator());
		translators.put(ServerEntityTeleportPacket404.class, new EntityTeleportTranslator());
		translators.put(ServerEntityVelocityPacket404.class, new EntityVelocityTranslator());
		translators.put(ServerExplosionPacket404.class, new ExplosionTranslator());
		translators.put(ServerRespawnPacket404.class, new ChangeDimensionTranslator());
		translators.put(ServerNotifyClientPacket404.class, new ChangeGameStateTranslator());
		translators.put(ServerChatPacket404.class, new ChatTranslator());
		translators.put(ServerChunkDataPacket404.class, new ChunkTranslator());
		translators.put(ServerMapDataPacket404.class, new MapTranslator());
		translators.put(ServerPlayerAbilitiesPacket404.class, new PlayerAbilitiesTranslator());
		translators.put(ServerPlayerListEntryPacket404.class, new PlayerListSetEntryTranslator());
		translators.put(ServerDifficultyPacket404.class, new ServerDifficultyTranslator());
		translators.put(ServerPlayerSetExperiencePacket404.class, new SetExperienceTranslator());
		translators.put(ServerPlayerHealthPacket404.class, new SetHealthTranslator());
		translators.put(ServerPlayerPositionRotationPacket404.class, new SetPositionTranslator());
		translators.put(ServerSpawnExpOrbPacket404.class, new SpawnExpOrbTranslator());
		translators.put(ServerSpawnGlobalEntityPacket404.class, new SpawnGlobalTranslator());
		translators.put(ServerSpawnMobPacket404.class, new SpawnLivingTranslator());
		translators.put(ServerSpawnPlayerPacket404.class, new SpawnNamedTranslator());
		translators.put(ServerSpawnObjectPacket404.class, new SpawnObjectTranslator());
		translators.put(ServerSpawnPaintingPacket404.class, new SpawnPaintingTranslator());
		translators.put(ServerSpawnPositionPacket404.class, new SpawnPositionTranslator());
		translators.put(ServerJoinGamePacket404.class, new StartGameTranslator());
		translators.put(ServerUpdateTimePacket404.class, new TimeUpdateTranslator());
		translators.put(ServerTitlePacket404.class, new TitleTranslator());
		translators.put(ServerUnloadChunkPacket404.class, new UnloadChunkTranslator());
		translators.put(ServerEntitySetPassengersPacket404.class, new VehiclePassengersTranslator());
		translators.put(ServerPlaySoundPacket404.class, new WorldCustomSoundTranslator());
		translators.put(ServerPlayEffectPacket404.class, new WorldEventTranslator());
		translators.put(ServerSpawnParticlePacket404.class, new WorldParticleTranslator());
		translators.put(ServerPlayBuiltinSoundPacket404.class, new WorldSoundTranslator());
		
		translators.put(ServerCloseWindowPacket404.class, new ClosedWindowTranslator());
		translators.put(ServerConfirmTransactionPacket404.class, new TransactionConfirmTranslator());
		translators.put(ServerOpenWindowPacket404.class, new OpenWindowTranslator());
		translators.put(ServerWindowItemsPacket404.class, new WindowItemsTranslator());
	}

	public static void translate(HybridSession session, Packet packet) {
		for(Map.Entry<Class<? extends Packet>, IDownstreamTranslator<? extends Packet>> entry : translators.entrySet()) {
			if (entry.getKey().isInstance(packet)) {
				IDownstreamTranslator<Packet> translator = (IDownstreamTranslator<Packet>) entry.getValue();
				BedrockPacket[] packets = translator.translate(session, packet);
				if (packets != null)
					for (BedrockPacket bepacket : packets)
						session.sendImmediatePackage(bepacket);
			}
		}
	}

}
