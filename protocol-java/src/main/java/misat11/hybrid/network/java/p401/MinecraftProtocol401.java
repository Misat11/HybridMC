package misat11.hybrid.network.java.p401;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.packetlib.Session;

import misat11.hybrid.network.java.p401.data.MagicValues401;
import misat11.hybrid.network.java.p401.packet.handshake.client.HandshakePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.ClientChatPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.ClientKeepAlivePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.ClientPluginMessagePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.ClientRequestPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.ClientResourcePackStatusPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.ClientSettingsPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.ClientTabCompletePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerAbilitiesPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerActionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerChangeHeldItemPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerInteractEntityPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerMovementPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerPlaceBlockPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerPositionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerPositionRotationPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerRotationPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerStatePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerSwingArmPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.player.ClientPlayerUseItemPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientAdvancementTabPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientCloseWindowPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientConfirmTransactionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientCraftingBookDataPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientCreativeInventoryActionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientEditBookPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientEnchantItemPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientMoveItemToHotbarPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientPrepareCraftingGridPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientRenameItemPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientSelectTradePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientSetBeaconEffectPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientUpdateCommandBlockMinecartPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientUpdateCommandBlockPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientUpdateStructureBlockPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.window.ClientWindowActionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.world.ClientBlockNBTRequestPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.world.ClientEntityNBTRequestPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.world.ClientSpectatePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.world.ClientSteerBoatPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.world.ClientSteerVehiclePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.world.ClientTeleportConfirmPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.world.ClientUpdateSignPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.client.world.ClientVehicleMovePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerAdvancementTabPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerAdvancementsPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerBossBarPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerChatPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerCombatPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerDeclareCommandsPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerDeclareRecipesPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerDeclareTagsPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerDifficultyPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerDisconnectPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerJoinGamePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerKeepAlivePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerPlayerListDataPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerPlayerListEntryPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerPluginMessagePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerResourcePackSendPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerRespawnPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerSetCooldownPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerStatisticsPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerStopSoundPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerSwitchCameraPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerTabCompletePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerTitlePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.ServerUnlockRecipesPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityAnimationPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityAttachPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityCollectItemPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityDestroyPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityEffectPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityEquipmentPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityHeadLookPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityMetadataPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityMovementPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityPositionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityPositionRotationPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityPropertiesPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityRemoveEffectPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityRotationPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntitySetPassengersPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityStatusPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityTeleportPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerEntityVelocityPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.ServerVehicleMovePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.player.ServerPlayerChangeHeldItemPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.player.ServerPlayerFacingPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.player.ServerPlayerHealthPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.player.ServerPlayerUseBedPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn.ServerSpawnMobPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.scoreboard.ServerDisplayScoreboardPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.scoreboard.ServerScoreboardObjectivePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.scoreboard.ServerTeamPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.scoreboard.ServerUpdateScorePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.window.ServerCloseWindowPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.window.ServerConfirmTransactionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.window.ServerOpenWindowPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.window.ServerPreparedCraftingGridPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.window.ServerSetSlotPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.window.ServerWindowItemsPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.window.ServerWindowPropertyPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerBlockBreakAnimPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerBlockChangePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerBlockValuePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerChunkDataPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerExplosionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerMapDataPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerMultiBlockChangePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerNBTResponsePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerNotifyClientPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerOpenTileEntityEditorPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerPlayBuiltinSoundPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerPlayEffectPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerPlaySoundPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerSpawnParticlePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerSpawnPositionPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerUnloadChunkPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerUpdateTileEntityPacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerUpdateTimePacket401;
import misat11.hybrid.network.java.p401.packet.ingame.server.world.ServerWorldBorderPacket401;
import misat11.hybrid.network.java.p401.packet.login.client.EncryptionResponsePacket401;
import misat11.hybrid.network.java.p401.packet.login.client.LoginPluginResponsePacket401;
import misat11.hybrid.network.java.p401.packet.login.client.LoginStartPacket401;
import misat11.hybrid.network.java.p401.packet.login.server.EncryptionRequestPacket401;
import misat11.hybrid.network.java.p401.packet.login.server.LoginDisconnectPacket401;
import misat11.hybrid.network.java.p401.packet.login.server.LoginPluginRequestPacket401;
import misat11.hybrid.network.java.p401.packet.login.server.LoginSetCompressionPacket401;
import misat11.hybrid.network.java.p401.packet.login.server.LoginSuccessPacket401;
import misat11.hybrid.network.java.p401.packet.status.client.StatusPingPacket401;
import misat11.hybrid.network.java.p401.packet.status.client.StatusQueryPacket401;
import misat11.hybrid.network.java.p401.packet.status.server.StatusPongPacket401;
import misat11.hybrid.network.java.p401.packet.status.server.StatusResponsePacket401;
import misat11.hybrid.network.java.p401.util.NetUtil401;
import misat11.hybrid.network.java.pabstract.MinecraftProtocolAbstract;
import misat11.hybrid.network.java.pabstract.data.MagicValues;
import misat11.hybrid.network.java.pabstract.data.SubProtocol;
import misat11.hybrid.network.java.pabstract.util.NetUtil;

