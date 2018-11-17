package misat11.hybrid.network.java.p404;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.session.SessionListener;

import misat11.hybrid.network.java.p404.packet.handshake.client.HandshakePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientChatPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientKeepAlivePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientPluginMessagePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientRequestPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientResourcePackStatusPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientSettingsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientTabCompletePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerAbilitiesPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerActionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerChangeHeldItemPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerInteractEntityPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerMovementPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerPlaceBlockPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerPositionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerPositionRotationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerRotationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerStatePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerSwingArmPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerUseItemPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientAdvancementTabPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientCloseWindowPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientConfirmTransactionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientCraftingBookDataPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientCreativeInventoryActionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientEditBookPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientEnchantItemPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientMoveItemToHotbarPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientPrepareCraftingGridPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientRenameItemPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientSelectTradePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientSetBeaconEffectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientUpdateCommandBlockMinecartPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientUpdateCommandBlockPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientUpdateStructureBlockPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientWindowActionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientBlockNBTRequestPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientEntityNBTRequestPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientSpectatePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientSteerBoatPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientSteerVehiclePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientTeleportConfirmPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientUpdateSignPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientVehicleMovePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerAdvancementTabPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerAdvancementsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerBossBarPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerChatPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerCombatPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareCommandsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareRecipesPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareTagsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDifficultyPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDisconnectPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerJoinGamePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerKeepAlivePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPlayerListDataPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPlayerListEntryPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPluginMessagePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerResourcePackSendPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerRespawnPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerSetCooldownPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerStatisticsPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerStopSoundPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerSwitchCameraPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerTabCompletePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerTitlePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerUnlockRecipesPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityAnimationPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityAttachPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityCollectItemPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityDestroyPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityEffectPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityEquipmentPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityHeadLookPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityMetadataPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityMovementPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPositionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPropertiesPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityRemoveEffectPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityRotationPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntitySetPassengersPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityStatusPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityTeleportPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityVelocityPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerVehicleMovePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerChangeHeldItemPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerFacingPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerHealthPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerUseBedPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnMobPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard.ServerDisplayScoreboardPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard.ServerScoreboardObjectivePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard.ServerTeamPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard.ServerUpdateScorePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerCloseWindowPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerConfirmTransactionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerOpenWindowPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerPreparedCraftingGridPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerSetSlotPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerWindowItemsPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerWindowPropertyPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerBlockBreakAnimPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerBlockChangePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerBlockValuePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerChunkDataPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerExplosionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerMapDataPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerMultiBlockChangePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerNBTResponsePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerNotifyClientPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerOpenTileEntityEditorPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlayBuiltinSoundPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlayEffectPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlaySoundPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerSpawnParticlePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerSpawnPositionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUnloadChunkPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUpdateTileEntityPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUpdateTimePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerWorldBorderPacket;
import misat11.hybrid.network.java.p404.packet.login.client.EncryptionResponsePacket404;
import misat11.hybrid.network.java.p404.packet.login.client.LoginPluginResponsePacket404;
import misat11.hybrid.network.java.p404.packet.login.client.LoginStartPacket404;
import misat11.hybrid.network.java.p404.packet.login.server.EncryptionRequestPacket404;
import misat11.hybrid.network.java.p404.packet.login.server.LoginDisconnectPacket404;
import misat11.hybrid.network.java.p404.packet.login.server.LoginPluginRequestPacket404;
import misat11.hybrid.network.java.p404.packet.login.server.LoginSetCompressionPacket404;
import misat11.hybrid.network.java.p404.packet.login.server.LoginSuccessPacket404;
import misat11.hybrid.network.java.p404.packet.status.client.StatusPingPacket404;
import misat11.hybrid.network.java.p404.packet.status.client.StatusQueryPacket404;
import misat11.hybrid.network.java.p404.packet.status.server.StatusPongPacket404;
import misat11.hybrid.network.java.p404.packet.status.server.StatusResponsePacket404;
import misat11.hybrid.network.java.pabstract.MinecraftProtocolAbstract;
import misat11.hybrid.network.java.pabstract.data.SubProtocol;

