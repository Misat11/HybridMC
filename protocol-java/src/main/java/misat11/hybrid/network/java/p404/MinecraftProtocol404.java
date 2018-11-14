package misat11.hybrid.network.java.p404;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.mc.auth.service.AuthenticationService;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.Server;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.crypt.AESEncryption;
import com.github.steveice10.packetlib.crypt.PacketEncryption;
import com.github.steveice10.packetlib.packet.DefaultPacketHeader;
import com.github.steveice10.packetlib.packet.PacketHeader;

import misat11.hybrid.network.java.p404.data.SubProtocol;
import misat11.hybrid.network.java.p404.packet.handshake.client.HandshakePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientChatPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientKeepAlivePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientPluginMessagePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientRequestPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientResourcePackStatusPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientSettingsPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientTabCompletePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerAbilitiesPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerActionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerChangeHeldItemPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerInteractEntityPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerMovementPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerPlaceBlockPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerPositionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerRotationPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerStatePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerSwingArmPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerUseItemPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientAdvancementTabPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientCloseWindowPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientConfirmTransactionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientCraftingBookDataPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientCreativeInventoryActionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientEditBookPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientEnchantItemPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientMoveItemToHotbarPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientPrepareCraftingGridPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientRenameItemPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientSelectTradePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientSetBeaconEffectPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientUpdateCommandBlockMinecartPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientUpdateCommandBlockPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientUpdateStructureBlockPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.window.ClientWindowActionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientBlockNBTRequestPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientEntityNBTRequestPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientSpectatePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientSteerBoatPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientSteerVehiclePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientTeleportConfirmPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientUpdateSignPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientVehicleMovePacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerAdvancementTabPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerAdvancementsPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerBossBarPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerChatPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerCombatPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareCommandsPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareRecipesPacket;
import misat11.hybrid.network.java.p404.packet.ingame.server.ServerDeclareTagsPacket;
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
import misat11.hybrid.network.java.p404.packet.login.client.EncryptionResponsePacket;
import misat11.hybrid.network.java.p404.packet.login.client.LoginPluginResponsePacket;
import misat11.hybrid.network.java.p404.packet.login.client.LoginStartPacket;
import misat11.hybrid.network.java.p404.packet.login.server.EncryptionRequestPacket;
import misat11.hybrid.network.java.p404.packet.login.server.LoginDisconnectPacket;
import misat11.hybrid.network.java.p404.packet.login.server.LoginPluginRequestPacket;
import misat11.hybrid.network.java.p404.packet.login.server.LoginSetCompressionPacket;
import misat11.hybrid.network.java.p404.packet.login.server.LoginSuccessPacket;
import misat11.hybrid.network.java.p404.packet.status.client.StatusPingPacket;
import misat11.hybrid.network.java.p404.packet.status.client.StatusQueryPacket;
import misat11.hybrid.network.java.p404.packet.status.server.StatusPongPacket;
import misat11.hybrid.network.java.p404.packet.status.server.StatusResponsePacket;
import misat11.hybrid.network.java.pabstract.MinecraftProtocolAbstract;

import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.UUID;

public class MinecraftProtocol404 extends MinecraftProtocolAbstract {
    private SubProtocol subProtocol = SubProtocol.HANDSHAKE;
    private PacketHeader header = new DefaultPacketHeader();
    private AESEncryption encrypt;

    private GameProfile profile;
    private String clientToken = "";
    private String accessToken = "";

    @SuppressWarnings("unused")
    private MinecraftProtocol404() {
    }

    public MinecraftProtocol404(SubProtocol subProtocol) {
        if(subProtocol != SubProtocol.LOGIN && subProtocol != SubProtocol.STATUS) {
            throw new IllegalArgumentException("Only login and status modes are permitted.");
        }

        this.subProtocol = subProtocol;
        if(subProtocol == SubProtocol.LOGIN) {
            this.profile = new GameProfile((UUID) null, "Player");
        }
    }

    public MinecraftProtocol404(String username) {
        this(SubProtocol.LOGIN);
        this.profile = new GameProfile((UUID) null, username);
    }

