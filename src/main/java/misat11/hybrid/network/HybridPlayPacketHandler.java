package misat11.hybrid.network;

import com.nukkitx.server.network.bedrock.NetworkPacketHandler;
import com.nukkitx.server.network.bedrock.packet.*;

public class HybridPlayPacketHandler implements NetworkPacketHandler {

	private final HybridSession session;

	public HybridPlayPacketHandler(HybridSession session) {
		this.session = session;
	}

	@Override
	public void handle(AdventureSettingsPacket packet) {
	}

	@Override
	public void handle(AnimatePacket packet) {
	}

	@Override
	public void handle(BlockEntityDataPacket packet) {

	}

	@Override
	public void handle(BlockPickRequestPacket packet) {

	}

	@Override
	public void handle(BookEditPacket packet) {

	}

	@Override
	public void handle(CommandBlockUpdatePacket packet) {

	}

	@Override
	public void handle(CommandRequestPacket packet) {
	}

	@Override
	public void handle(ContainerClosePacket packet) {
	}

	@Override
	public void handle(CraftingEventPacket packet) {

	}

	@Override
	public void handle(EntityEventPacket packet) {

	}

	@Override
	public void handle(EntityPickRequestPacket packet) {

	}

	@Override
	public void handle(EventPacket packet) {

	}

	@Override
	public void handle(InteractPacket packet) {
	}

	@Override
	public void handle(InventoryContentPacket packet) {

	}

	@Override
	public void handle(InventorySlotPacket packet) {

	}

	@Override
	public void handle(InventoryTransactionPacket packet) {
	}

	@Override
	public void handle(ItemFrameDropItemPacket packet) {

	}

	@Override
	public void handle(LevelSoundEventPacket packet) {
	}

	@Override
	public void handle(MapInfoRequestPacket packet) {

	}

	@Override
	public void handle(MobArmorEquipmentPacket packet) {

	}

	@Override
	public void handle(MobEquipmentPacket packet) {
	}

	@Override
	public void handle(ModalFormResponsePacket packet) {

	}

	@Override
	public void handle(MoveEntityAbsolutePacket packet) {

	}

	@Override
	public void handle(MovePlayerPacket packet) {
	}

	@Override
	public void handle(PhotoTransferPacket packet) {

	}

	@Override
	public void handle(PlayerActionPacket packet) {
	}

	@Override
	public void handle(PlayerHotbarPacket packet) {

	}

	@Override
	public void handle(PlayerInputPacket packet) {

	}

	@Override
	public void handle(PlayerSkinPacket packet) {

	}

	@Override
	public void handle(PurchaseReceiptPacket packet) {

	}

	@Override
	public void handle(RequestChunkRadiusPacket packet) {

	}

	@Override
	public void handle(ResourcePackChunkRequestPacket packet) {
	}

	@Override
	public void handle(ResourcePackClientResponsePacket packet) {
	}

	@Override
	public void handle(RiderJumpPacket packet) {

	}

	@Override
	public void handle(ServerSettingsRequestPacket packet) {

	}

	@Override
	public void handle(SetLocalPlayerAsInitializedPacket packet) {
	}

	@Override
	public void handle(SetDefaultGameTypePacket packet) {

	}

	@Override
	public void handle(SetPlayerGameTypePacket packet) {

	}

	@Override
	public void handle(SubClientLoginPacket packet) {

	}

	@Override
	public void handle(TextPacket packet) {
	}

}