import java.net.Proxy;

public class MinecraftProtocol401 extends MinecraftProtocolAbstract {
	
    public MinecraftProtocol401(SubProtocol subProtocol) {
    	super(subProtocol);
    }

	public MinecraftProtocol401(String username) {
		super(username);
	}

    public MinecraftProtocol401(String username, String password) throws RequestException {
    	super(username, password);
    }

    public MinecraftProtocol401(String username, String clientToken, String accessToken) throws RequestException {
    	super(username, clientToken, accessToken);
    }

    public MinecraftProtocol401(String username, String password, Proxy proxy) throws RequestException {
    	super(username, password, proxy);
    }

    public MinecraftProtocol401(String username, String clientToken, String accessToken, Proxy proxy) throws RequestException {
    	super(username, clientToken, accessToken, proxy);
    }

    public MinecraftProtocol401(GameProfile profile, String clientToken, String accessToken) {
    	super(profile, clientToken, accessToken);
    }

	@Override
    protected void initClientHandshake(Session session) {
        this.registerOut(0, HandshakePacket401.class);
    }

	@Override
    protected void initClientLogin(Session session) {
        this.registerIn(0x00, LoginDisconnectPacket401.class);
        this.registerIn(0x01, EncryptionRequestPacket401.class);
        this.registerIn(0x02, LoginSuccessPacket401.class);
        this.registerIn(0x03, LoginSetCompressionPacket401.class);
        this.registerIn(0x04, LoginPluginRequestPacket401.class);

        this.registerOut(0x00, LoginStartPacket401.class);
        this.registerOut(0x01, EncryptionResponsePacket401.class);
        this.registerOut(0x02, LoginPluginResponsePacket401.class);
    }

