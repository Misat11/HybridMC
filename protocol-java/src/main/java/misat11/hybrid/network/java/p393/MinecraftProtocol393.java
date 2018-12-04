package misat11.hybrid.network.java.p393;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.packetlib.Session;

import misat11.hybrid.network.java.p393.data.MagicValues393;
import misat11.hybrid.network.java.p393.packet.handshake.client.HandshakePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.ClientChatPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.ClientKeepAlivePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.ClientPluginMessagePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.ClientRequestPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.ClientResourcePackStatusPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.ClientSettingsPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.ClientTabCompletePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerAbilitiesPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerActionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerChangeHeldItemPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerInteractEntityPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerMovementPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerPlaceBlockPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerPositionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerPositionRotationPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerRotationPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerStatePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerSwingArmPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.player.ClientPlayerUseItemPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientAdvancementTabPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientCloseWindowPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientConfirmTransactionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientCraftingBookDataPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientCreativeInventoryActionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientEditBookPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientEnchantItemPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientMoveItemToHotbarPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientPrepareCraftingGridPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientRenameItemPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientSelectTradePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientSetBeaconEffectPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientUpdateCommandBlockMinecartPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientUpdateCommandBlockPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientUpdateStructureBlockPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.window.ClientWindowActionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.world.ClientBlockNBTRequestPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.world.ClientEntityNBTRequestPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.world.ClientSpectatePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.world.ClientSteerBoatPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.world.ClientSteerVehiclePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.world.ClientTeleportConfirmPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.world.ClientUpdateSignPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.client.world.ClientVehicleMovePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerAdvancementTabPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerAdvancementsPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerBossBarPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerChatPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerCombatPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerDeclareCommandsPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerDeclareRecipesPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerDeclareTagsPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerDifficultyPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerDisconnectPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerJoinGamePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerKeepAlivePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerPlayerListDataPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerPlayerListEntryPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerPluginMessagePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerResourcePackSendPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerRespawnPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerSetCooldownPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerStatisticsPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerStopSoundPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerSwitchCameraPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerTabCompletePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerTitlePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.ServerUnlockRecipesPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityAnimationPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityAttachPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityCollectItemPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityDestroyPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityEffectPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityEquipmentPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityHeadLookPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityMetadataPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityMovementPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityPositionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityPositionRotationPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityPropertiesPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityRemoveEffectPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityRotationPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntitySetPassengersPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityStatusPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityTeleportPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerEntityVelocityPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.ServerVehicleMovePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.player.ServerPlayerChangeHeldItemPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.player.ServerPlayerFacingPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.player.ServerPlayerHealthPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.player.ServerPlayerUseBedPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.spawn.ServerSpawnMobPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.scoreboard.ServerDisplayScoreboardPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.scoreboard.ServerScoreboardObjectivePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.scoreboard.ServerTeamPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.scoreboard.ServerUpdateScorePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.window.ServerCloseWindowPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.window.ServerConfirmTransactionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.window.ServerOpenWindowPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.window.ServerPreparedCraftingGridPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.window.ServerSetSlotPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.window.ServerWindowItemsPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.window.ServerWindowPropertyPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerBlockBreakAnimPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerBlockChangePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerBlockValuePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerChunkDataPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerExplosionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerMapDataPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerMultiBlockChangePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerNBTResponsePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerNotifyClientPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerOpenTileEntityEditorPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerPlayBuiltinSoundPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerPlayEffectPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerPlaySoundPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerSpawnParticlePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerSpawnPositionPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerUnloadChunkPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerUpdateTileEntityPacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerUpdateTimePacket393;
import misat11.hybrid.network.java.p393.packet.ingame.server.world.ServerWorldBorderPacket393;
import misat11.hybrid.network.java.p393.packet.login.client.EncryptionResponsePacket393;
import misat11.hybrid.network.java.p393.packet.login.client.LoginPluginResponsePacket393;
import misat11.hybrid.network.java.p393.packet.login.client.LoginStartPacket393;
import misat11.hybrid.network.java.p393.packet.login.server.EncryptionRequestPacket393;
import misat11.hybrid.network.java.p393.packet.login.server.LoginDisconnectPacket393;
import misat11.hybrid.network.java.p393.packet.login.server.LoginPluginRequestPacket393;
import misat11.hybrid.network.java.p393.packet.login.server.LoginSetCompressionPacket393;
import misat11.hybrid.network.java.p393.packet.login.server.LoginSuccessPacket393;
import misat11.hybrid.network.java.p393.packet.status.client.StatusPingPacket393;
import misat11.hybrid.network.java.p393.packet.status.client.StatusQueryPacket393;
import misat11.hybrid.network.java.p393.packet.status.server.StatusPongPacket393;
import misat11.hybrid.network.java.p393.packet.status.server.StatusResponsePacket393;
import misat11.hybrid.network.java.p393.util.NetUtil393;
import misat11.hybrid.network.java.pabstract.MinecraftProtocolAbstract;
import misat11.hybrid.network.java.pabstract.data.MagicValues;
import misat11.hybrid.network.java.pabstract.data.SubProtocol;
import misat11.hybrid.network.java.pabstract.util.NetUtil;