import java.net.Proxy;

public class MinecraftProtocol404 extends MinecraftProtocolAbstract {
	
    public MinecraftProtocol404(SubProtocol subProtocol) {
    	super(subProtocol);
    }

	public MinecraftProtocol404(String username) {
		super(username);
	}

    public MinecraftProtocol404(String username, String password) throws RequestException {
    	super(username, password);
    }

    public MinecraftProtocol404(String username, String clientToken, String accessToken) throws RequestException {
    	super(username, clientToken, accessToken);
    }

    public MinecraftProtocol404(String username, String password, Proxy proxy) throws RequestException {
    	super(username, password, proxy);
    }

    public MinecraftProtocol404(String username, String clientToken, String accessToken, Proxy proxy) throws RequestException {
    	super(username, clientToken, accessToken, proxy);
    }

    public MinecraftProtocol404(GameProfile profile, String clientToken, String accessToken) {
    	super(profile, clientToken, accessToken);
    }

	@Override
    protected void initClientHandshake(Session session) {
        this.registerOutgoing(0, HandshakePacket404.class);
    }

	@Override
    protected void initClientLogin(Session session) {
        this.registerIncoming(0x00, LoginDisconnectPacket404.class);
        this.registerIncoming(0x01, EncryptionRequestPacket404.class);
        this.registerIncoming(0x02, LoginSuccessPacket404.class);
        this.registerIncoming(0x03, LoginSetCompressionPacket404.class);
        this.registerIncoming(0x04, LoginPluginRequestPacket404.class);

        this.registerOutgoing(0x00, LoginStartPacket404.class);
        this.registerOutgoing(0x01, EncryptionResponsePacket404.class);
        this.registerOutgoing(0x02, LoginPluginResponsePacket404.class);
    }