    public MinecraftProtocol404(String username, String password) throws RequestException {
        this(username, password, Proxy.NO_PROXY);
    }

    public MinecraftProtocol404(String username, String clientToken, String accessToken) throws RequestException {
        this(username, clientToken, accessToken, Proxy.NO_PROXY);
    }

    public MinecraftProtocol404(String username, String password, Proxy proxy) throws RequestException {
        this(username, UUID.randomUUID().toString(), password, false, proxy);
    }

    public MinecraftProtocol404(String username, String clientToken, String accessToken, Proxy proxy) throws RequestException {
        this(username, clientToken, accessToken, true, proxy);
    }

    private MinecraftProtocol404(String username, String clientToken, String using, boolean token, Proxy authProxy) throws RequestException {
        this(SubProtocol.LOGIN);

        AuthenticationService auth = new AuthenticationService(clientToken, authProxy);
        auth.setUsername(username);
        if(token) {
            auth.setAccessToken(using);
        } else {
            auth.setPassword(using);
        }

        auth.login();
        this.profile = auth.getSelectedProfile();
        this.clientToken =  auth.getClientToken();
        this.accessToken = auth.getAccessToken();
    }

    public MinecraftProtocol404(GameProfile profile, String clientToken, String accessToken) {
        this(SubProtocol.LOGIN);
        this.profile = profile;
        this.clientToken = clientToken;
        this.accessToken = accessToken;
    }

    public GameProfile getProfile() {
        return this.profile;
    }

