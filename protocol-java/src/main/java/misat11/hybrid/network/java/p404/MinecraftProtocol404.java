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
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerChatPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerCombatPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareCommandsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareRecipesPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareTagsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDifficultyPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDisconnectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerJoinGamePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerKeepAlivePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPlayerListDataPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPlayerListEntryPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerPluginMessagePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerResourcePackSendPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerRespawnPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerSetCooldownPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerStatisticsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerStopSoundPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerSwitchCameraPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerTabCompletePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerTitlePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerUnlockRecipesPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityAnimationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityAttachPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityCollectItemPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityDestroyPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityEffectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityEquipmentPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityHeadLookPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityMetadataPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityMovementPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPositionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPositionRotationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityPropertiesPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityRemoveEffectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityRotationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntitySetPassengersPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityStatusPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityTeleportPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerEntityVelocityPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.ServerVehicleMovePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerChangeHeldItemPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerFacingPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerHealthPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.player.ServerPlayerUseBedPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnMobPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard.ServerDisplayScoreboardPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard.ServerScoreboardObjectivePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard.ServerTeamPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.scoreboard.ServerUpdateScorePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerCloseWindowPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerConfirmTransactionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerOpenWindowPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerPreparedCraftingGridPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerSetSlotPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerWindowItemsPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.window.ServerWindowPropertyPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerBlockBreakAnimPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerBlockChangePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerBlockValuePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerChunkDataPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerExplosionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerMapDataPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerMultiBlockChangePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerNBTResponsePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerNotifyClientPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerOpenTileEntityEditorPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlayBuiltinSoundPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlayEffectPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerPlaySoundPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerSpawnParticlePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerSpawnPositionPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUnloadChunkPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUpdateTileEntityPacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerUpdateTimePacket404;
import misat11.hybrid.network.java.p404.packet.ingame.server.world.ServerWorldBorderPacket404;
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
        this.registerOut(0, HandshakePacket404.class);
    }

	@Override
    protected void initClientLogin(Session session) {
        this.registerIn(0x00, LoginDisconnectPacket404.class);
        this.registerIn(0x01, EncryptionRequestPacket404.class);
        this.registerIn(0x02, LoginSuccessPacket404.class);
        this.registerIn(0x03, LoginSetCompressionPacket404.class);
        this.registerIn(0x04, LoginPluginRequestPacket404.class);

        this.registerOut(0x00, LoginStartPacket404.class);
        this.registerOut(0x01, EncryptionResponsePacket404.class);
        this.registerOut(0x02, LoginPluginResponsePacket404.class);
    }

	@Override
    protected void initClientGame(Session session) {
        this.registerIn(0x00, ServerSpawnObjectPacket404.class);
        this.registerIn(0x01, ServerSpawnExpOrbPacket404.class);
        this.registerIn(0x02, ServerSpawnGlobalEntityPacket404.class);
        this.registerIn(0x03, ServerSpawnMobPacket404.class);
        this.registerIn(0x04, ServerSpawnPaintingPacket404.class);
        this.registerIn(0x05, ServerSpawnPlayerPacket404.class);
        this.registerIn(0x06, ServerEntityAnimationPacket404.class);
        this.registerIn(0x07, ServerStatisticsPacket404.class);
        this.registerIn(0x08, ServerBlockBreakAnimPacket404.class);
        this.registerIn(0x09, ServerUpdateTileEntityPacket404.class);
        this.registerIn(0x0A, ServerBlockValuePacket404.class);
        this.registerIn(0x0B, ServerBlockChangePacket404.class);
        this.registerIn(0x0C, ServerBossBarPacket404.class);
        this.registerIn(0x0D, ServerDifficultyPacket404.class);
        this.registerIn(0x0E, ServerChatPacket404.class);
        this.registerIn(0x0F, ServerMultiBlockChangePacket404.class);
        this.registerIn(0x10, ServerTabCompletePacket404.class);
        this.registerIn(0x11, ServerDeclareCommandsPacket404.class);
        this.registerIn(0x12, ServerConfirmTransactionPacket404.class);
        this.registerIn(0x13, ServerCloseWindowPacket404.class);
        this.registerIn(0x14, ServerOpenWindowPacket404.class);
        this.registerIn(0x15, ServerWindowItemsPacket404.class);
        this.registerIn(0x16, ServerWindowPropertyPacket404.class);
        this.registerIn(0x17, ServerSetSlotPacket404.class);
        this.registerIn(0x18, ServerSetCooldownPacket404.class);
        this.registerIn(0x19, ServerPluginMessagePacket404.class);
        this.registerIn(0x1A, ServerPlaySoundPacket404.class);
        this.registerIn(0x1B, ServerDisconnectPacket404.class);
        this.registerIn(0x1C, ServerEntityStatusPacket404.class);
        this.registerIn(0x1D, ServerNBTResponsePacket404.class);
        this.registerIn(0x1E, ServerExplosionPacket404.class);
        this.registerIn(0x1F, ServerUnloadChunkPacket404.class);
        this.registerIn(0x20, ServerNotifyClientPacket404.class);
        this.registerIn(0x21, ServerKeepAlivePacket404.class);
        this.registerIn(0x22, ServerChunkDataPacket404.class);
        this.registerIn(0x23, ServerPlayEffectPacket404.class);
        this.registerIn(0x24, ServerSpawnParticlePacket404.class);
        this.registerIn(0x25, ServerJoinGamePacket404.class);
        this.registerIn(0x26, ServerMapDataPacket404.class);
        this.registerIn(0x27, ServerEntityMovementPacket404.class);
        this.registerIn(0x28, ServerEntityPositionPacket404.class);
        this.registerIn(0x29, ServerEntityPositionRotationPacket404.class);
        this.registerIn(0x2A, ServerEntityRotationPacket404.class);
        this.registerIn(0x2B, ServerVehicleMovePacket404.class);
        this.registerIn(0x2C, ServerOpenTileEntityEditorPacket404.class);
        this.registerIn(0x2D, ServerPreparedCraftingGridPacket404.class);
        this.registerIn(0x2E, ServerPlayerAbilitiesPacket404.class);
        this.registerIn(0x2F, ServerCombatPacket404.class);
        this.registerIn(0x30, ServerPlayerListEntryPacket404.class);
        this.registerIn(0x31, ServerPlayerFacingPacket404.class);
        this.registerIn(0x32, ServerPlayerPositionRotationPacket404.class);
        this.registerIn(0x33, ServerPlayerUseBedPacket404.class);
        this.registerIn(0x34, ServerUnlockRecipesPacket404.class);
        this.registerIn(0x35, ServerEntityDestroyPacket404.class);
        this.registerIn(0x36, ServerEntityRemoveEffectPacket404.class);
        this.registerIn(0x37, ServerResourcePackSendPacket404.class);
        this.registerIn(0x38, ServerRespawnPacket404.class);
        this.registerIn(0x39, ServerEntityHeadLookPacket404.class);
        this.registerIn(0x3A, ServerAdvancementTabPacket404.class);
        this.registerIn(0x3B, ServerWorldBorderPacket404.class);
        this.registerIn(0x3C, ServerSwitchCameraPacket404.class);
        this.registerIn(0x3D, ServerPlayerChangeHeldItemPacket404.class);
        this.registerIn(0x3E, ServerDisplayScoreboardPacket404.class);
        this.registerIn(0x3F, ServerEntityMetadataPacket404.class);
        this.registerIn(0x40, ServerEntityAttachPacket404.class);
        this.registerIn(0x41, ServerEntityVelocityPacket404.class);
        this.registerIn(0x42, ServerEntityEquipmentPacket404.class);
        this.registerIn(0x43, ServerPlayerSetExperiencePacket404.class);
        this.registerIn(0x44, ServerPlayerHealthPacket404.class);
        this.registerIn(0x45, ServerScoreboardObjectivePacket404.class);
        this.registerIn(0x46, ServerEntitySetPassengersPacket404.class);
        this.registerIn(0x47, ServerTeamPacket404.class);
        this.registerIn(0x48, ServerUpdateScorePacket404.class);
        this.registerIn(0x49, ServerSpawnPositionPacket404.class);
        this.registerIn(0x4A, ServerUpdateTimePacket404.class);
        this.registerIn(0x4B, ServerTitlePacket404.class);
        this.registerIn(0x4C, ServerStopSoundPacket404.class);
        this.registerIn(0x4D, ServerPlayBuiltinSoundPacket404.class);
        this.registerIn(0x4E, ServerPlayerListDataPacket404.class);
        this.registerIn(0x4F, ServerEntityCollectItemPacket404.class);
        this.registerIn(0x50, ServerEntityTeleportPacket404.class);
        this.registerIn(0x51, ServerAdvancementsPacket404.class);
        this.registerIn(0x52, ServerEntityPropertiesPacket404.class);
        this.registerIn(0x53, ServerEntityEffectPacket404.class);
        this.registerIn(0x54, ServerDeclareRecipesPacket404.class);
        this.registerIn(0x55, ServerDeclareTagsPacket404.class);

        this.registerOut(0x00, ClientTeleportConfirmPacket404.class);
        this.registerOut(0x01, ClientBlockNBTRequestPacket404.class);
        this.registerOut(0x02, ClientChatPacket404.class);
        this.registerOut(0x03, ClientRequestPacket404.class);
        this.registerOut(0x04, ClientSettingsPacket404.class);
        this.registerOut(0x05, ClientTabCompletePacket404.class);
        this.registerOut(0x06, ClientConfirmTransactionPacket404.class);
        this.registerOut(0x07, ClientEnchantItemPacket404.class);
        this.registerOut(0x08, ClientWindowActionPacket404.class);
        this.registerOut(0x09, ClientCloseWindowPacket404.class);
        this.registerOut(0x0A, ClientPluginMessagePacket404.class);
        this.registerOut(0x0B, ClientEditBookPacket404.class);
        this.registerOut(0x0C, ClientEntityNBTRequestPacket404.class);
        this.registerOut(0x0D, ClientPlayerInteractEntityPacket404.class);
        this.registerOut(0x0E, ClientKeepAlivePacket404.class);
        this.registerOut(0x0F, ClientPlayerMovementPacket404.class);
        this.registerOut(0x10, ClientPlayerPositionPacket404.class);
        this.registerOut(0x11, ClientPlayerPositionRotationPacket404.class);
        this.registerOut(0x12, ClientPlayerRotationPacket404.class);
        this.registerOut(0x13, ClientVehicleMovePacket404.class);
        this.registerOut(0x14, ClientSteerBoatPacket404.class);
        this.registerOut(0x15, ClientMoveItemToHotbarPacket404.class);
        this.registerOut(0x16, ClientPrepareCraftingGridPacket404.class);
        this.registerOut(0x17, ClientPlayerAbilitiesPacket404.class);
        this.registerOut(0x18, ClientPlayerActionPacket404.class);
        this.registerOut(0x19, ClientPlayerStatePacket404.class);
        this.registerOut(0x1A, ClientSteerVehiclePacket404.class);
        this.registerOut(0x1B, ClientCraftingBookDataPacket404.class);
        this.registerOut(0x1C, ClientRenameItemPacket404.class);
        this.registerOut(0x1D, ClientResourcePackStatusPacket404.class);
        this.registerOut(0x1E, ClientAdvancementTabPacket404.class);
        this.registerOut(0x1F, ClientSelectTradePacket404.class);
        this.registerOut(0x20, ClientSetBeaconEffectPacket404.class);
        this.registerOut(0x21, ClientPlayerChangeHeldItemPacket404.class);
        this.registerOut(0x22, ClientUpdateCommandBlockPacket404.class);
        this.registerOut(0x23, ClientUpdateCommandBlockMinecartPacket404.class);
        this.registerOut(0x24, ClientCreativeInventoryActionPacket404.class);
        this.registerOut(0x25, ClientUpdateStructureBlockPacket404.class);
        this.registerOut(0x26, ClientUpdateSignPacket404.class);
        this.registerOut(0x27, ClientPlayerSwingArmPacket404.class);
        this.registerOut(0x28, ClientSpectatePacket404.class);
        this.registerOut(0x29, ClientPlayerPlaceBlockPacket404.class);
        this.registerOut(0x2A, ClientPlayerUseItemPacket404.class);
    }

	@Override
    protected void initClientStatus(Session session) {
        this.registerIn(0x00, StatusResponsePacket404.class);
        this.registerIn(0x01, StatusPongPacket404.class);

        this.registerOut(0x00, StatusQueryPacket404.class);
        this.registerOut(0x01, StatusPingPacket404.class);
    }

	@Override
	protected SessionListener createClientSession() {
		return new ClientListener404();
	}
}
