package misat11.hybrid.network.bedrock.session;

import com.flowpowered.math.vector.Vector3f;

import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.downstream.cache.MovementCache;
import misat11.hybrid.downstream.translators.StartGameTranslator;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.inventory.transaction.ItemReleaseTransaction;
import misat11.hybrid.inventory.transaction.ItemUseOnEntityTransaction;
import misat11.hybrid.inventory.transaction.ItemUseTransaction;
import misat11.hybrid.network.bedrock.NetworkPacketHandler;
import misat11.hybrid.network.bedrock.packet.*;
import misat11.hybrid.network.bedrock.packet.AnimatePacket.Animation;
import misat11.hybrid.network.java.p404.data.game.ClientRequest;
import misat11.hybrid.network.java.p404.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.p404.data.game.entity.player.GameMode;
import misat11.hybrid.network.java.p404.data.game.entity.player.Hand;
import misat11.hybrid.network.java.p404.data.game.entity.player.InteractAction;
import misat11.hybrid.network.java.p404.data.game.entity.player.PlayerAction;
import misat11.hybrid.network.java.p404.data.game.entity.player.PlayerState;
import misat11.hybrid.network.java.p404.data.game.setting.ChatVisibility;
import misat11.hybrid.network.java.p404.data.game.setting.SkinPart;
import misat11.hybrid.network.java.p404.data.game.world.block.BlockFace;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientRequestPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.ClientSettingsPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerAbilitiesPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerActionPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerChangeHeldItemPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerInteractEntityPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerPlaceBlockPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerStatePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.player.ClientPlayerSwingArmPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientSteerBoatPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientSteerVehiclePacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientTeleportConfirmPacket;
import misat11.hybrid.network.java.p404.packet.ingame.client.world.ClientVehicleMovePacket;
import misat11.hybrid.typeremapper.EntityRemapper;
import misat11.hybrid.utils.BlockFaceModificator;

public class HybridPlayPacketHandler implements NetworkPacketHandler {

	private final HybridSession session;

	public HybridPlayPacketHandler(HybridSession session) {
		this.session = session;
	}

	@Override
	public void handle(AdventureSettingsPacket packet) {
		session.getDownstream()
				.send(new ClientPlayerAbilitiesPacket(false,
						(packet.getFlags() & StartGameTranslator.ALLOW_FLIGHT) == StartGameTranslator.ALLOW_FLIGHT,
						(packet.getFlags() & StartGameTranslator.FLYING) == StartGameTranslator.FLYING, false, 0, 0));
	}

	@Override
	public void handle(AnimatePacket packet) {
		if (packet.getAction() == Animation.SWING_ARM)
			session.getDownstream().send(new ClientPlayerSwingArmPacket(Hand.MAIN_HAND));

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
		session.getDownstream().sendChat(packet.getCommand());
	}

	@Override
	public void handle(ContainerClosePacket packet) {
		session.getDownstream().getInventoryCache().closeOpened(session, false);
	}

	@Override
	public void handle(CraftingEventPacket packet) {

	}

	@Override
	public void handle(EntityEventPacket packet) {
		switch (packet.getEvent()) {
		case EATING_ITEM:
			if (packet.getData() != 0) {
				session.sendImmediatePackage(packet);
			}
			break;
		case COMPLETE_TRADE:
			// Custom Payload MC|TrSel
			break;
		default:
			break;
		}
	}

	@Override
	public void handle(EntityPickRequestPacket packet) {

	}

	@Override
	public void handle(EventPacket packet) {

	}

