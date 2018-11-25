package misat11.hybrid.network.java.pabstract.packet.ingame.client.window;

import misat11.hybrid.network.java.pabstract.data.game.entity.metadata.Position;
import misat11.hybrid.network.java.pabstract.data.game.window.UpdateStructureBlockAction;
import misat11.hybrid.network.java.pabstract.data.game.window.UpdateStructureBlockMode;
import misat11.hybrid.network.java.pabstract.data.game.world.block.StructureMirror;
import misat11.hybrid.network.java.pabstract.data.game.world.block.StructureRotation;
import misat11.hybrid.network.java.pabstract.packet.IMinecraftPacket;

public interface ClientUpdateStructureBlockPacket extends IMinecraftPacket {

	public Position getPosition();

	public UpdateStructureBlockAction getAction();

	public UpdateStructureBlockMode getMode();

	public String getName();

	public Position getOffset();

	public Position getSize();

	public StructureMirror getMirror();

	public StructureRotation getRotation();

	public String getMetadata();

	public float getIntegrity();

	public long getSeed();

	public boolean isIgnoreEntities();

	public boolean isShowAir();

	public boolean isShowBoundingBox();
}