import java.net.Proxy;

public class MinecraftProtocol393 extends MinecraftProtocolAbstract {
	
    public MinecraftProtocol393(SubProtocol subProtocol) {
    	super(subProtocol);
    }

	public MinecraftProtocol393(String username) {
		super(username);
	}

    public MinecraftProtocol393(String username, String password) throws RequestException {
    	super(username, password);
    }

    public MinecraftProtocol393(String username, String clientToken, String accessToken) throws RequestException {
    	super(username, clientToken, accessToken);
    }

    public MinecraftProtocol393(String username, String password, Proxy proxy) throws RequestException {
    	super(username, password, proxy);
    }

    public MinecraftProtocol393(String username, String clientToken, String accessToken, Proxy proxy) throws RequestException {
    	super(username, clientToken, accessToken, proxy);
    }

    public MinecraftProtocol393(GameProfile profile, String clientToken, String accessToken) {
    	super(profile, clientToken, accessToken);
    }

	@Override
    protected void initClientHandshake(Session session) {
        this.registerOut(0, HandshakePacket393.class);
    }

	@Override
    protected void initClientLogin(Session session) {
        this.registerIn(0x00, LoginDisconnectPacket393.class);
        this.registerIn(0x01, EncryptionRequestPacket393.class);
        this.registerIn(0x02, LoginSuccessPacket393.class);
        this.registerIn(0x03, LoginSetCompressionPacket393.class);
        this.registerIn(0x04, LoginPluginRequestPacket393.class);

        this.registerOut(0x00, LoginStartPacket393.class);
        this.registerOut(0x01, EncryptionResponsePacket393.class);
        this.registerOut(0x02, LoginPluginResponsePacket393.class);
    }

