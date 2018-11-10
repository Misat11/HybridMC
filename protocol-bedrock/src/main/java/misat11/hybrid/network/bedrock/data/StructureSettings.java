package misat11.hybrid.network.bedrock.data;

import lombok.Value;
import misat11.hybrid.util.BoundingBox;

@Value
public class StructureSettings {
    private boolean integrity;
    private int seed;
    private int mirror;
    private int rotation;
    private boolean ignoreEntities;
    private boolean ignoreStructureBlocks;
    private BoundingBox boundingBox;
}
