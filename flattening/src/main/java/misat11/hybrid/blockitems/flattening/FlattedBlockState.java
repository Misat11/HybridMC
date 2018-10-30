package misat11.hybrid.blockitems.flattening;

import java.util.Map;

public class FlattedBlockState {
	public final int id;
	public final FlattedBlock block;
	public final Map<String, String> properties;
	
	public FlattedBlockState(int id, FlattedBlock block, Map<String, String> properties) {
		this.id = id;
		this.block = block;
		this.properties = properties;
	}
}