    public String getClientToken() {
        return this.clientToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    @Override
    public String getSRVRecordPrefix() {
        return "_minecraft";
    }

    @Override
    public PacketHeader getPacketHeader() {
        return this.header;
    }

    @Override
    public PacketEncryption getEncryption() {
        return this.encrypt;
    }

    @Override
    public void newClientSession(Client client, Session session) {
        if(this.profile != null) {
            session.setFlag(MinecraftConstants.PROFILE_KEY, this.profile);
            session.setFlag(MinecraftConstants.ACCESS_TOKEN_KEY, this.accessToken);
        }

        this.setSubProtocol(this.subProtocol, true, session);
        session.addListener(new ClientListener());
    }

    @Override
    public void newServerSession(Server server, Session session) {
        this.setSubProtocol(SubProtocol.HANDSHAKE, false, session);
        session.addListener(new ServerListener());
    }

    protected void enableEncryption(Key key) {
        try {
            this.encrypt = new AESEncryption(key);
        } catch(GeneralSecurityException e) {
            throw new Error("Failed to enable protocol encryption.", e);
        }
    }

    public SubProtocol getSubProtocol() {
        return this.subProtocol;
    }

    protected void setSubProtocol(SubProtocol subProtocol, boolean client, Session session) {
        this.clearPackets();
        switch(subProtocol) {
            case HANDSHAKE:
                if(client) {
                    this.initClientHandshake(session);
                } else {
                    this.initServerHandshake(session);
                }

                break;
            case LOGIN:
                if(client) {
                    this.initClientLogin(session);
                } else {
                    this.initServerLogin(session);
                }

                break;
            case GAME:
                if(client) {
                    this.initClientGame(session);
                } else {
                    this.initServerGame(session);
                }

                break;
            case STATUS:
                if(client) {
                    this.initClientStatus(session);
                } else {
                    this.initServerStatus(session);
                }

                break;
        }

        this.subProtocol = subProtocol;
    }

    private void initClientHandshake(Session session) {
        this.registerOutgoing(0, HandshakePacket.class);
    }

    private void initServerHandshake(Session session) {
        this.registerIncoming(0, HandshakePacket.class);
    }

    private void initClientLogin(Session session) {
        this.registerIncoming(0x00, LoginDisconnectPacket.class);
        this.registerIncoming(0x01, EncryptionRequestPacket.class);
        this.registerIncoming(0x02, LoginSuccessPacket.class);
        this.registerIncoming(0x03, LoginSetCompressionPacket.class);
        this.registerIncoming(0x04, LoginPluginRequestPacket.class);

        this.registerOutgoing(0x00, LoginStartPacket.class);
        this.registerOutgoing(0x01, EncryptionResponsePacket.class);
        this.registerOutgoing(0x02, LoginPluginResponsePacket.class);
    }

    private void initServerLogin(Session session) {
        this.registerIncoming(0x00, LoginStartPacket.class);
        this.registerIncoming(0x01, EncryptionResponsePacket.class);
        this.registerIncoming(0x02, LoginPluginResponsePacket.class);

        this.registerOutgoing(0x00, LoginDisconnectPacket.class);
        this.registerOutgoing(0x01, EncryptionRequestPacket.class);
        this.registerOutgoing(0x02, LoginSuccessPacket.class);
        this.registerOutgoing(0x03, LoginSetCompressionPacket.class);
        this.registerOutgoing(0x04, LoginPluginRequestPacket.class);
    }

    private void initClientGame(Session session) {
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
        this.registerIncoming(0x0C, ServerBossBarPacket.class);
        this.registerIncoming(0x0D, ServerDifficultyPacket.class);
        this.registerIncoming(0x0E, ServerChatPacket.class);
        this.registerIncoming(0x0F, ServerMultiBlockChangePacket.class);
        this.registerIncoming(0x10, ServerTabCompletePacket.class);
        this.registerIncoming(0x11, ServerDeclareCommandsPacket.class);
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
        this.registerIncoming(0x2F, ServerCombatPacket.class);
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
        this.registerIncoming(0x3A, ServerAdvancementTabPacket.class);
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
        this.registerIncoming(0x51, ServerAdvancementsPacket.class);
        this.registerIncoming(0x52, ServerEntityPropertiesPacket.class);
        this.registerIncoming(0x53, ServerEntityEffectPacket.class);
        this.registerIncoming(0x54, ServerDeclareRecipesPacket.class);
        this.registerIncoming(0x55, ServerDeclareTagsPacket.class);

        this.registerOutgoing(0x00, ClientTeleportConfirmPacket.class);
        this.registerOutgoing(0x01, ClientBlockNBTRequestPacket.class);
        this.registerOutgoing(0x02, ClientChatPacket.class);
        this.registerOutgoing(0x03, ClientRequestPacket.class);
        this.registerOutgoing(0x04, ClientSettingsPacket.class);
        this.registerOutgoing(0x05, ClientTabCompletePacket.class);
        this.registerOutgoing(0x06, ClientConfirmTransactionPacket.class);
        this.registerOutgoing(0x07, ClientEnchantItemPacket.class);
        this.registerOutgoing(0x08, ClientWindowActionPacket.class);
        this.registerOutgoing(0x09, ClientCloseWindowPacket.class);
        this.registerOutgoing(0x0A, ClientPluginMessagePacket.class);
        this.registerOutgoing(0x0B, ClientEditBookPacket.class);
        this.registerOutgoing(0x0C, ClientEntityNBTRequestPacket.class);
        this.registerOutgoing(0x0D, ClientPlayerInteractEntityPacket.class);
        this.registerOutgoing(0x0E, ClientKeepAlivePacket.class);
        this.registerOutgoing(0x0F, ClientPlayerMovementPacket.class);
        this.registerOutgoing(0x10, ClientPlayerPositionPacket.class);
        this.registerOutgoing(0x11, ClientPlayerPositionRotationPacket.class);
        this.registerOutgoing(0x12, ClientPlayerRotationPacket.class);
        this.registerOutgoing(0x13, ClientVehicleMovePacket.class);
        this.registerOutgoing(0x14, ClientSteerBoatPacket.class);
        this.registerOutgoing(0x15, ClientMoveItemToHotbarPacket.class);
        this.registerOutgoing(0x16, ClientPrepareCraftingGridPacket.class);
        this.registerOutgoing(0x17, ClientPlayerAbilitiesPacket.class);
        this.registerOutgoing(0x18, ClientPlayerActionPacket.class);
        this.registerOutgoing(0x19, ClientPlayerStatePacket.class);
        this.registerOutgoing(0x1A, ClientSteerVehiclePacket.class);
        this.registerOutgoing(0x1B, ClientCraftingBookDataPacket.class);
        this.registerOutgoing(0x1C, ClientRenameItemPacket.class);
        this.registerOutgoing(0x1D, ClientResourcePackStatusPacket.class);
        this.registerOutgoing(0x1E, ClientAdvancementTabPacket.class);
        this.registerOutgoing(0x1F, ClientSelectTradePacket.class);
        this.registerOutgoing(0x20, ClientSetBeaconEffectPacket.class);
        this.registerOutgoing(0x21, ClientPlayerChangeHeldItemPacket.class);
        this.registerOutgoing(0x22, ClientUpdateCommandBlockPacket.class);
        this.registerOutgoing(0x23, ClientUpdateCommandBlockMinecartPacket.class);
        this.registerOutgoing(0x24, ClientCreativeInventoryActionPacket.class);
        this.registerOutgoing(0x25, ClientUpdateStructureBlockPacket.class);
        this.registerOutgoing(0x26, ClientUpdateSignPacket.class);
        this.registerOutgoing(0x27, ClientPlayerSwingArmPacket.class);
        this.registerOutgoing(0x28, ClientSpectatePacket.class);
        this.registerOutgoing(0x29, ClientPlayerPlaceBlockPacket.class);
        this.registerOutgoing(0x2A, ClientPlayerUseItemPacket.class);
    }

    private void initServerGame(Session session) {
        this.registerIncoming(0x00, ClientTeleportConfirmPacket.class);
        this.registerIncoming(0x01, ClientBlockNBTRequestPacket.class);
        this.registerIncoming(0x02, ClientChatPacket.class);
        this.registerIncoming(0x03, ClientRequestPacket.class);
        this.registerIncoming(0x04, ClientSettingsPacket.class);
        this.registerIncoming(0x05, ClientTabCompletePacket.class);
        this.registerIncoming(0x06, ClientConfirmTransactionPacket.class);
        this.registerIncoming(0x07, ClientEnchantItemPacket.class);
        this.registerIncoming(0x08, ClientWindowActionPacket.class);
        this.registerIncoming(0x09, ClientCloseWindowPacket.class);
        this.registerIncoming(0x0A, ClientPluginMessagePacket.class);
        this.registerIncoming(0x0B, ClientEditBookPacket.class);
        this.registerIncoming(0x0C, ClientEntityNBTRequestPacket.class);
        this.registerIncoming(0x0D, ClientPlayerInteractEntityPacket.class);
        this.registerIncoming(0x0E, ClientKeepAlivePacket.class);
        this.registerIncoming(0x0F, ClientPlayerMovementPacket.class);
        this.registerIncoming(0x10, ClientPlayerPositionPacket.class);
        this.registerIncoming(0x11, ClientPlayerPositionRotationPacket.class);
        this.registerIncoming(0x12, ClientPlayerRotationPacket.class);
        this.registerIncoming(0x13, ClientVehicleMovePacket.class);
        this.registerIncoming(0x14, ClientSteerBoatPacket.class);
        this.registerIncoming(0x15, ClientMoveItemToHotbarPacket.class);
        this.registerIncoming(0x16, ClientPrepareCraftingGridPacket.class);
        this.registerIncoming(0x17, ClientPlayerAbilitiesPacket.class);
        this.registerIncoming(0x18, ClientPlayerActionPacket.class);
        this.registerIncoming(0x19, ClientPlayerStatePacket.class);
        this.registerIncoming(0x1A, ClientSteerVehiclePacket.class);
        this.registerIncoming(0x1B, ClientCraftingBookDataPacket.class);
        this.registerIncoming(0x1C, ClientRenameItemPacket.class);
        this.registerIncoming(0x1D, ClientResourcePackStatusPacket.class);
        this.registerIncoming(0x1E, ClientAdvancementTabPacket.class);
        this.registerIncoming(0x1F, ClientSelectTradePacket.class);
        this.registerIncoming(0x20, ClientSetBeaconEffectPacket.class);
        this.registerIncoming(0x21, ClientPlayerChangeHeldItemPacket.class);
        this.registerIncoming(0x22, ClientUpdateCommandBlockPacket.class);
        this.registerIncoming(0x23, ClientUpdateCommandBlockMinecartPacket.class);
        this.registerIncoming(0x24, ClientCreativeInventoryActionPacket.class);
        this.registerIncoming(0x25, ClientUpdateStructureBlockPacket.class);
        this.registerIncoming(0x26, ClientUpdateSignPacket.class);
        this.registerIncoming(0x27, ClientPlayerSwingArmPacket.class);
        this.registerIncoming(0x28, ClientSpectatePacket.class);
        this.registerIncoming(0x29, ClientPlayerPlaceBlockPacket.class);
        this.registerIncoming(0x2A, ClientPlayerUseItemPacket.class);

        this.registerOutgoing(0x00, ServerSpawnObjectPacket.class);
        this.registerOutgoing(0x01, ServerSpawnExpOrbPacket.class);
        this.registerOutgoing(0x02, ServerSpawnGlobalEntityPacket.class);
        this.registerOutgoing(0x03, ServerSpawnMobPacket.class);
        this.registerOutgoing(0x04, ServerSpawnPaintingPacket.class);
        this.registerOutgoing(0x05, ServerSpawnPlayerPacket.class);
        this.registerOutgoing(0x06, ServerEntityAnimationPacket.class);
        this.registerOutgoing(0x07, ServerStatisticsPacket.class);
        this.registerOutgoing(0x08, ServerBlockBreakAnimPacket.class);
        this.registerOutgoing(0x09, ServerUpdateTileEntityPacket.class);
        this.registerOutgoing(0x0A, ServerBlockValuePacket.class);
        this.registerOutgoing(0x0B, ServerBlockChangePacket.class);
        this.registerOutgoing(0x0C, ServerBossBarPacket.class);
        this.registerOutgoing(0x0D, ServerDifficultyPacket.class);
        this.registerOutgoing(0x0E, ServerChatPacket.class);
        this.registerOutgoing(0x0F, ServerMultiBlockChangePacket.class);
        this.registerOutgoing(0x10, ServerTabCompletePacket.class);
        this.registerOutgoing(0x11, ServerDeclareCommandsPacket.class);
        this.registerOutgoing(0x12, ServerConfirmTransactionPacket.class);
        this.registerOutgoing(0x13, ServerCloseWindowPacket.class);
        this.registerOutgoing(0x14, ServerOpenWindowPacket.class);
        this.registerOutgoing(0x15, ServerWindowItemsPacket.class);
        this.registerOutgoing(0x16, ServerWindowPropertyPacket.class);
        this.registerOutgoing(0x17, ServerSetSlotPacket.class);
        this.registerOutgoing(0x18, ServerSetCooldownPacket.class);
        this.registerOutgoing(0x19, ServerPluginMessagePacket.class);
        this.registerOutgoing(0x1A, ServerPlaySoundPacket.class);
        this.registerOutgoing(0x1B, ServerDisconnectPacket.class);
        this.registerOutgoing(0x1C, ServerEntityStatusPacket.class);
        this.registerOutgoing(0x1D, ServerNBTResponsePacket.class);
        this.registerOutgoing(0x1E, ServerExplosionPacket.class);
        this.registerOutgoing(0x1F, ServerUnloadChunkPacket.class);
        this.registerOutgoing(0x20, ServerNotifyClientPacket.class);
        this.registerOutgoing(0x21, ServerKeepAlivePacket.class);
        this.registerOutgoing(0x22, ServerChunkDataPacket.class);
        this.registerOutgoing(0x23, ServerPlayEffectPacket.class);
        this.registerOutgoing(0x24, ServerSpawnParticlePacket.class);
        this.registerOutgoing(0x25, ServerJoinGamePacket.class);
        this.registerOutgoing(0x26, ServerMapDataPacket.class);
        this.registerOutgoing(0x27, ServerEntityMovementPacket.class);
        this.registerOutgoing(0x28, ServerEntityPositionPacket.class);
        this.registerOutgoing(0x29, ServerEntityPositionRotationPacket.class);
        this.registerOutgoing(0x2A, ServerEntityRotationPacket.class);
        this.registerOutgoing(0x2B, ServerVehicleMovePacket.class);
        this.registerOutgoing(0x2C, ServerOpenTileEntityEditorPacket.class);
        this.registerOutgoing(0x2D, ServerPreparedCraftingGridPacket.class);
        this.registerOutgoing(0x2E, ServerPlayerAbilitiesPacket.class);
        this.registerOutgoing(0x2F, ServerCombatPacket.class);
        this.registerOutgoing(0x30, ServerPlayerListEntryPacket.class);
        this.registerOutgoing(0x31, ServerPlayerFacingPacket.class);
        this.registerOutgoing(0x32, ServerPlayerPositionRotationPacket.class);
        this.registerOutgoing(0x33, ServerPlayerUseBedPacket.class);
        this.registerOutgoing(0x34, ServerUnlockRecipesPacket.class);
        this.registerOutgoing(0x35, ServerEntityDestroyPacket.class);
        this.registerOutgoing(0x36, ServerEntityRemoveEffectPacket.class);
        this.registerOutgoing(0x37, ServerResourcePackSendPacket.class);
        this.registerOutgoing(0x38, ServerRespawnPacket.class);
        this.registerOutgoing(0x39, ServerEntityHeadLookPacket.class);
        this.registerOutgoing(0x3A, ServerAdvancementTabPacket.class);
        this.registerOutgoing(0x3B, ServerWorldBorderPacket.class);
        this.registerOutgoing(0x3C, ServerSwitchCameraPacket.class);
        this.registerOutgoing(0x3D, ServerPlayerChangeHeldItemPacket.class);
        this.registerOutgoing(0x3E, ServerDisplayScoreboardPacket.class);
        this.registerOutgoing(0x3F, ServerEntityMetadataPacket.class);
        this.registerOutgoing(0x40, ServerEntityAttachPacket.class);
        this.registerOutgoing(0x41, ServerEntityVelocityPacket.class);
        this.registerOutgoing(0x42, ServerEntityEquipmentPacket.class);
        this.registerOutgoing(0x43, ServerPlayerSetExperiencePacket.class);
        this.registerOutgoing(0x44, ServerPlayerHealthPacket.class);
        this.registerOutgoing(0x45, ServerScoreboardObjectivePacket.class);
        this.registerOutgoing(0x46, ServerEntitySetPassengersPacket.class);
        this.registerOutgoing(0x47, ServerTeamPacket.class);
        this.registerOutgoing(0x48, ServerUpdateScorePacket.class);
        this.registerOutgoing(0x49, ServerSpawnPositionPacket.class);
        this.registerOutgoing(0x4A, ServerUpdateTimePacket.class);
        this.registerOutgoing(0x4B, ServerTitlePacket.class);
        this.registerOutgoing(0x4C, ServerStopSoundPacket.class);
        this.registerOutgoing(0x4D, ServerPlayBuiltinSoundPacket.class);
        this.registerOutgoing(0x4E, ServerPlayerListDataPacket.class);
        this.registerOutgoing(0x4F, ServerEntityCollectItemPacket.class);
        this.registerOutgoing(0x50, ServerEntityTeleportPacket.class);
        this.registerOutgoing(0x51, ServerAdvancementsPacket.class);
        this.registerOutgoing(0x52, ServerEntityPropertiesPacket.class);
        this.registerOutgoing(0x53, ServerEntityEffectPacket.class);
        this.registerOutgoing(0x54, ServerDeclareRecipesPacket.class);
        this.registerOutgoing(0x55, ServerDeclareTagsPacket.class);
    }

    private void initClientStatus(Session session) {
        this.registerIncoming(0x00, StatusResponsePacket.class);
        this.registerIncoming(0x01, StatusPongPacket.class);

        this.registerOutgoing(0x00, StatusQueryPacket.class);
        this.registerOutgoing(0x01, StatusPingPacket.class);
    }

    private void initServerStatus(Session session) {
        this.registerIncoming(0x00, StatusQueryPacket.class);
        this.registerIncoming(0x01, StatusPingPacket.class);

        this.registerOutgoing(0x00, StatusResponsePacket.class);
        this.registerOutgoing(0x01, StatusPongPacket.class);
    }
}
