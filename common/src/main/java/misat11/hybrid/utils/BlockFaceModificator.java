package misat11.hybrid.utils;

import java.util.HashMap;
import java.util.Map;

import com.flowpowered.math.vector.Vector3i;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.Position;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockFace;

public enum BlockFaceModificator {
	SELF(BlockFace.SPECIAL, -1, 0, 0, 0),
	BOTTOM(BlockFace.DOWN, 0, 0, -1, 0),
	TOP(BlockFace.UP, 1, 0, 1, 0),
	NORTH(BlockFace.NORTH, 2, 0, 0, -1),
	SOUTH(BlockFace.SOUTH, 3, 0, 0, 1),
	WEST(BlockFace.WEST, 4, -1, 0, 0),
	EAST(BlockFace.EAST, 5, 1, 0, 0);

	private static final Map<Integer, BlockFaceModificator> byId = new HashMap<Integer, BlockFaceModificator>();

	static {
		for (BlockFaceModificator entry : values()) {
			byId.put(entry.peNetId, entry);
		}
	}

	private final BlockFace face;
	private final int peNetId;
	private final int modX;
	private final int modY;
	private final int modZ;

	private BlockFaceModificator(BlockFace face, int peNetId, int modX, int modY, int modZ) {
		this.face = face;
		this.modX = modX;
		this.modY = modY;
		this.modZ = modZ;
		this.peNetId = peNetId;
	}

	public Position modPosition(Position position) {
		return modPosition(position.getX(), position.getY(), position.getZ());
	}

	public Position modPosition(Vector3i position) {
		return modPosition(position.getX(), position.getY(), position.getZ());
	}

	public Position modPosition(int ox, int oy, int oz) {
		int x = modX + ox;
		int y = modY + oy;
		int z = modZ + oz;
		return new Position(x, y, z);
	}

	public BlockFace getTranslatedBlockFace() {
		return face;
	}

	public static BlockFaceModificator getByFace(int face) {
		BlockFaceModificator bface = byId.get(face);
		if (bface == null) {
			return BlockFaceModificator.SELF;
		}
		return bface;
	}

}
