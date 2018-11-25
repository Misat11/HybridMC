package misat11.hybrid.downstream.translators;

import com.flowpowered.math.vector.Vector3i;

import misat11.hybrid.downstream.IDownstreamTranslator;
import misat11.hybrid.downstream.WatchedEntity;
import misat11.hybrid.entity.EntityType;
import misat11.hybrid.entity.Painting;
import misat11.hybrid.network.bedrock.BedrockPacket;
import misat11.hybrid.network.bedrock.packet.AddPaintingPacket;
import misat11.hybrid.network.bedrock.session.HybridSession;
import misat11.hybrid.network.java.p404.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket404;
import misat11.hybrid.network.java.pabstract.data.game.entity.type.PaintingType;

public class SpawnPaintingTranslator implements IDownstreamTranslator<ServerSpawnPaintingPacket404> {

	@Override
	public BedrockPacket[] translate(HybridSession session, ServerSpawnPaintingPacket404 packet) {
		AddPaintingPacket app = new AddPaintingPacket();
		app.setRuntimeEntityId(packet.getEntityId());
		app.setUniqueEntityId(packet.getEntityId());
		int x = packet.getPosition().getX();
		int y = packet.getPosition().getY();
		int z = packet.getPosition().getZ();
		switch (packet.getDirection()) {
		case NORTH:
			z += 1;
			break;
		case SOUTH:
			z -= 1;
			break;
		case EAST:
			x -= 1;
			break;
		case WEST:
			x += 1;
			break;
		default:
			break;
		}
		app.setBlockPosition(new Vector3i(x, y, z));
		app.setDirection(packet.getDirection().ordinal());
		app.setTitle(convert(packet.getPaintingType()).getName());
		session.getDownstream().getWatchedEntities().put((long) packet.getEntityId(),
				new WatchedEntity(packet.getEntityId(), EntityType.PAINTING.getType(), x, y, z, 0, 0));
		return new BedrockPacket[] { app };
	}

	public static Painting.Type convert(PaintingType pc) {
		switch (pc) {
		case ALBAN:
			return Painting.Type.ALBAN;
		case AZTEC:
			return Painting.Type.AZTEC;
		case AZTEC2:
			return Painting.Type.AZTEC2;
		case BOMB:
			return Painting.Type.BOMB;
		case BURNING_SKULL:
			return Painting.Type.BURNING_SKULL;
		case BUST:
			return Painting.Type.BUST;
		case COURBET:
			return Painting.Type.COURBET;
		case CREEBET:
			return Painting.Type.CREEBET;
		case DONKEY_KONG:
			return Painting.Type.DONKEY_KONG;
		case FIGHTERS:
			return Painting.Type.FIGHTERS;
		case GRAHAM:
			return Painting.Type.GRAHAM;
		case KEBAB:
			return Painting.Type.KEBAB;
		case MATCH:
			return Painting.Type.MATCH;
		case PIG_SCENE:
			return Painting.Type.PIG_SCENE;
		case PLANT:
			return Painting.Type.PLANT;
		case POINTER:
			return Painting.Type.POINTER;
		case POOL:
			return Painting.Type.POOL;
		case SEA:
			return Painting.Type.SEA;
		case SKELETON:
			return Painting.Type.SKELETON;
		case SKULL_AND_ROSES:
			return Painting.Type.SKULL_AND_ROSES;
		case STAGE:
			return Painting.Type.STAGE;
		case SUNSET:
			return Painting.Type.SUNSET;
		case VOID:
			return Painting.Type.VOID;
		case WANDERER:
			return Painting.Type.WANDERER;
		case WASTELAND:
			return Painting.Type.WASTELAND;
		case WITHER:
			return Painting.Type.WITHER;
		default:
			return Painting.Type.KEBAB;
		}
	}

}