	@Override
    protected void initClientGame(Session session) {
        this.registerIn(0x00, ServerSpawnObjectPacket401.class);
        this.registerIn(0x01, ServerSpawnExpOrbPacket401.class);
        this.registerIn(0x02, ServerSpawnGlobalEntityPacket401.class);
        this.registerIn(0x03, ServerSpawnMobPacket401.class);
        this.registerIn(0x04, ServerSpawnPaintingPacket401.class);
        this.registerIn(0x05, ServerSpawnPlayerPacket401.class);
        this.registerIn(0x06, ServerEntityAnimationPacket401.class);
        this.registerIn(0x07, ServerStatisticsPacket401.class);
        this.registerIn(0x08, ServerBlockBreakAnimPacket401.class);
        this.registerIn(0x09, ServerUpdateTileEntityPacket401.class);
        this.registerIn(0x0A, ServerBlockValuePacket401.class);
        this.registerIn(0x0B, ServerBlockChangePacket401.class);
        this.registerIn(0x0C, ServerBossBarPacket401.class);
        this.registerIn(0x0D, ServerDifficultyPacket401.class);
        this.registerIn(0x0E, ServerChatPacket401.class);
        this.registerIn(0x0F, ServerMultiBlockChangePacket401.class);
        this.registerIn(0x10, ServerTabCompletePacket401.class);
        this.registerIn(0x11, ServerDeclareCommandsPacket401.class);
        this.registerIn(0x12, ServerConfirmTransactionPacket401.class);
        this.registerIn(0x13, ServerCloseWindowPacket401.class);
        this.registerIn(0x14, ServerOpenWindowPacket401.class);
        this.registerIn(0x15, ServerWindowItemsPacket401.class);
        this.registerIn(0x16, ServerWindowPropertyPacket401.class);
        this.registerIn(0x17, ServerSetSlotPacket401.class);
        this.registerIn(0x18, ServerSetCooldownPacket401.class);
        this.registerIn(0x19, ServerPluginMessagePacket401.class);
        this.registerIn(0x1A, ServerPlaySoundPacket401.class);
        this.registerIn(0x1B, ServerDisconnectPacket401.class);
        this.registerIn(0x1C, ServerEntityStatusPacket401.class);
        this.registerIn(0x1D, ServerNBTResponsePacket401.class);
        this.registerIn(0x1E, ServerExplosionPacket401.class);
        this.registerIn(0x1F, ServerUnloadChunkPacket401.class);
        this.registerIn(0x20, ServerNotifyClientPacket401.class);
        this.registerIn(0x21, ServerKeepAlivePacket401.class);
        this.registerIn(0x22, ServerChunkDataPacket401.class);
        this.registerIn(0x23, ServerPlayEffectPacket401.class);
        this.registerIn(0x24, ServerSpawnParticlePacket401.class);
        this.registerIn(0x25, ServerJoinGamePacket401.class);
        this.registerIn(0x26, ServerMapDataPacket401.class);
        this.registerIn(0x27, ServerEntityMovementPacket401.class);
        this.registerIn(0x28, ServerEntityPositionPacket401.class);
        this.registerIn(0x29, ServerEntityPositionRotationPacket401.class);
        this.registerIn(0x2A, ServerEntityRotationPacket401.class);
        this.registerIn(0x2B, ServerVehicleMovePacket401.class);
        this.registerIn(0x2C, ServerOpenTileEntityEditorPacket401.class);
        this.registerIn(0x2D, ServerPreparedCraftingGridPacket401.class);
        this.registerIn(0x2E, ServerPlayerAbilitiesPacket401.class);
        this.registerIn(0x2F, ServerCombatPacket401.class);
        this.registerIn(0x30, ServerPlayerListEntryPacket401.class);
        this.registerIn(0x31, ServerPlayerFacingPacket401.class);
        this.registerIn(0x32, ServerPlayerPositionRotationPacket401.class);
        this.registerIn(0x33, ServerPlayerUseBedPacket401.class);
        this.registerIn(0x34, ServerUnlockRecipesPacket401.class);
        this.registerIn(0x35, ServerEntityDestroyPacket401.class);
        this.registerIn(0x36, ServerEntityRemoveEffectPacket401.class);
        this.registerIn(0x37, ServerResourcePackSendPacket401.class);
        this.registerIn(0x38, ServerRespawnPacket401.class);
        this.registerIn(0x39, ServerEntityHeadLookPacket401.class);
        this.registerIn(0x3A, ServerAdvancementTabPacket401.class);
        this.registerIn(0x3B, ServerWorldBorderPacket401.class);
        this.registerIn(0x3C, ServerSwitchCameraPacket401.class);
        this.registerIn(0x3D, ServerPlayerChangeHeldItemPacket401.class);
        this.registerIn(0x3E, ServerDisplayScoreboardPacket401.class);
        this.registerIn(0x3F, ServerEntityMetadataPacket401.class);
        this.registerIn(0x40, ServerEntityAttachPacket401.class);
        this.registerIn(0x41, ServerEntityVelocityPacket401.class);
        this.registerIn(0x42, ServerEntityEquipmentPacket401.class);
        this.registerIn(0x43, ServerPlayerSetExperiencePacket401.class);
        this.registerIn(0x44, ServerPlayerHealthPacket401.class);
        this.registerIn(0x45, ServerScoreboardObjectivePacket401.class);
        this.registerIn(0x46, ServerEntitySetPassengersPacket401.class);
        this.registerIn(0x47, ServerTeamPacket401.class);
        this.registerIn(0x48, ServerUpdateScorePacket401.class);
        this.registerIn(0x49, ServerSpawnPositionPacket401.class);
        this.registerIn(0x4A, ServerUpdateTimePacket401.class);
        this.registerIn(0x4B, ServerTitlePacket401.class);
        this.registerIn(0x4C, ServerStopSoundPacket401.class);
        this.registerIn(0x4D, ServerPlayBuiltinSoundPacket401.class);
        this.registerIn(0x4E, ServerPlayerListDataPacket401.class);
        this.registerIn(0x4F, ServerEntityCollectItemPacket401.class);
        this.registerIn(0x50, ServerEntityTeleportPacket401.class);
        this.registerIn(0x51, ServerAdvancementsPacket401.class);
        this.registerIn(0x52, ServerEntityPropertiesPacket401.class);
        this.registerIn(0x53, ServerEntityEffectPacket401.class);
        this.registerIn(0x54, ServerDeclareRecipesPacket401.class);
        this.registerIn(0x55, ServerDeclareTagsPacket401.class);

        this.registerOut(0x00, ClientTeleportConfirmPacket401.class);
        this.registerOut(0x01, ClientBlockNBTRequestPacket401.class);
        this.registerOut(0x02, ClientChatPacket401.class);
        this.registerOut(0x03, ClientRequestPacket401.class);
        this.registerOut(0x04, ClientSettingsPacket401.class);
        this.registerOut(0x05, ClientTabCompletePacket401.class);
        this.registerOut(0x06, ClientConfirmTransactionPacket401.class);
        this.registerOut(0x07, ClientEnchantItemPacket401.class);
        this.registerOut(0x08, ClientWindowActionPacket401.class);
        this.registerOut(0x09, ClientCloseWindowPacket401.class);
        this.registerOut(0x0A, ClientPluginMessagePacket401.class);
        this.registerOut(0x0B, ClientEditBookPacket401.class);
        this.registerOut(0x0C, ClientEntityNBTRequestPacket401.class);
        this.registerOut(0x0D, ClientPlayerInteractEntityPacket401.class);
        this.registerOut(0x0E, ClientKeepAlivePacket401.class);
        this.registerOut(0x0F, ClientPlayerMovementPacket401.class);
        this.registerOut(0x10, ClientPlayerPositionPacket401.class);
        this.registerOut(0x11, ClientPlayerPositionRotationPacket401.class);
        this.registerOut(0x12, ClientPlayerRotationPacket401.class);
        this.registerOut(0x13, ClientVehicleMovePacket401.class);
        this.registerOut(0x14, ClientSteerBoatPacket401.class);
        this.registerOut(0x15, ClientMoveItemToHotbarPacket401.class);
        this.registerOut(0x16, ClientPrepareCraftingGridPacket401.class);
        this.registerOut(0x17, ClientPlayerAbilitiesPacket401.class);
        this.registerOut(0x18, ClientPlayerActionPacket401.class);
        this.registerOut(0x19, ClientPlayerStatePacket401.class);
        this.registerOut(0x1A, ClientSteerVehiclePacket401.class);
        this.registerOut(0x1B, ClientCraftingBookDataPacket401.class);
        this.registerOut(0x1C, ClientRenameItemPacket401.class);
        this.registerOut(0x1D, ClientResourcePackStatusPacket401.class);
        this.registerOut(0x1E, ClientAdvancementTabPacket401.class);
        this.registerOut(0x1F, ClientSelectTradePacket401.class);
        this.registerOut(0x20, ClientSetBeaconEffectPacket401.class);
        this.registerOut(0x21, ClientPlayerChangeHeldItemPacket401.class);
        this.registerOut(0x22, ClientUpdateCommandBlockPacket401.class);
        this.registerOut(0x23, ClientUpdateCommandBlockMinecartPacket401.class);
        this.registerOut(0x24, ClientCreativeInventoryActionPacket401.class);
        this.registerOut(0x25, ClientUpdateStructureBlockPacket401.class);
        this.registerOut(0x26, ClientUpdateSignPacket401.class);
        this.registerOut(0x27, ClientPlayerSwingArmPacket401.class);
        this.registerOut(0x28, ClientSpectatePacket401.class);
        this.registerOut(0x29, ClientPlayerPlaceBlockPacket401.class);
        this.registerOut(0x2A, ClientPlayerUseItemPacket401.class);
    }

	@Override
    protected void initClientStatus(Session session) {
        this.registerIn(0x00, StatusResponsePacket401.class);
        this.registerIn(0x01, StatusPongPacket401.class);

        this.registerOut(0x00, StatusQueryPacket401.class);
        this.registerOut(0x01, StatusPingPacket401.class);
    }

	@Override
	public int getProtocolVersion() {
		return MinecraftConstants401.PROTOCOL_VERSION;
	}

	@Override
	public MagicValues getMagic() {
		return MagicValues401.INSTANCE;
	}

	@Override
	public NetUtil getNetUtil() {
		return NetUtil401.INSTANCE;
	}
}
