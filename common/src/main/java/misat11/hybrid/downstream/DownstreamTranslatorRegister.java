package misat11.hybrid.downstream;

import java.util.HashMap;
import java.util.Map;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerDifficultyPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPluginMessagePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerRespawnPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerTitlePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityAnimationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityAttachPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityCollectItemPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityDestroyPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityEffectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityEquipmentPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityHeadLookPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMetadataPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMovementPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPropertiesPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityRemoveEffectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntitySetPassengersPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityStatusPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityVelocityPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerHealthPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnMobPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerCloseWindowPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerOpenWindowPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerWindowItemsPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerBlockChangePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerExplosionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerMapDataPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerMultiBlockChangePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerNotifyClientPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlayBuiltinSoundPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlayEffectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlaySoundPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerSpawnParticlePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerSpawnPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUnloadChunkPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTileEntityPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTimePacket;
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

public class DownstreamTranslatorRegister {

	private static final Map<Class<? extends Packet>, IDownstreamTranslator<? extends Packet>> translators = new HashMap<>();

	static {
		translators.put(ServerEntityAnimationPacket.class, new AnimationPacketTranslator());
		translators.put(ServerMultiBlockChangePacket.class, new BlockChangeMultiTranslator());
		translators.put(ServerBlockChangePacket.class, new BlockChangeSingleTranslator());
		translators.put(ServerUpdateTileEntityPacket.class, new BlockTileUpdateTranslator());
		translators.put(ServerEntityCollectItemPacket.class, new CollectEffectTranslator());
		translators.put(ServerPluginMessagePacket.class, new CustomPayloadTranslator());
		translators.put(ServerEntityMovementPacket.class, new EntityDeltaPositionRotationTranslator());
		translators.put(ServerEntityDestroyPacket.class, new EntityDestroyTranslator());
		translators.put(ServerEntityEffectPacket.class, new EntityEffectAddTranslator());
		translators.put(ServerEntityRemoveEffectPacket.class, new EntityEffectRemoveTranslator());
		translators.put(ServerEntityEquipmentPacket.class, new EntityEquipmentTranslator());
		translators.put(ServerEntityHeadLookPacket.class, new EntityHeadRotationTranslator());
		translators.put(ServerEntityAttachPacket.class, new EntityLeashTranslator());
		translators.put(ServerEntityMetadataPacket.class, new EntityMetadataTranslator());
		translators.put(ServerEntityPropertiesPacket.class, new EntitySetAttributesTranslator());
		translators.put(ServerEntityStatusPacket.class, new EntityStatusTranslator());
		translators.put(ServerEntityTeleportPacket.class, new EntityTeleportTranslator());
		translators.put(ServerEntityVelocityPacket.class, new EntityVelocityTranslator());
		translators.put(ServerExplosionPacket.class, new ExplosionTranslator());
		translators.put(ServerRespawnPacket.class, new ChangeDimensionTranslator());
		translators.put(ServerNotifyClientPacket.class, new ChangeGameStateTranslator());
		translators.put(ServerChatPacket.class, new ChatTranslator());
		translators.put(ServerChunkDataPacket.class, new ChunkTranslator());
		translators.put(ServerMapDataPacket.class, new MapTranslator());
		translators.put(ServerPlayerAbilitiesPacket.class, new PlayerAbilitiesTranslator());
		translators.put(ServerPlayerListEntryPacket.class, new PlayerListSetEntryTranslator());
		translators.put(ServerDifficultyPacket.class, new ServerDifficultyTranslator());
		translators.put(ServerPlayerSetExperiencePacket.class, new SetExperienceTranslator());
		translators.put(ServerPlayerHealthPacket.class, new SetHealthTranslator());
		translators.put(ServerPlayerPositionRotationPacket.class, new SetPositionTranslator());
		translators.put(ServerSpawnExpOrbPacket.class, new SpawnExpOrbTranslator());
		translators.put(ServerSpawnGlobalEntityPacket.class, new SpawnGlobalTranslator());
		translators.put(ServerSpawnMobPacket.class, new SpawnLivingTranslator());
		translators.put(ServerSpawnPlayerPacket.class, new SpawnNamedTranslator());
		translators.put(ServerSpawnObjectPacket.class, new SpawnObjectTranslator());
		translators.put(ServerSpawnPaintingPacket.class, new SpawnPaintingTranslator());
		translators.put(ServerSpawnPositionPacket.class, new SpawnPositionTranslator());
		translators.put(ServerJoinGamePacket.class, new StartGameTranslator());
		translators.put(ServerUpdateTimePacket.class, new TimeUpdateTranslator());
		translators.put(ServerTitlePacket.class, new TitleTranslator());
		translators.put(ServerUnloadChunkPacket.class, new UnloadChunkTranslator());
		translators.put(ServerEntitySetPassengersPacket.class, new VehiclePassengersTranslator());
		translators.put(ServerPlaySoundPacket.class, new WorldCustomSoundTranslator());
		translators.put(ServerPlayEffectPacket.class, new WorldEventTranslator());
		translators.put(ServerSpawnParticlePacket.class, new WorldParticleTranslator());
		translators.put(ServerPlayBuiltinSoundPacket.class, new WorldSoundTranslator());
		
		translators.put(ServerCloseWindowPacket.class, new ClosedWindowTranslator());
		translators.put(ServerConfirmTransactionPacket.class, new TransactionConfirmTranslator());
		translators.put(ServerOpenWindowPacket.class, new OpenWindowTranslator());
		translators.put(ServerWindowItemsPacket.class, new WindowItemsTranslator());
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