	@Override
    protected void initClientGame(Session session) {
        this.registerIncoming(0x00, ServerSpawnObjectPacket.class);
        this.registerIncoming(0x01, ServerSpawnExpOrbPacket.class);
        this.registerIncoming(0x02, ServerSpawnGlobalEntityPacket.class);
        this.registerIncoming(0x03, ServerSpawnMobPacket.class);
        this.registerIncoming(0x04, ServerSpawnPaintingPacket.class);
        this.registerIncoming(0x05, ServerSpawnPlayerPacket.class);
        this.registerIncoming(0x06, ServerEntityAnimationPacket.class);
        this.registerIncoming(0x07, ServerStatisticsPacket.class);
        this.registerIncoming(0x08, ServerBlockBreakAnimPacket.class);
        this.registerIncoming(0x09, ServerUpdateTileEntityPacket.class);
        this.registerIncoming(0x0A, ServerBlockValuePacket.class);
        this.registerIncoming(0x0B, ServerBlockChangePacket.class);
        this.registerIncoming(0x0C, ServerBossBarPacket404.class);
        this.registerIncoming(0x0D, ServerDifficultyPacket.class);
        this.registerIncoming(0x0E, ServerChatPacket.class);
        this.registerIncoming(0x0F, ServerMultiBlockChangePacket.class);
        this.registerIncoming(0x10, ServerTabCompletePacket.class);
        this.registerIncoming(0x11, ServerDeclareCommandsPacket404.class);
        this.registerIncoming(0x12, ServerConfirmTransactionPacket.class);
        this.registerIncoming(0x13, ServerCloseWindowPacket.class);
        this.registerIncoming(0x14, ServerOpenWindowPacket.class);
        this.registerIncoming(0x15, ServerWindowItemsPacket.class);
        this.registerIncoming(0x16, ServerWindowPropertyPacket.class);
        this.registerIncoming(0x17, ServerSetSlotPacket.class);
        this.registerIncoming(0x18, ServerSetCooldownPacket.class);
        this.registerIncoming(0x19, ServerPluginMessagePacket.class);
        this.registerIncoming(0x1A, ServerPlaySoundPacket.class);
        this.registerIncoming(0x1B, ServerDisconnectPacket.class);
        this.registerIncoming(0x1C, ServerEntityStatusPacket.class);
        this.registerIncoming(0x1D, ServerNBTResponsePacket.class);
        this.registerIncoming(0x1E, ServerExplosionPacket.class);
        this.registerIncoming(0x1F, ServerUnloadChunkPacket.class);
        this.registerIncoming(0x20, ServerNotifyClientPacket.class);
        this.registerIncoming(0x21, ServerKeepAlivePacket.class);
        this.registerIncoming(0x22, ServerChunkDataPacket.class);
        this.registerIncoming(0x23, ServerPlayEffectPacket.class);
        this.registerIncoming(0x24, ServerSpawnParticlePacket.class);
        this.registerIncoming(0x25, ServerJoinGamePacket.class);
        this.registerIncoming(0x26, ServerMapDataPacket.class);
        this.registerIncoming(0x27, ServerEntityMovementPacket.class);
        this.registerIncoming(0x28, ServerEntityPositionPacket.class);
        this.registerIncoming(0x29, ServerEntityPositionRotationPacket.class);
        this.registerIncoming(0x2A, ServerEntityRotationPacket.class);
        this.registerIncoming(0x2B, ServerVehicleMovePacket.class);
        this.registerIncoming(0x2C, ServerOpenTileEntityEditorPacket.class);
        this.registerIncoming(0x2D, ServerPreparedCraftingGridPacket.class);
        this.registerIncoming(0x2E, ServerPlayerAbilitiesPacket.class);
        this.registerIncoming(0x2F, ServerCombatPacket404.class);
        this.registerIncoming(0x30, ServerPlayerListEntryPacket.class);
        this.registerIncoming(0x31, ServerPlayerFacingPacket.class);
        this.registerIncoming(0x32, ServerPlayerPositionRotationPacket.class);
        this.registerIncoming(0x33, ServerPlayerUseBedPacket.class);
        this.registerIncoming(0x34, ServerUnlockRecipesPacket.class);
        this.registerIncoming(0x35, ServerEntityDestroyPacket.class);
        this.registerIncoming(0x36, ServerEntityRemoveEffectPacket.class);
        this.registerIncoming(0x37, ServerResourcePackSendPacket.class);
        this.registerIncoming(0x38, ServerRespawnPacket.class);
        this.registerIncoming(0x39, ServerEntityHeadLookPacket.class);
        this.registerIncoming(0x3A, ServerAdvancementTabPacket404.class);
        this.registerIncoming(0x3B, ServerWorldBorderPacket.class);
        this.registerIncoming(0x3C, ServerSwitchCameraPacket.class);
        this.registerIncoming(0x3D, ServerPlayerChangeHeldItemPacket.class);
        this.registerIncoming(0x3E, ServerDisplayScoreboardPacket.class);
        this.registerIncoming(0x3F, ServerEntityMetadataPacket.class);
        this.registerIncoming(0x40, ServerEntityAttachPacket.class);
        this.registerIncoming(0x41, ServerEntityVelocityPacket.class);
        this.registerIncoming(0x42, ServerEntityEquipmentPacket.class);
        this.registerIncoming(0x43, ServerPlayerSetExperiencePacket.class);
        this.registerIncoming(0x44, ServerPlayerHealthPacket.class);
        this.registerIncoming(0x45, ServerScoreboardObjectivePacket.class);
        this.registerIncoming(0x46, ServerEntitySetPassengersPacket.class);
        this.registerIncoming(0x47, ServerTeamPacket.class);
        this.registerIncoming(0x48, ServerUpdateScorePacket.class);
        this.registerIncoming(0x49, ServerSpawnPositionPacket.class);
        this.registerIncoming(0x4A, ServerUpdateTimePacket.class);
        this.registerIncoming(0x4B, ServerTitlePacket.class);
        this.registerIncoming(0x4C, ServerStopSoundPacket.class);
        this.registerIncoming(0x4D, ServerPlayBuiltinSoundPacket.class);
        this.registerIncoming(0x4E, ServerPlayerListDataPacket.class);
        this.registerIncoming(0x4F, ServerEntityCollectItemPacket.class);
        this.registerIncoming(0x50, ServerEntityTeleportPacket.class);
        this.registerIncoming(0x51, ServerAdvancementsPacket404.class);
        this.registerIncoming(0x52, ServerEntityPropertiesPacket.class);
        this.registerIncoming(0x53, ServerEntityEffectPacket.class);
        this.registerIncoming(0x54, ServerDeclareRecipesPacket404.class);
        this.registerIncoming(0x55, ServerDeclareTagsPacket404.class);

        this.registerOutgoing(0x00, ClientTeleportConfirmPacket404.class);
        this.registerOutgoing(0x01, ClientBlockNBTRequestPacket404.class);
        this.registerOutgoing(0x02, ClientChatPacket404.class);
        this.registerOutgoing(0x03, ClientRequestPacket404.class);
        this.registerOutgoing(0x04, ClientSettingsPacket404.class);
        this.registerOutgoing(0x05, ClientTabCompletePacket404.class);
        this.registerOutgoing(0x06, ClientConfirmTransactionPacket404.class);
        this.registerOutgoing(0x07, ClientEnchantItemPacket404.class);
        this.registerOutgoing(0x08, ClientWindowActionPacket404.class);
        this.registerOutgoing(0x09, ClientCloseWindowPacket404.class);
        this.registerOutgoing(0x0A, ClientPluginMessagePacket404.class);
        this.registerOutgoing(0x0B, ClientEditBookPacket404.class);
        this.registerOutgoing(0x0C, ClientEntityNBTRequestPacket404.class);
        this.registerOutgoing(0x0D, ClientPlayerInteractEntityPacket404.class);
        this.registerOutgoing(0x0E, ClientKeepAlivePacket404.class);
        this.registerOutgoing(0x0F, ClientPlayerMovementPacket404.class);
        this.registerOutgoing(0x10, ClientPlayerPositionPacket404.class);
        this.registerOutgoing(0x11, ClientPlayerPositionRotationPacket404.class);
        this.registerOutgoing(0x12, ClientPlayerRotationPacket404.class);
        this.registerOutgoing(0x13, ClientVehicleMovePacket404.class);
        this.registerOutgoing(0x14, ClientSteerBoatPacket404.class);
        this.registerOutgoing(0x15, ClientMoveItemToHotbarPacket404.class);
        this.registerOutgoing(0x16, ClientPrepareCraftingGridPacket404.class);
        this.registerOutgoing(0x17, ClientPlayerAbilitiesPacket404.class);
        this.registerOutgoing(0x18, ClientPlayerActionPacket404.class);
        this.registerOutgoing(0x19, ClientPlayerStatePacket404.class);
        this.registerOutgoing(0x1A, ClientSteerVehiclePacket404.class);
        this.registerOutgoing(0x1B, ClientCraftingBookDataPacket404.class);
        this.registerOutgoing(0x1C, ClientRenameItemPacket404.class);
        this.registerOutgoing(0x1D, ClientResourcePackStatusPacket404.class);
        this.registerOutgoing(0x1E, ClientAdvancementTabPacket404.class);
        this.registerOutgoing(0x1F, ClientSelectTradePacket404.class);
        this.registerOutgoing(0x20, ClientSetBeaconEffectPacket404.class);
        this.registerOutgoing(0x21, ClientPlayerChangeHeldItemPacket404.class);
        this.registerOutgoing(0x22, ClientUpdateCommandBlockPacket404.class);
        this.registerOutgoing(0x23, ClientUpdateCommandBlockMinecartPacket404.class);
        this.registerOutgoing(0x24, ClientCreativeInventoryActionPacket404.class);
        this.registerOutgoing(0x25, ClientUpdateStructureBlockPacket404.class);
        this.registerOutgoing(0x26, ClientUpdateSignPacket404.class);
        this.registerOutgoing(0x27, ClientPlayerSwingArmPacket404.class);
        this.registerOutgoing(0x28, ClientSpectatePacket404.class);
        this.registerOutgoing(0x29, ClientPlayerPlaceBlockPacket404.class);
        this.registerOutgoing(0x2A, ClientPlayerUseItemPacket404.class);
    }

	@Override
    protected void initClientStatus(Session session) {
        this.registerIncoming(0x00, StatusResponsePacket404.class);
        this.registerIncoming(0x01, StatusPongPacket404.class);

        this.registerOutgoing(0x00, StatusQueryPacket404.class);
        this.registerOutgoing(0x01, StatusPingPacket404.class);
    }

	@Override
	protected SessionListener createClientSession() {
		return new ClientListener404();
	}
}