	@Override
    protected void initClientGame(Session session) {
        this.registerIn(0x00, ServerSpawnObjectPacket393.class);
        this.registerIn(0x01, ServerSpawnExpOrbPacket393.class);
        this.registerIn(0x02, ServerSpawnGlobalEntityPacket393.class);
        this.registerIn(0x03, ServerSpawnMobPacket393.class);
        this.registerIn(0x04, ServerSpawnPaintingPacket393.class);
        this.registerIn(0x05, ServerSpawnPlayerPacket393.class);
        this.registerIn(0x06, ServerEntityAnimationPacket393.class);
        this.registerIn(0x07, ServerStatisticsPacket393.class);
        this.registerIn(0x08, ServerBlockBreakAnimPacket393.class);
        this.registerIn(0x09, ServerUpdateTileEntityPacket393.class);
        this.registerIn(0x0A, ServerBlockValuePacket393.class);
        this.registerIn(0x0B, ServerBlockChangePacket393.class);
        this.registerIn(0x0C, ServerBossBarPacket393.class);
        this.registerIn(0x0D, ServerDifficultyPacket393.class);
        this.registerIn(0x0E, ServerChatPacket393.class);
        this.registerIn(0x0F, ServerMultiBlockChangePacket393.class);
        this.registerIn(0x10, ServerTabCompletePacket393.class);
        this.registerIn(0x11, ServerDeclareCommandsPacket393.class);
        this.registerIn(0x12, ServerConfirmTransactionPacket393.class);
        this.registerIn(0x13, ServerCloseWindowPacket393.class);
        this.registerIn(0x14, ServerOpenWindowPacket393.class);
        this.registerIn(0x15, ServerWindowItemsPacket393.class);
        this.registerIn(0x16, ServerWindowPropertyPacket393.class);
        this.registerIn(0x17, ServerSetSlotPacket393.class);
        this.registerIn(0x18, ServerSetCooldownPacket393.class);
        this.registerIn(0x19, ServerPluginMessagePacket393.class);
        this.registerIn(0x1A, ServerPlaySoundPacket393.class);
        this.registerIn(0x1B, ServerDisconnectPacket393.class);
        this.registerIn(0x1C, ServerEntityStatusPacket393.class);
        this.registerIn(0x1D, ServerNBTResponsePacket393.class);
        this.registerIn(0x1E, ServerExplosionPacket393.class);
        this.registerIn(0x1F, ServerUnloadChunkPacket393.class);
        this.registerIn(0x20, ServerNotifyClientPacket393.class);
        this.registerIn(0x21, ServerKeepAlivePacket393.class);
        this.registerIn(0x22, ServerChunkDataPacket393.class);
        this.registerIn(0x23, ServerPlayEffectPacket393.class);
        this.registerIn(0x24, ServerSpawnParticlePacket393.class);
        this.registerIn(0x25, ServerJoinGamePacket393.class);
        this.registerIn(0x26, ServerMapDataPacket393.class);
        this.registerIn(0x27, ServerEntityMovementPacket393.class);
        this.registerIn(0x28, ServerEntityPositionPacket393.class);
        this.registerIn(0x29, ServerEntityPositionRotationPacket393.class);
        this.registerIn(0x2A, ServerEntityRotationPacket393.class);
        this.registerIn(0x2B, ServerVehicleMovePacket393.class);
        this.registerIn(0x2C, ServerOpenTileEntityEditorPacket393.class);
        this.registerIn(0x2D, ServerPreparedCraftingGridPacket393.class);
        this.registerIn(0x2E, ServerPlayerAbilitiesPacket393.class);
        this.registerIn(0x2F, ServerCombatPacket393.class);
        this.registerIn(0x30, ServerPlayerListEntryPacket393.class);
        this.registerIn(0x31, ServerPlayerFacingPacket393.class);
        this.registerIn(0x32, ServerPlayerPositionRotationPacket393.class);
        this.registerIn(0x33, ServerPlayerUseBedPacket393.class);
        this.registerIn(0x34, ServerUnlockRecipesPacket393.class);
        this.registerIn(0x35, ServerEntityDestroyPacket393.class);
        this.registerIn(0x36, ServerEntityRemoveEffectPacket393.class);
        this.registerIn(0x37, ServerResourcePackSendPacket393.class);
        this.registerIn(0x38, ServerRespawnPacket393.class);
        this.registerIn(0x39, ServerEntityHeadLookPacket393.class);
        this.registerIn(0x3A, ServerAdvancementTabPacket393.class);
        this.registerIn(0x3B, ServerWorldBorderPacket393.class);
        this.registerIn(0x3C, ServerSwitchCameraPacket393.class);
        this.registerIn(0x3D, ServerPlayerChangeHeldItemPacket393.class);
        this.registerIn(0x3E, ServerDisplayScoreboardPacket393.class);
        this.registerIn(0x3F, ServerEntityMetadataPacket393.class);
        this.registerIn(0x40, ServerEntityAttachPacket393.class);
        this.registerIn(0x41, ServerEntityVelocityPacket393.class);
        this.registerIn(0x42, ServerEntityEquipmentPacket393.class);
        this.registerIn(0x43, ServerPlayerSetExperiencePacket393.class);
        this.registerIn(0x44, ServerPlayerHealthPacket393.class);
        this.registerIn(0x45, ServerScoreboardObjectivePacket393.class);
        this.registerIn(0x46, ServerEntitySetPassengersPacket393.class);
        this.registerIn(0x47, ServerTeamPacket393.class);
        this.registerIn(0x48, ServerUpdateScorePacket393.class);
        this.registerIn(0x49, ServerSpawnPositionPacket393.class);
        this.registerIn(0x4A, ServerUpdateTimePacket393.class);
        this.registerIn(0x4B, ServerTitlePacket393.class);
        this.registerIn(0x4C, ServerStopSoundPacket393.class);
        this.registerIn(0x4D, ServerPlayBuiltinSoundPacket393.class);
        this.registerIn(0x4E, ServerPlayerListDataPacket393.class);
        this.registerIn(0x4F, ServerEntityCollectItemPacket393.class);
        this.registerIn(0x50, ServerEntityTeleportPacket393.class);
        this.registerIn(0x51, ServerAdvancementsPacket393.class);
        this.registerIn(0x52, ServerEntityPropertiesPacket393.class);
        this.registerIn(0x53, ServerEntityEffectPacket393.class);
        this.registerIn(0x54, ServerDeclareRecipesPacket393.class);
        this.registerIn(0x55, ServerDeclareTagsPacket393.class);

        this.registerOut(0x00, ClientTeleportConfirmPacket393.class);
        this.registerOut(0x01, ClientBlockNBTRequestPacket393.class);
        this.registerOut(0x02, ClientChatPacket393.class);
        this.registerOut(0x03, ClientRequestPacket393.class);
        this.registerOut(0x04, ClientSettingsPacket393.class);
        this.registerOut(0x05, ClientTabCompletePacket393.class);
        this.registerOut(0x06, ClientConfirmTransactionPacket393.class);
        this.registerOut(0x07, ClientEnchantItemPacket393.class);
        this.registerOut(0x08, ClientWindowActionPacket393.class);
        this.registerOut(0x09, ClientCloseWindowPacket393.class);
        this.registerOut(0x0A, ClientPluginMessagePacket393.class);
        this.registerOut(0x0B, ClientEditBookPacket393.class);
        this.registerOut(0x0C, ClientEntityNBTRequestPacket393.class);
        this.registerOut(0x0D, ClientPlayerInteractEntityPacket393.class);
        this.registerOut(0x0E, ClientKeepAlivePacket393.class);
        this.registerOut(0x0F, ClientPlayerMovementPacket393.class);
        this.registerOut(0x10, ClientPlayerPositionPacket393.class);
        this.registerOut(0x11, ClientPlayerPositionRotationPacket393.class);
        this.registerOut(0x12, ClientPlayerRotationPacket393.class);
        this.registerOut(0x13, ClientVehicleMovePacket393.class);
        this.registerOut(0x14, ClientSteerBoatPacket393.class);
        this.registerOut(0x15, ClientMoveItemToHotbarPacket393.class);
        this.registerOut(0x16, ClientPrepareCraftingGridPacket393.class);
        this.registerOut(0x17, ClientPlayerAbilitiesPacket393.class);
        this.registerOut(0x18, ClientPlayerActionPacket393.class);
        this.registerOut(0x19, ClientPlayerStatePacket393.class);
        this.registerOut(0x1A, ClientSteerVehiclePacket393.class);
        this.registerOut(0x1B, ClientCraftingBookDataPacket393.class);
        this.registerOut(0x1C, ClientRenameItemPacket393.class);
        this.registerOut(0x1D, ClientResourcePackStatusPacket393.class);
        this.registerOut(0x1E, ClientAdvancementTabPacket393.class);
        this.registerOut(0x1F, ClientSelectTradePacket393.class);
        this.registerOut(0x20, ClientSetBeaconEffectPacket393.class);
        this.registerOut(0x21, ClientPlayerChangeHeldItemPacket393.class);
        this.registerOut(0x22, ClientUpdateCommandBlockPacket393.class);
        this.registerOut(0x23, ClientUpdateCommandBlockMinecartPacket393.class);
        this.registerOut(0x24, ClientCreativeInventoryActionPacket393.class);
        this.registerOut(0x25, ClientUpdateStructureBlockPacket393.class);
        this.registerOut(0x26, ClientUpdateSignPacket393.class);
        this.registerOut(0x27, ClientPlayerSwingArmPacket393.class);
        this.registerOut(0x28, ClientSpectatePacket393.class);
        this.registerOut(0x29, ClientPlayerPlaceBlockPacket393.class);
        this.registerOut(0x2A, ClientPlayerUseItemPacket393.class);
    }

	@Override
    protected void initClientStatus(Session session) {
        this.registerIn(0x00, StatusResponsePacket393.class);
        this.registerIn(0x01, StatusPongPacket393.class);

        this.registerOut(0x00, StatusQueryPacket393.class);
        this.registerOut(0x01, StatusPingPacket393.class);
    }

	@Override
	public int getProtocolVersion() {
		return MinecraftConstants393.PROTOCOL_VERSION;
	}

	@Override
	public MagicValues getMagic() {
		return MagicValues393.INSTANCE;
	}

	@Override
	public NetUtil getNetUtil() {
		return NetUtil393.INSTANCE;
	}
}