	@Override
	public void handle(InteractPacket packet) {
		switch (packet.getAction()) {
		case LEAVE_VEHICLE:
			session.getDownstream().send(new ClientSteerVehiclePacket(0, 0, false, true));
			break;
		case OPEN_INVENTORY:
			session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
					PlayerState.OPEN_HORSE_INVENTORY));
			break;
		default:
			break;
		}
	}

	@Override
	public void handle(InventoryContentPacket packet) {

	}

	@Override
	public void handle(InventorySlotPacket packet) {

	}

	@Override
	public void handle(InventoryTransactionPacket packet) {
		switch (packet.getTransaction().getType()) {
		case ITEM_RELEASE:
			ItemReleaseTransaction releaseTransaction = (ItemReleaseTransaction) packet.getTransaction();
			switch (releaseTransaction.getAction()) {
			case RELEASE:
				session.getDownstream().send(new ClientPlayerActionPacket(PlayerAction.RELEASE_USE_ITEM,
						new Position(0, 0, 0), BlockFace.DOWN));
				break;
			default:
				break;
			}
			break;
		case ITEM_USE:
			ItemUseTransaction itemTransaction = (ItemUseTransaction) packet.getTransaction();
			BlockFaceModificator bfmod = BlockFaceModificator.getByFace(itemTransaction.getFace());
			switch (itemTransaction.getAction()) {
			case BREAK:
				// instant break
				if (session.getDownstream().gamemode == GameMode.CREATIVE) {

					session.getDownstream()
							.send(new ClientPlayerActionPacket(PlayerAction.START_DIGGING,
									new Position(itemTransaction.getPosition().getX(),
											itemTransaction.getPosition().getY(), itemTransaction.getPosition().getZ()),
									BlockFace.DOWN));

					session.getDownstream()
							.send(new ClientPlayerActionPacket(PlayerAction.FINISH_DIGGING,
									new Position(itemTransaction.getPosition().getX(),
											itemTransaction.getPosition().getY(), itemTransaction.getPosition().getZ()),
									BlockFace.DOWN));

				}
				break;
			case PLACE:
				session.getDownstream()
						.send(new ClientPlayerPlaceBlockPacket(bfmod.modPosition(itemTransaction.getPosition()),
								bfmod.getTranslatedBlockFace(), Hand.MAIN_HAND,
								itemTransaction.getClickPosition().getX(), itemTransaction.getClickPosition().getY(),
								itemTransaction.getClickPosition().getZ()));
				break;
			case USE:
				bfmod = BlockFaceModificator.getByFace(-1);
				session.getDownstream()
						.send(new ClientPlayerPlaceBlockPacket(bfmod.modPosition(itemTransaction.getPosition()),
								bfmod.getTranslatedBlockFace(), Hand.MAIN_HAND,
								itemTransaction.getClickPosition().getX(), itemTransaction.getClickPosition().getY(),
								itemTransaction.getClickPosition().getZ()));
				break;
			default:
				break;
			}
			break;
		case ITEM_USE_ON_ENTITY:
			ItemUseOnEntityTransaction entityTransaction = (ItemUseOnEntityTransaction) packet.getTransaction();
			switch (entityTransaction.getAction()) {
			case ATTACK:
				session.getDownstream().send(new ClientPlayerInteractEntityPacket(
						(int) entityTransaction.getRuntimeEntityId(), InteractAction.ATTACK));
				break;
			case INTERACT:
				session.getDownstream()
						.send(new ClientPlayerInteractEntityPacket((int) entityTransaction.getRuntimeEntityId(),
								InteractAction.INTERACT, entityTransaction.getClickPosition().getX(),
								entityTransaction.getClickPosition().getY(),
								entityTransaction.getClickPosition().getZ(), Hand.MAIN_HAND));
				break;
			case ITEM_INTERACT:
				session.getDownstream()
						.send(new ClientPlayerInteractEntityPacket((int) entityTransaction.getRuntimeEntityId(),
								InteractAction.INTERACT_AT, entityTransaction.getClickPosition().getX(),
								entityTransaction.getClickPosition().getY(),
								entityTransaction.getClickPosition().getZ(), Hand.MAIN_HAND));
				break;
			default:
				break;

			}
			break;
		default:
			break;

		}
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
		if(packet.getHotbarSlot() > 8) {
			return;
		}
		if (packet.getWindowId() == 0) {
			session.getDownstream().send(new ClientPlayerChangeHeldItemPacket(packet.getHotbarSlot()));
		}
	}

	@Override
	public void handle(ModalFormResponsePacket packet) {

	}

	@Override
	public void handle(MoveEntityAbsolutePacket packet) {
		MovementCache cache = session.getDownstream().getMovementCache();
		if (cache == null) {
			return; // Player isn't initialized yet
		}
		WatchedEntity vehicle = session.getDownstream().getWatchedEntities().get(packet.getRuntimeEntityId());
		if (vehicle != null) {
			if (vehicle.getType() == EntityType.BOAT.getType()) {
				session.getDownstream()
						.send(new ClientSteerBoatPacket(cache.isPERightPaddleTurning(), cache.isPELeftPaddleTurning()));
			}
		}
		Vector3f offset = EntityRemapper.makeOffset(vehicle.getType());
		session.getDownstream().getWatchedEntities().get(session.getDownstream().playerEntityId)
				.setLastRidingYaw(packet.getRotation().getYaw());

		float realPitch = (360f / 256f) * packet.getRotation().getPitch();
		float realYaw = (360f / 256f) * packet.getRotation().getYaw();
		session.getDownstream()
				.send(new ClientVehicleMovePacket(packet.getPosition().getX() - offset.getX(),
						packet.getPosition().getY() - offset.getY(), packet.getPosition().getZ() - offset.getZ(),
						realYaw, realPitch));
	}

	@Override
	public void handle(MovePlayerPacket packet) {
		MovementCache cache = session.getDownstream().getMovementCache();
		WatchedEntity entity = session.getDownstream().getWatchedEntities().get(session.getDownstream().playerEntityId);
		if (entity == null || cache == null) {
			return; // Player isn't initialized yet
		}
		cache.updatePEPositionLeniency(packet.getPosition().getY());
		cache.setPEClientPosition(packet.getPosition().getX(), packet.getPosition().getY(),
				packet.getPosition().getZ());

		int teleport = cache.teleportConfirm();
		if (teleport != -1) {
			session.getDownstream().send(new ClientTeleportConfirmPacket(teleport));
			session.getDownstream().send(new ClientPlayerPositionRotationPacket(packet.isOnGround(), cache.getX(),
					cache.getY(), cache.getZ(), packet.getRotation().getHeadYaw(), packet.getRotation().getPitch()));
		}

		float yaw = packet.getRotation().getYaw();
		Vector3f offset = EntityRemapper.makeOffset(EntityType.PLAYER.getType());
		if (entity.isRiding()) {
			WatchedEntity vehicle = session.getDownstream().getWatchedEntities().get(entity.getVehicleID());
			if (vehicle != null) {
				if (vehicle.getType() == EntityType.BOAT.getType()) {
					yaw = ((360f / 256f) * entity.getLastRidingYaw()) + yaw + 90;
				}
			}
			session.getDownstream()
			.send(new ClientVehicleMovePacket(packet.getPosition().getX() - offset.getX(),
					packet.getPosition().getY() - offset.getY(), packet.getPosition().getZ() - offset.getZ(), yaw,
					packet.getRotation().getPitch()));
			
		} else {
			session.getDownstream()
					.send(new ClientPlayerPositionRotationPacket(packet.isOnGround(),
							packet.getPosition().getX() - offset.getX(), packet.getPosition().getY() - offset.getY(),
							packet.getPosition().getZ() - offset.getZ(), yaw, packet.getRotation().getPitch()));
		}
	}

	@Override
	public void handle(PhotoTransferPacket packet) {

	}

	@Override
	public void handle(PlayerActionPacket packet) {
		switch (packet.getAction()) {
		case ABORT_BREAK:
			session.getDownstream()
					.send(new ClientPlayerActionPacket(
							PlayerAction.CANCEL_DIGGING, new Position(packet.getBlockPosition().getX(),
									packet.getBlockPosition().getY(), packet.getBlockPosition().getZ()),
							BlockFace.values()[packet.getFace().ordinal()]));
			break;
		case RESPAWN:
		case DIMENSION_CHANGE_REQUEST:
			session.getDownstream().send(new ClientRequestPacket(ClientRequest.RESPAWN));
			break;
		case DROP_ITEM:
			session.getDownstream().send(new ClientPlayerActionPacket(PlayerAction.RELEASE_USE_ITEM,
					new Position(0, 0, 0), BlockFace.UP));
			break;
		case START_BREAK:
			session.getDownstream()
					.send(new ClientPlayerActionPacket(
							PlayerAction.START_DIGGING, new Position(packet.getBlockPosition().getX(),
									packet.getBlockPosition().getY(), packet.getBlockPosition().getZ()),
							BlockFace.values()[packet.getFace().ordinal()]));
			break;
		case START_SNEAK:
			session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
					PlayerState.START_SNEAKING));
			break;
		case START_SPRINT:
			session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
					PlayerState.START_SPRINTING));
			break;
		case STOP_BREAK:
			session.getDownstream()
					.send(new ClientPlayerActionPacket(
							PlayerAction.FINISH_DIGGING, new Position(packet.getBlockPosition().getX(),
									packet.getBlockPosition().getY(), packet.getBlockPosition().getZ()),
							BlockFace.values()[packet.getFace().ordinal()]));
			break;
		case STOP_GLIDE:
		case START_GLIDE:
			session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
					PlayerState.START_ELYTRA_FLYING));
			break;
		case STOP_SLEEP:
			session.getDownstream().send(
					new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId, PlayerState.LEAVE_BED));
			break;
		case STOP_SNEAK:
			session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
					PlayerState.STOP_SNEAKING));
			break;
		case STOP_SPRINT:
			session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
					PlayerState.STOP_SPRINTING));
			break;
		case STOP_SWIMMING:
			session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
					PlayerState.STOP_SNEAKING));
			break;
		default:
			break;

		}
	}

	@Override
	public void handle(PlayerHotbarPacket packet) {

	}

	@Override
	public void handle(PlayerInputPacket packet) {
		session.getDownstream().send(new ClientSteerVehiclePacket(packet.getInputMotion().getX(),
				packet.getInputMotion().getY(), packet.isUnknown0(), packet.isUnknown1()));
	}

	@Override
	public void handle(PlayerSkinPacket packet) {

	}

	@Override
	public void handle(PurchaseReceiptPacket packet) {

	}

	@Override
	public void handle(RequestChunkRadiusPacket packet) {
		session.getDownstream().send(new ClientSettingsPacket("en_US", packet.getRadius(), ChatVisibility.FULL, true,
				new SkinPart[] {}, Hand.MAIN_HAND));

	}

	@Override
	public void handle(ResourcePackChunkRequestPacket packet) {
	}

	@Override
	public void handle(ResourcePackClientResponsePacket packet) {
	}

	@Override
	public void handle(RiderJumpPacket packet) {
		session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
				PlayerState.START_HORSE_JUMP, packet.getUnknown0() / 2));
		session.getDownstream().send(new ClientSteerVehiclePacket(0, 0, true, false));
		session.getDownstream().send(new ClientPlayerStatePacket((int) session.getDownstream().playerEntityId,
				PlayerState.STOP_HORSE_JUMP, 0));
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
		session.getDownstream().sendChat(packet.getMessage().getMessage());
	}

}
